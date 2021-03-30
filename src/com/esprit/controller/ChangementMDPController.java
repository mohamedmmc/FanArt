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
import com.esprit.utilis.Sms;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ChangementMDPController implements Initializable {

    @FXML
    private TextField v2;
    @FXML
    private TextField v1;
    @FXML
    private PasswordField v1c;
    @FXML
    private PasswordField v2c;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Button turnoff;
    @FXML
    private TextField mailoublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser u = new ServiceUser();
        //System.out.println(String.valueOf(u.findBymail(Session.getId()).getNumtel()));
        //u.findBymail(Session.getId());

        //System.out.println(u.findBymail(Session.getId()).getEmail());
        //System.out.println(su.getEmail());
        if (u.findBymail(Session.getId()).getEmail() != null) {
            mailoublie.setText(String.valueOf(u.findBymail(Session.getId()).getNumtel()));
            mailoublie.setDisable(true);
        } else {
            mailoublie.setDisable(false);
        }

    }

    @FXML
    private void afficher(ActionEvent event) {

        if (checkbox.isSelected()) {
            v1.setText(v1c.getText());
            v2.setText(v2c.getText());
            v1.setVisible(true);
            v1c.setVisible(false);
            v2.setVisible(true);
            v2c.setVisible(false);
            return;
        }
        v1c.setText(v1.getText());
        v1c.setVisible(true);
        v1.setVisible(false);
        v2c.setText(v2.getText());
        v2c.setVisible(true);
        v2.setVisible(false);

    }

    @FXML
    private void valider(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
        ServiceUser u = new ServiceUser();
        String mail = mailoublie.getText();
        v1c.getText();
        v2c.getText();

        String Hashed = generatedHash(v1c.getText(), "SHA-256");
        //System.out.println(v1c.getText());
        if (mail.isEmpty() || v1c.getText().isEmpty() || v2c.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Tout les champs obligatoire pour changer de mot de passe !");
            alert.show();
        } else if (mail.length() != 8) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Numéro invalide !");
            alert.show();
        } else if (!v1c.getText().equals(v2c.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe n'est pas identique !");
            alert.show();
        } else {

            if (u.findBymail(Session.getId()).getId() != 0) {
                u.MotdepasseOublie(Hashed, Session.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Modifié avec succées !");
                alert.show();
                Stage stage = (Stage) turnoff.getScene().getWindow();
                stage.close();

            } else {
                int mailint = Integer.parseInt(mail);
                if (u.VerifyNum(mailint) != 0) {
                    Sms sms = new Sms();
                    sms.otpsend(mailoublie.getText());
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setHeaderText("Confirmation avec SMS");
                    dialog.setContentText("Code de cofnfirmation : ");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        if (sms.otpverify(mailoublie.getText(), result.get())) {
                            u.MotdepasseOublie(Hashed, u.VerifyNum(mailint));
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Modifié avec succées !");
                            alert.show();
                            Stage stage = (Stage) turnoff.getScene().getWindow();
                            stage.close();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Code erroné");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Echec de la réinitialisation, vous pouvez réessayer");
                        alert.show();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Numéro inconnu !");
                    alert.show();
                }

            }

        }
    }
}
