
package com.esprit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ranya
 */
public class InfoReservationController implements Initializable {

    @FXML
    private TextField nomclient;
    
    @FXML
    private TextField numsalle;
    
    @FXML
    private TextField nbreplace;
    
    @FXML
    private TextField date;
    
    @FXML
    private TextField artiste;
    
    @FXML
    private Button retour;
    
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
        
        Image image = new Image (getClass().getResourceAsStream("/Image/cinema.jpg"));
        //ImageView.setImage(image);
    }  

     
    @FXML
    private void retour(ActionEvent event) {
        
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
             } catch(Exception e) {
              e.printStackTrace();
             }
    }
    }
    

