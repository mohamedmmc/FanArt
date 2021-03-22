/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_addevent;
    @FXML
    private Button btn_listevent;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // Image image1;
      // image1 = new Image("file: c:/list");
      // img1.setImage(image1);
       
    }    

    @FXML
    private void AddEventPage(MouseEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/pidev/view/HomeEvent.fxml"));
            Stage window = (Stage) btn_addevent.getScene().getWindow();
            window.setScene(new Scene(root));
           

        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ListEventPage(MouseEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/pidev/view/ListEvent.fxml"));
            Stage window = (Stage) btn_listevent.getScene().getWindow();
            window.setScene(new Scene(root));
           

        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
