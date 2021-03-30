/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
import com.esprit.utilis.Connexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class AdminController implements Initializable {
    Connection con;
    PreparedStatement ps ;
    ResultSet rs;

    @FXML
    private TableView<User> userTab;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> numerotel;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label numerolabel;
    @FXML
    private Label typelabel;
    @FXML
    private ImageView cross;

    String imagepath, emaildel;
    @FXML
    private AnchorPane parent;
    double x = 0, y = 0;
    @FXML
    private ImageView imagee;
    @FXML
    private TextField chercher;
    @FXML
    private VBox vue;
    @FXML
    private AnchorPane parentt;
    @FXML
    private TextField numsalle;
    @FXML
    private TextField place;
    @FXML
    private Button parcourrir;
    @FXML
    private Button annuler;
    @FXML
    private Button ajout;
    @FXML
    private TextArea desc;
    @FXML
    private TextField numsalle1;
    @FXML
    private TextField place1;
    @FXML
    private Button parcourrir1;
    @FXML
    private Button annuler1;
    @FXML
    private Button ajout1;
    @FXML
    private TextArea desc1;
    private String pathimage;
    @FXML
    private ImageView imgg;
    private String filename;
    private File source,dest;

    public String verifier(){
    if (numsalle.getText().equals("")|| place.getText().equals("") || desc.getText().equals("") )
       return "true";

    else 
         return "false";
} 
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            makeDragable();
                        

            ServiceUser su = new ServiceUser();
            //Image im = new Image(getClass().getResourceAsStream("/com/esprit/img/guestuser.png"));
            imagee.setFitHeight(150);
            imagee.setPreserveRatio(true);

            userTab.setOnMouseClicked((event) -> {
                //vue.getChildren().remove(imagee);

                Image img = new Image(getClass().getResourceAsStream(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getPhoto()));
                imagee.setImage(img);
                //System.out.println(String.valueOf(su.displayAllList().get(userTab.getSelectionModel().getSelectedIndex()).getNom()));
                nomlabel.setText(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getNom());
                prenomlabel.setText(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getPrenom());
                emaillabel.setText(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getEmail());
                typelabel.setText(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getType());
                numerolabel.setText(String.valueOf(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getNumtel()));

            });
            try {
                // TODO

                ObservableList<User> list = su.getUserList();
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));
                numerotel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
                type.setCellValueFactory(new PropertyValueFactory<>("type"));

                userTab.setItems(list);
            } catch (SQLException ex) {

            }
        });
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }

    private void makeDragable() {

        parentt.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));
        parentt.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        parentt.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        parentt.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }

    @FXML
    private void retour(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "AdminController{" + "emaillabel=" + emaillabel + '}';
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("Voulez vous vraiment supprimer cet utilisateur "+nomlabel.getText()+" "+prenomlabel.getText() +"?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceUser su = new ServiceUser();
            try {
                su.Deluser(emaillabel.getText());
                //System.out.println(emaillabel.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supprimé");
                alert.setHeaderText(null);
                alert.setContentText("Supprimé avec succés !");
                alert.show();
                userTab.setItems(su.getUserList());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur non supprimé !");
            }

        } else {
            alert2.close();
        }

    }

    @FXML
    private void chercher(KeyEvent event) throws SQLException {
        ServiceUser su = new ServiceUser();
        su.chercher(chercher.getText());
        //tableau.setItems(su.getUserList());
        userTab.setItems(su.getUserListfiltered(chercher.getText()));
    }

    @FXML
    private void Annuler(ActionEvent event) {
        numsalle.setText("");
        place.setText("");
        desc.setText("");
    }

    @FXML
    private void add(ActionEvent event) {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String num=numsalle.getText();
        String nbreplace=place.getText();
        String descri=desc.getText();
        
        
        String query ="insert into salle (numsalle,nbreplace,description) values (?,?,?)";
        
        if (verifier()=="false") { 
        try {
        ps =con.prepareStatement(query);
        ps.setString(1, num);
        ps.setString(2,nbreplace);
        ps.setString(3, descri);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"La salle a bien été ajouté ,vous pouvez en mettre un autre.. ");
    
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
        }
    
        } else{
     //ImageIcon img1 = new ImageIcon("C:\\Users\\ranya\\Desktop\\attention.png");
    JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
}
    }

    @FXML
    private void ajoutImg(ActionEvent event) {
        FileChooser f = new FileChooser();
        String imggg;
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            imggg = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(imggg);
            
            imgg.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            source = new File(pathimage);
            dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
        }
        imgg.setFitHeight(94);
        imgg.setFitWidth(94);
    }
}
