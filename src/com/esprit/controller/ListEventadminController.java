/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.EvenementService;
import com.esprit.dao.ServiceUser;
import com.esprit.entity.Evenement;
import com.esprit.entity.User;
import com.esprit.utilis.MailSender;
import com.esprit.utilis.Newmailevent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class ListEventadminController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String>  desc;
    @FXML
    private TableColumn<Evenement, String>  date_debut;
    @FXML
    private TableColumn<Evenement, String>  date_fin;
    @FXML
    private TableColumn<Evenement, String>  salle;
    @FXML
    private TableColumn<Evenement, Integer>  nbplace;
    @FXML
    private TableColumn<Evenement, Integer>  price;
    @FXML
    private TableView<Evenement> lsitevent;
    @FXML
    private TextField titre1;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date_debut1;
    @FXML
    private DatePicker date_fin1;
    @FXML
    private TextField nbplace1;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> artist;
    @FXML
    private ComboBox<String> local;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_reinitialiser;
    int index=-1;
    String pathimage;
    String filename;
    String stringfinal;
    File source;
    File dest;
    String chaine2="C:\\xampp\\htdocs\\img\\events.jpg";
    
    String mail_contenu="";
    @FXML
    private Button bt_upload_img;
    @FXML
    private Button btn_supp;
    @FXML
    private TableColumn<Evenement,String> idevent;
    @FXML
    private Text idselectedevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // TODO
            EvenementService ed;
            ed = EvenementService.getInstance();
            List listtoobser = ed.listlocal();
            ObservableList<String> list = FXCollections.observableArrayList(listtoobser);

            local.setItems(list);
            listtoobser = ed.listartist();
            list = FXCollections.observableArrayList(listtoobser);
            artist.setItems(list);
            DisablePastTime();
          
           btn_enregistrer.setOnAction((ActionEvent event) -> {
                LocalDate value = date_debut1.getValue();
                LocalDate value1 = date_fin1.getValue();
                String s = String.valueOf(value.getMonthValue()) + "/" + String.valueOf(value.getDayOfMonth() + "/" + String.valueOf(value.getYear()));
                String s1 = String.valueOf(value1.getMonthValue()) + "/" + String.valueOf(value1.getDayOfMonth() + "/" + String.valueOf(value1.getYear()));
                Evenement p = new Evenement(titre.getText(), description.getText(), local.getValue(), s, s1, Integer.parseInt(nbplace1.getText()), Integer.parseInt(prix.getText()), stringfinal);
                System.out.println(p.toString());
                EvenementService es;
                
                try {
                    FileUtils.copyFile(source, dest);
                } catch (IOException ex) {
                    Logger.getLogger(ListEventadminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    es = EvenementService.getInstance();
                    
                        es.insert(p);
                        ServiceUser su=new ServiceUser();
            List mail=su.displayAllList();
                    for (Iterator iterator = mail.iterator(); iterator.hasNext();) {
                       User next = (User) iterator.next();
                       Newmailevent mailpourtous =new Newmailevent();
                       mailpourtous.setNom_user(next.getPrenom()+""+next.getNom());
                       MailSender mailsender =new MailSender(); 
                        mailsender.send(next.getEmail(),next.getPrenom()+""+next.getNom(), mailpourtous.getMail_contenu());
                        
                    }
                        
                      
                        
                    

                } catch (SQLException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Evenement insérée avec succés!");
                alert.show();

            });
           
        } catch (SQLException ex) {
            Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
        
this.update();
    }
    public void update() {
        try {
            EvenementService es=new EvenementService();
            ObservableList<Evenement> Listevent=es.displayAll();
             
            
            titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
            date_debut.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
            date_fin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
            nbplace.setCellValueFactory(new PropertyValueFactory<>("Nombre_place"));
            price.setCellValueFactory(new PropertyValueFactory<>("Prix"));
            salle.setCellValueFactory(new PropertyValueFactory<>("Locall"));
           
            idevent.setCellValueFactory(new PropertyValueFactory<>("Id_evenement"));
            
            lsitevent.setItems(Listevent);
                    
                    
                    } catch (SQLException ex) {
            Logger.getLogger(ListEventadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void nbpalcemaximal(MouseEvent event) {
    }

    @FXML
    private void initiate(MouseEvent event) {
          titre1.setText("");
        description.setText("");
        prix.setText("");
        local.setValue("Aucun");
        date_debut1.setValue(null);
        date_fin1.setValue(null);
        nbplace1.setText("");
    }
   private void DisablePastTime() {
         date_debut1.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
          date_fin1.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            
            
            LocalDate today =date_debut1.getValue();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        
    }

    @FXML
    private void UploadImage(MouseEvent event) {
         FileChooser fc = new FileChooser();

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File SelectedFile = fc.showOpenDialog(null);
        if (SelectedFile != null) {
            pathimage = SelectedFile.toString();
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            source = new File(pathimage);
            
            
            dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
//            System.out.println(dest);
//            System.out.println(source);
            stringfinal = "/com/esprit/img/" + filename;
            //..\img\google.png
            //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img

        }
    }

    @FXML
    private void getselected(MouseEvent event) {
                  index=lsitevent.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
       
            
        
       titre1.setText( titre.getCellData(index));
       description.setText(desc.getCellData(index));
       local.setValue(salle.getCellData(index));
       nbplace1.setText(String.valueOf(nbplace.getCellData(index)));
       prix.setText(String.valueOf(price.getCellData(index)));
       idselectedevent.setText(String.valueOf(idevent.getCellData(index)));
        

    }

    @FXML
    private void Supprimerevenement(MouseEvent event) {
        try {
            EvenementService es =new EvenementService();
            Evenement e=new Evenement();
            e.setId_evenement(Integer.parseInt(idselectedevent.getText()));
            es.delete(e);
            lsitevent.getItems().clear();
            this.update();
            
            System.out.println(idselectedevent.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ListEventadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
