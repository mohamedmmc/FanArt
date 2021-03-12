/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
import static com.esprit.entity.User.validate;
import com.esprit.utilis.Session;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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

    private String current;

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
    @FXML
    private Button admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        makeDragable();

        creationCompte.setOnAction(event -> {
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
        admin.setOnAction(event -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Admin.fxml"));
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
    private void verify(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        ServiceUser sp = new ServiceUser();
        User u = new User();

        //sp.verify(email.getText(), mdp.getText());
        if (email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login vide");
            alert.setHeaderText(null);
            alert.setContentText("Login vide");
            alert.show();
        } else if (mdp.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe vide");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe vide");
            alert.show();
        } else {
            if (validate(email.getText())) {
                if (sp.verify(email.getText(), mdp.getText())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie");
                    alert.show();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/interface.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(loader.load()));
                        InterfaceController ic = loader.getController();
                        ic.initData(email.getText());
                        //System.out.println(email.getText());
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Mot de passe ou login incorrect");
                    alert.show();
                }
            }
        }
    }

    public void initData(String mail) {
        current = mail;

    }

}
