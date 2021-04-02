/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.EvenementService;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import static com.esprit.dao.Session.pathfile;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class HomeEventController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField prix;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_reinitialiser;
    @FXML
    private ComboBox<String> artist;
    @FXML
    private ComboBox<String> local;

    @FXML
    private DatePicker date_debut;
    
    @FXML
    private ImageView imageview;
   
    @FXML
    private Button bt_upload_img;
    String pathimage;
    String filename;
    String stringfinal;
    File source;
    File dest;
    String chaine2="http://localhost:8080/img/picture.png";
    
    String mail_contenu="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
imageview.setImage(new Image(chaine2));
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
                LocalDate value = date_debut.getValue();
                LocalDate value1 = date_fin.getValue();
                String s = String.valueOf(value.getMonthValue()) + "/" + String.valueOf(value.getDayOfMonth() + "/" + String.valueOf(value.getYear()));
                String s1 = String.valueOf(value1.getMonthValue()) + "/" + String.valueOf(value1.getDayOfMonth() + "/" + String.valueOf(value1.getYear()));
                Evenement p = new Evenement(titre.getText(), description.getText(), local.getValue(), s, s1, Integer.parseInt(nbplace.getText()), Integer.parseInt(prix.getText()), Session.filename);
                
                EvenementService es;
                
                try {
                    es = EvenementService.getInstance();
                    
                        es.insert(p);
                        ServiceUser su=new ServiceUser();
                        su.sendphp(pathfile);
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
                } catch (IOException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Evenement insérée avec succés!");
                alert.show();

            });
           
        } catch (SQLException ex) {
            Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initiate(MouseEvent event) {
        titre.setText("");
        description.setText("");
        prix.setText("");
        local.setValue("Aucun");
        date_debut.getEditor().clear();
        date_fin.getEditor().clear();
        nbplace.setText("");
        imageview.setImage(new Image(chaine2));

    }

    @FXML
    private void UploadImage(MouseEvent event) {
        FileChooser f = new FileChooser();
        String img;
        
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            System.out.println(img);
            //System.out.println(fc.getAbsolutePath());
            Image i = new Image(img);
            imageview.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            
            //source = new File(pathimage);
            
            //dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            Session.filename="localhost:8080/img/" + filename;
            //su.sendphp(fc.getAbsolutePath());
        }
        imageview.setFitHeight(94);
        imageview.setFitWidth(94);
        //..\img\google.png
        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        pathfile = fc.getAbsolutePath();
    }

   
    private void DisablePastTime() {
         date_debut.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
          date_fin.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            
            
            LocalDate today =date_debut.getValue();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        
    }

    @FXML
    private void nbpalcemaximal(MouseEvent event) {
    }

}
