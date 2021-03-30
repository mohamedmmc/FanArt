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
import com.esprit.utilis.WebCamGui;
import java.io.File;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class InterfaceController implements Initializable {

    User u, u2;
    String oldmail, oldpass;
    private int current;

    @FXML
    private AnchorPane parent;
    @FXML
    private Label emailLabel;
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
    @FXML
    private ImageView img;
    private String pathimage;
    private String filename;
    private File source;
    private File dest;
    @FXML
    private VBox userInfoContainer1;
    @FXML
    private CheckBox pass_toggle;
    @FXML
    private TextField emailaffiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            try {
                this.togglevisiblePassword(null);
            } catch (SQLException ex) {
                Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServiceUser su = new ServiceUser();
            //initData(current);

            u = su.findBymail(Session.getId());
            int a = u.getNumtel();
            String num = String.valueOf(a);
            // System.out.println(u.getNom());
            nomfield.setText(u.getNom());
            prenomfield.setText(u.getPrenom());
            mdpfield.setPromptText("Tapper votre ancien mot de passe");
            mdpfieldv.setPromptText("Tapper le nouveau mot de passe");
            emailfield.setText(u.getEmail());
            numfield.setText(num);
            Session.filename = u.getPhoto();
            Image imgg = new Image(Session.filename);
            //System.out.println(Session.filename);
            img.setImage(imgg);
            //oldpass = u.getMdp();
            oldmail = u.getEmail();
            // System.out.println(emailfield.toString());
        });

    }

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
    private void appliquer(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException {
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
        } else if (!mdpfield.getText().isEmpty() && mdpfieldv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez indiquer votre nouveau mot de passe");
            alert.show();
        } else {

            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Tapper votre ancien mot de passe pour confirmer");
            dialog.setContentText("Ancien mot de passe : ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {

                if ((su.verify(oldmail, result.get()) != 0)) {
                    /*System.out.println(u.toString());
                    u2 = new User(Session.getId(),nomfield.getText(), prenomfield.getText(), generatedHash(result.get(), "SHA-256"), emailfield.getText(), num);
                    System.out.println(u2.toString());*/
                    if (!mdpfieldv.getText().isEmpty()) {
                        String Hashed = generatedHash(mdpfieldv.getText(), "SHA-256");
                        u = new User(nomfield.getText(), prenomfield.getText(), Hashed, emailfield.getText(), num, Session.filename);
                        su.ModifierUser(u, Session.getId());
                    } else {
                        u = new User(nomfield.getText(), prenomfield.getText(), emailfield.getText(), num, Session.filename);
                        //System.out.println(Session.filename);
                        //System.out.println(u.toString());
                        su.ModifierUserWmdp(u, Session.getId());
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Profil modifié avec succés!");
                    alert.show();
                    if (source != null && dest != null) {
                        FileUtils.copyFile(source, dest);
                    }

                    //Session.filename = "";
                    mdpfield.clear();
                    mdpfieldv.clear();
                    u = su.findBymail(Session.getId());
                    oldmail = u.getEmail();
                    //System.out.println(oldmail);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Il faut saisir votre ancien mot de passe pour valider la modification");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Echec de la modification");
                alert.show();
            }
        }

//            if ((su.verify(oldmail, mdpfield.getText()) != 0 && mdpfieldv.getText().isEmpty()) || Session.filename.isEmpty()) {
//                if (!mdpfield.getText().isEmpty() && mdpfieldv.getText().isEmpty()) {
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Information Dialog");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Veuillez indiquer votre nouveau mot de passe");
//                    alert.show();
//                } else {
//                    String Hashed = generatedHash(mdpfieldv.getText(), "SHA-256");
//                    u = new User(nomfield.getText(), prenomfield.getText(), Hashed, emailfield.getText(), num, Session.filename);
//                    su.ModifierUser(u, Session.getId());
//
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Dialog");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Profil modifié avec succés!");
//                    alert.show();
//                    Session.filename="";
//                    mdpfield.clear();
//                    mdpfieldv.clear();
//                }
//
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText(null);
//                alert.setContentText("Echec de la modification");
//                alert.show();
//            }
//
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser f = new FileChooser();
        String imggg;
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            imggg = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(imggg);
            img.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }

            source = new File(pathimage);
            dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            Session.filename = "/com/esprit/img/" + filename;
        }
//        img.setFitHeight(94);
//        img.setFitWidth(94);
    }

    @FXML
    private void cam(ActionEvent event) {
        WebCamGui.main(new String[0]);
    }

    @FXML
    private void mdpoublie(MouseEvent event) throws IOException {

        Sms sms = new Sms();
        String numm = String.valueOf(u.getNumtel());
        sms.otpsend(numm);
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Confirmation avec SMS");
        dialog.setContentText("Code de cofnfirmation : ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (sms.otpverify(numm, result.get())) {
                Parent part = FXMLLoader.load(getClass().getResource("/com/esprit/view/ChangementMDP.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(part);
                stage.setScene(scene);
                stage.show();

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
    }

    @FXML
    private void togglevisiblePassword(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        ServiceUser su = new ServiceUser();
        if (pass_toggle.isSelected()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Tapper votre mot de passe pour afficher votre mail");
            dialog.setContentText("Mot de passe : ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if ((su.verify(oldmail, result.get()) != 0)) {
                    emailaffiche.setText(emailfield.getText());
                    emailaffiche.setVisible(true);
                    emailfield.setVisible(false);
                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Ancien mot de passe incorrect");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Code erroné");
                alert.show();
            }

        }
        emailfield.setText(emailaffiche.getText());
        emailfield.setVisible(true);
        emailaffiche.setVisible(false);
    }

}
