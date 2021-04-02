/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnPayer;
    @FXML
    private Button btnPlacer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnAjout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLDocument.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnPayer.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLPayerEmp.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnAffiche.setOnAction(event -> {
            try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLListEmp.fxml"));
                Scene scene = new Scene(page3);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnPlacer.setOnAction(event -> {

            try {
                Parent page4 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLPlacerEmp.fxml"));
                Scene scene = new Scene(page4);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
