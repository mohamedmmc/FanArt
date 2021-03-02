/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
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
 * FXML Controller class
 *
 * @author splin
 */
public class CreateCompteController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private TextField numtel;
    @FXML
    private Button retour;
    @FXML
    private Button creation;
    @FXML
    private ImageView cross;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        creation.setOnAction((event) -> {
            String numtell = numtel.getText();
            int num = Integer.parseInt(numtell);
            User u = new User(nom.getText(), prenom.getText(), mdp.getText(), email.getText(), num);
            try {
                ServiceUser su = new ServiceUser();
                su.insert(u);
            } catch (SQLException ex) {
                Logger.getLogger(CreateCompteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Personne insérée avec succés!");
            alert.show();
        });
        retour.setOnAction(event -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    double x, y = 0;

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
    private void annuler(ActionEvent event) {

    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }

}
