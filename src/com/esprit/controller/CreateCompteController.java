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
import com.esprit.utilis.Sms;
import com.esprit.utilis.WebCamGui;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.security.MessageDigest;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class CreateCompteController implements Initializable {

    String filename = "";

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
    ServiceUser su = new ServiceUser();
    MailSender ms = new MailSender();
    /**
     * Initializes the controller class.
     */
    Boolean emailvalide = false, mdpvalide = false, mail = false;
    @FXML
    private PasswordField mdp2;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button browse;
    @FXML
    private Label noml;
    @FXML
    private Label prenoml;
    @FXML
    private Label emaill;
    @FXML
    private Label mdpl;
    @FXML
    private Label mdprl;
    @FXML
    private Label tell;
    @FXML
    private Label photol;
    @FXML
    private ImageView pdp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        type.getItems().removeAll(type.getItems());
        type.getItems().addAll("Client", "Artiste");
        type.getSelectionModel().select("Client");

        creation.setOnAction((event) -> {
            emailvalide = validate(email.getText());
            if (email.getText().isEmpty()) {
                emaill.setText("Champs obligatoire !");
            } else if (!emailvalide) {
                emaill.setText("exemple xxx@yyy.com");
            } else {
                emaill.setText("");
            }
            if (prenom.getText().isEmpty()) {
                prenoml.setText("Champs obligatoire !");
            } else {
                prenoml.setText("");
            }
            if (nom.getText().isEmpty()) {
                noml.setText("Champs obligatoire !");
            } else {
                noml.setText("");
            }
            if (filename.isEmpty()) {
                photol.setText("Photo obligatoire !");
            } else {
                photol.setText("");
            }
            if (numtel.getText().isEmpty()) {
                tell.setText("Champs obligatoire !");
            } else if (numtel.getText().length() != 8) {
                tell.setText("Numéro a 8 chiffres");
            } else {
                tell.setText("");
            }
            if (mdp.getText().isEmpty()) {
                mdpl.setText("Champs obligatoire !");
            } else {
                mdpl.setText("");
            }
            if (mdp2.getText().isEmpty()) {
                mdprl.setText("Champs obligatoire !");
            } else {
                mdprl.setText("");
            }
            if (email.getText().isEmpty() || prenom.getText().isEmpty() || mdp.getText().isEmpty() || nom.getText().equals("") || filename.isEmpty() || numtel.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Tout les champs sont obligatoires !");
                alert.show();
                System.out.println(filename.isEmpty());
            } else if (numtel.getText().length() != 8) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Numéro invalide !");
                alert.show();
            } else if (!mdp.getText().equals(mdp2.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe n'est pas identique !");
                alert.show();
            } else {

                if (validate(email.getText())) {
                    String numtell = numtel.getText();
                    int num = Integer.parseInt(numtell);
                    User u = new User(nom.getText(), prenom.getText(), mdp.getText(), email.getText(), num, stringfinal, type.getValue());
                    try {
                        if (su.Existance(u) == 1) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("Mail déjà existant");
                            alert.show();
                        } else if (su.Existance(u) == 2) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("Numéro déjà existant");
                            alert.show();
                        } else {
                            try {
                                Sms sms = new Sms();
                                sms.otpsend(numtel.getText());
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setHeaderText("Code à 6 chiffres envoyé sur votre téléphone");
                                dialog.setContentText("Saisir votre code : ");

                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {

                                    if (sms.otpverify(numtel.getText(), result.get())) {
                                        su.insert(u);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Profil crée avec succés!");
                                        alert.show();
                                        mail = true;
                                        try {

                                            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
                                            Scene scene = new Scene(page1);
                                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            stage.setScene(scene);
                                            stage.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        try {
                                            FileUtils.copyFile(source, dest);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else if (!numtel.getText().equals(result.get())) {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setHeaderText(null);
                                        alert.setContentText("Code de vérification erroné");
                                        alert.show();
                                    }
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Echec de la création du compte, vous pouvez réessayer");
                                    alert.show();
                                }
                                if (mail) {
                                    ms.send(email.getText(), nom.getText());
                                }
                            } catch (NoSuchAlgorithmException | InterruptedException | SQLException ex) {
                                Logger.getLogger(CreateCompteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CreateCompteController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Mail invalid");
                    alert.show();
                }
            }

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
    String pathimage;

    String stringfinal;
    File source;
    File dest;

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
        Session.setFile(filename);
        stage.close();
    }

    @FXML
    private void browseaction(ActionEvent event) throws FileNotFoundException {
        FileChooser f = new FileChooser();
        String img;
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            pdp.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            source = new File(pathimage);
            dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
        }
        pdp.setFitHeight(94);
        pdp.setFitWidth(94);
        //..\img\google.png
        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img

    }

    @FXML
    private void cam(ActionEvent event) throws InterruptedException {
        /*Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        WebcamPanel webcampanel = new WebcamPanel(webcam);
        webcampanel.setImageSizeDisplayed(true);
        JFrame frame = new  JFrame();
        frame.add(webcampanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        //frame.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);*/

        WebCamGui.main(new String[0]);

        /* Image image = new Image(Session.getFile());
            

            pdp.setImage(image);
            pdp.setFitHeight(94);
            pdp.setFitWidth(94);*/
    }

}
