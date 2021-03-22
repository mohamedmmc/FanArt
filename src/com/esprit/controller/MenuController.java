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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class MenuController implements Initializable {

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
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void LoadPage(String page) {
         Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
    }
     @FXML
    private void LoadProduit(ActionEvent event) {
        LoadPage("/com/esprit/view/FXMLproduis");
    }

    @FXML
    private void Loadajout(ActionEvent event) {
    LoadPage("/com/esprit/view/FXMLAffichageProduit");
    }
    

  
    
}

