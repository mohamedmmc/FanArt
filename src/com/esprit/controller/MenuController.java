/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;
import com.esprit.dao.Session;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class MenuController implements Initializable {

    double x,y;
    @FXML
    private Button Accueil;
    @FXML
    private Button Evenement;
    @FXML
    private Button Produit;
    @FXML
    private Button Artistes;
    @FXML
    private Button Profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private AnchorPane ap;
    @FXML
    public BorderPane bp;
    @FXML
    private Button Evenement1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
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
    private void LoadProduit(ActionEvent event) {
        LoadPage("/com/esprit/view/FXMLproduis");
    }


    @FXML
    private void loadprofil(ActionEvent event) {
        LoadPage("/com/esprit/view/interface");
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

    /*private void panier(ActionEvent event) {

          
        //parent.setCenter(root);
        //bp.setCenter(root);
    }*/

    @FXML
    private void evenementbut(ActionEvent event) {
        LoadPage("/com/esprit/view/HomeEvent");
    }

    @FXML
    private void salle(ActionEvent event) {
        LoadPage("/com/esprit/view/ListeSalles");
    }

    @FXML
    private void panier(MouseEvent event) {
        LoadPage("/com/esprit/view/FXMLpanier");
    }

    @FXML
    private void Reclamation(ActionEvent event) {
        LoadPage("/com/esprit/view/Rec");
    }
    

  
    
}

