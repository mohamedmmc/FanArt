/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.EvenementService;
import com.esprit.entity.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
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
    private Button btn_addevent;
    private Button btn_selectevent;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btn_list;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button bt_upload_img;
    String pathimage;
    String filename;
    String stringfinal;
    File source;
    File dest;
    String chaine2="C:\\xampp\\htdocs\\img\\events.jpg";
    

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
                LocalDate value = date_debut.getValue();
                LocalDate value1 = date_fin.getValue();
                String s = String.valueOf(value.getMonthValue()) + "/" + String.valueOf(value.getDayOfMonth() + "/" + String.valueOf(value.getYear()));
                String s1 = String.valueOf(value1.getMonthValue()) + "/" + String.valueOf(value1.getDayOfMonth() + "/" + String.valueOf(value1.getYear()));
                Evenement p = new Evenement(titre.getText(), description.getText(), local.getValue(), s, s1, Integer.parseInt(nbplace.getText()), Integer.parseInt(prix.getText()), stringfinal);
                System.out.println(p.toString());
                EvenementService es;
                try {
                    FileUtils.copyFile(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    es = EvenementService.getInstance();
                    
                        es.insert(p);
                    

                } catch (SQLException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Evenement insérée avec succés!");
                alert.show();

            });
            btn_list.setOnAction((ActionEvent event1) -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListEvent.fxml"));
                    Stage window = (Stage) btn_list.getScene().getWindow();
                    window.setScene(new Scene(root));

                } catch (IOException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btn_stat.setOnAction((ActionEvent event1) -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Statistique.fxml"));
                    Stage window = (Stage) btn_stat.getScene().getWindow();
                    window.setScene(new Scene(root));

                } catch (IOException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btn_acceuil.setOnAction((ActionEvent event1) -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Menu.fxml"));
                    Stage window = (Stage) btn_acceuil.getScene().getWindow();
                    window.setScene(new Scene(root));

                } catch (IOException ex) {
                    Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        date_debut.setValue(null);
        date_fin.setValue(null);
        nbplace.setText("");

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
            stringfinal = "/com/esprit/image/" + filename;
            //..\img\google.png
            //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img

        }
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

}
