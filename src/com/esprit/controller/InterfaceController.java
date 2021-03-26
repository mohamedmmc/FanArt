/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.User;
import static com.esprit.utilis.HashCode.generatedHash;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.NoSuchAlgorithmException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class InterfaceController implements Initializable {

    User u;
    String oldmail;
    private int current;

    @FXML
    private AnchorPane parent;
    @FXML
    private Label emailLabel;
    @FXML
    private VBox userInfoContainer;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField prenomfield;
    @FXML
    private TextField mdpfield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField numfield;
    @FXML
    private TextField mdpfieldv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(Session.getId());

    }

    @FXML
    private void retour(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("Voulez vous vraiment vous déconnecter ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                Session.setId(0);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            alert2.close();
        }
    }

    @FXML
    private void modifcompte(ActionEvent event) {
        Platform.runLater(() -> {
            ServiceUser su = new ServiceUser();
            //initData(current);

            u = su.findBymail(Session.getId());
            /*VBox hboxInfo1 = new VBox();
            userInfoContainer.getChildren().addAll(hboxInfo1);
            TextField nametxt = new TextField(u.getNom());
            TextField prenomtext = new TextField();
            TextField mdptext = new TextField();
            TextField mailtext = new TextField();
            TextField numeroteltxt = new TextField();
            prenomtext.setText(u.getPrenom());
            mdptext.setText(u.getMdp());
            mailtext.setText(u.getEmail());
            //nametxt.setText(u.getNumtel());
            hboxInfo1.getChildren().addAll(nametxt);
            hboxInfo1.getChildren().addAll(prenomtext);
            hboxInfo1.getChildren().addAll(mdptext);
            hboxInfo1.getChildren().addAll(mailtext);
            hboxInfo1.getChildren().addAll(numeroteltxt);*/
            int a = u.getNumtel();
            String num = String.valueOf(a);
            // System.out.println(u.getNom());
            nomfield.setText(u.getNom());
            prenomfield.setText(u.getPrenom());
            mdpfield.setPromptText("Tapper votre ancien mot de passe");
            mdpfieldv.setPromptText("Tapper le nouveau mot de passe");
            emailfield.setText(u.getEmail());
            numfield.setText(num);

            oldmail = emailfield.getText();

        });

    }


    @FXML
    private void appliquer(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        String numtell = numfield.getText();
        int num = Integer.parseInt(numtell);
        ServiceUser su = new ServiceUser();
        if ((nomfield.getText().isEmpty()) || prenomfield.getText().isEmpty() || emailfield.getText().isEmpty() || numfield.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tout les champs sont obligatoires !");
            alert.show();
        } else if (numfield.getText().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Numéro invalide !");
            alert.show();
        } else {

            if (su.verify(oldmail, mdpfield.getText()) != 0 && mdpfieldv.getText().isEmpty()) {
                if (!mdpfield.getText().isEmpty() && mdpfieldv.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez indiquer votre nouveau mot de passe");
                    alert.show();
                } else {
                    String Hashed = generatedHash(mdpfieldv.getText(), "SHA-256");
                    User u = new User(nomfield.getText(), prenomfield.getText(), Hashed, emailfield.getText(), num);
                    su.ModifierUser(u, Session.getId());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Profil modifié avec succés!");
                    alert.show();
                    mdpfield.clear();
                    mdpfieldv.clear();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Echec de la modification");
                alert.show();
            }

        }

    }

}
