/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FantArtMenuController implements Initializable {

    @FXML
    private BorderPane bp;
     double x,y;
    @FXML
    private HBox ajouterProd;
    @FXML
    private ImageView icone;
    @FXML
    private Label Labelap;
    @FXML
    private VBox vboxprod;
    @FXML
    private Label profilname;
    @FXML
    private ImageView imageprofil;
    @FXML
    private HBox cross;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser serviceuser = new ServiceUser();
           User user = new User();
           user = serviceuser.findBymail(Session.getId());
           profilname.setText(user.getPrenom()+" "+user.getNom());
           Image image = new Image("http://"+user.getPhoto());
           imageprofil.setImage(image);
           makeDragable();
                   ServiceUser su = new ServiceUser();
//        try {
//            if (su.verifytype(Session.getId())){
//                vboxprod.getChildren().remove(ajouterProd);
//                /*ajouterProd.setVisible(false);
//                icone.setVisible(false);
//                Labelap.setVisible(false);*/
//                
//            }
//        } catch (SQLException | NoSuchAlgorithmException ex) {
//            Logger.getLogger(FantArtMenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }  
    public void LoadPage(String page) {

         Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
    }
     private void makeDragable() {

        bp.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));
        bp.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        bp.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        bp.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }

    @FXML
    private void panier(MouseEvent event) throws SQLException, NoSuchAlgorithmException {
    LoadPage("/com/esprit/view/FXMLpanier");
    }

    @FXML
    private void listevenement(MouseEvent event) {
       // LoadPage("/com/esprit/view/Admin");
       LoadPage("/com/esprit/view/ListEvenement");
    }

    @FXML
    private void ajouterevenement(MouseEvent event) {
        LoadPage("/com/esprit/view/HomeEvent");
    }

    @FXML
    private void listmesevenement(MouseEvent event) {
        
    }

    @FXML
    private void listproduit(MouseEvent event) throws SQLException, NoSuchAlgorithmException {
    LoadPage("/com/esprit/view/produit");
    }

    @FXML
    private void ajouterproduit(MouseEvent event) {
        LoadPage("/com/esprit/view/FXMLAffichageProduit");
    }

    @FXML
    private void listermesproduit(MouseEvent event) {
    }

    @FXML
    private void ajouterProduit(MouseEvent event) {
    }

    @FXML
    private void deco(MouseEvent event) {
        Stage stagee = (Stage) cross.getScene().getWindow();
        stagee.close();
       try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Main.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
        stage.show();
    
        } catch(Exception e) {
        e.printStackTrace();
        }
    }

    @FXML
    private void loadale(MouseEvent event) {
        LoadPage("/com/esprit/view/ListSalle");
    }

    @FXML
    private void profil(MouseEvent event) {
        LoadPage("/com/esprit/view/interface");
    }

    @FXML
    private void listmesevenementa(MouseEvent event) {
        LoadPage("/com/esprit/view/Rec");
    }
    
}
