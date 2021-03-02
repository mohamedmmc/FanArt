/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author TheDot
 */
public class LoginController implements Initializable {

    @FXML
    AnchorPane parent;
    double x = 0, y = 0;
    
    @FXML
    private TextField email;
    @FXML
    private Button creationCompte;
    @FXML
    private TextField mdp;
    @FXML
    private Button connexion;
    @FXML
    private ImageView cross;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        creationCompte.setOnAction(event ->{
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/CreateCompte.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    private void makeDragable() {
        
        parent.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));

        parent.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);

        }));
        
        
        
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verify(ActionEvent event) throws SQLException {
        ServiceUser sp = new ServiceUser();
        if (email.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Login vide");
                    alert.setHeaderText(null);
                    alert.setContentText("Login vide");
                    alert.show();}
        else if (mdp.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Mot de passe vide");
                    alert.setHeaderText(null);
                    alert.setContentText("Mot de passe vide");
                    alert.show();}
        sp.verify(email.getText(), mdp.getText());
    }
    
    
}
