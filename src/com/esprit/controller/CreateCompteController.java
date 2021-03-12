/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
import static com.esprit.entity.User.validate;
import com.esprit.utilis.MailSender;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

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
    Boolean emailvalide = false, mdpvalide = false;
    @FXML
    private PasswordField mdp2;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button browse;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        type.getItems().removeAll(type.getItems());
        type.getItems().addAll("Client", "Artiste");
        type.getSelectionModel().select("Client");
        creation.setOnAction((event) -> {
            if (email.getText().isEmpty())
            {
                System.out.println("test reussi");
            }

            
            if (email.getText().isEmpty() || prenom.getText().isEmpty() || mdp.getText().isEmpty() || nom.getText().isEmpty() || browse.getText().isEmpty() || numtel.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Tout les champs sont obligatoires !");
                alert.show();
            }
            else if (numtel.getText().length()!=8)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Numéro invalide !");
                alert.show();
            }
            else if (!mdp.getText().equals(mdp2.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe n'est pas identique !");
                alert.show();
            }
            else
            {
                if (validate(email.getText())) {
                try {
                FileUtils.copyFile(source, dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
                String numtell = numtel.getText();
            int num = Integer.parseInt(numtell);
                User u = new User(nom.getText(), prenom.getText(), mdp.getText(), email.getText(), num, stringfinal, type.getValue());
                ServiceUser su = new ServiceUser();
                MailSender ms = new MailSender();
                try {
                    su.insert(u);
                    Thread.sleep(2000);
                    ms.send(email.getText(),nom.getText());
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(CreateCompteController.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (InterruptedException ex) {
                        Logger.getLogger(CreateCompteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Profil crée avec succés!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    byte[] userimage = null;
    double x, y = 0;
    String pathimage;
    String filename;
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
        stage.close();
    }

    @FXML
    private void browseaction(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File SelectedFile = fc.showOpenDialog(null);
        if (SelectedFile != null)
        {
            pathimage = SelectedFile.toString();
            int index= pathimage.lastIndexOf('\\');
            if (index>0)
            {
                filename = pathimage.substring(index+1);
            }
             source = new File(pathimage);
             dest = new File(System.getProperty("user.dir")+"\\src\\com\\esprit\\img\\" + filename);
//            System.out.println(dest);
//            System.out.println(source);
            stringfinal="/com/esprit/img/" + filename;
            //..\img\google.png
                    //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
            
            
        }

    }

}
