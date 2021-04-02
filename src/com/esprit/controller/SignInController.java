/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.User;
import static com.esprit.entity.User.validate;
import com.esprit.utilis.MailSender;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.jfoenix.controls.JFXButton;
import java.io.File;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class SignInController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private JFXButton connexion;
    @FXML
    private VBox parent;
    double x = 0, y = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.setId(0);
        makeDragable();

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
    private void mdpoublie(MouseEvent event) throws IOException {
        Parent part = FXMLLoader.load(getClass().getResource("/com/esprit/view/ChangementMDP.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void verify(ActionEvent event) throws IOException, InterruptedException, SQLException, NoSuchAlgorithmException {
        ServiceUser sp = new ServiceUser();
        User u = new User();

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
                if (sp.verify(email.getText(), mdp.getText()) != 0) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Dialog");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Connexion réussie");
//                    Session.filename = "";
//                    alert.show();
                    u = sp.findBymail(sp.verify(email.getText(), mdp.getText()));
                    System.out.println(u.toString());
                    if (u.getType().equals("Admin")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/Admin.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(loader.load()));
                        stage.show();
                    } else {
                        try {
                            Session.setId(sp.verify(email.getText(), mdp.getText()));

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/FantArtMenu.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(loader.load()));
                            stage.show();
                            //Session.wrong = 0;
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Mot de passe ou login incorrect");
                    alert.show();
                    Session.wrong += 1;
                    if (Session.wrong == 5) {
                        Webcam webcam = Webcam.getDefault();
                        webcam.setViewSize(WebcamResolution.VGA.getSize());
                        webcam.open();
                        ImageIO.write(webcam.getImage(), "PNG", new File("D:\\hack.png"));
                        MailSender ok = new MailSender();
                        ok.sendHack(email.getText(), "D:\\hack.png");
                        webcam.close();
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setHeaderText(null);
                        alert.setTitle("Activité suspecte detectée");
                        alerta.setContentText("Un mail a été envoyé a l'utilisateur");
                        Session.filename = "";
                        alerta.show();

                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Email incorrect");
                alert.show();
            }
        }
    }

}
