/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.dao.ServiceProduit;
import com.esprit.dao.Session;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FXMLpanierController implements Initializable {

    @FXML
    private ScrollPane spfxid;
    @FXML
    private GridPane gpfxid;
    @FXML
    private AnchorPane ap;
    int n=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.listproduit();
        } catch (IOException ex) {
            Logger.getLogger(FXMLpanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     private List<Panier_elem> getList(int idpanier) {
        List<Panier_elem> list;
        ServicePanier_elem servicepanier_elem = ServicePanier_elem.getInstance();
        //list = servicepanier_elem.displayList();
        list = servicepanier_elem.displayListById(idpanier);
        return list;  
    }

    @FXML
    private void payer(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/esprit/view/paymentproduit.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    private void listproduit() throws IOException{
        ServicePanier panier = new ServicePanier();
        int idpanier = panier.verif(Session.getId());
        List list = new ArrayList<>(getList(idpanier));
        int column = 0;
        int row = 1;
            for (Iterator it = list.iterator(); it.hasNext();) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/esprit/view/produitdanslepanier.fxml"));
                HBox cardbox = (HBox) fxmlLoader.load();
                ProduitdanslepanierController produitdanslepaniercontroller = (ProduitdanslepanierController) fxmlLoader.getController();
                cardbox.setSpacing(10);
                cardbox.setStyle("-fx-border-style: dotted;-fx-alignment:center;-fx-border-color: #ff9900;-fx-border-width: 5px;");
                Panier_elem panier_elem = (Panier_elem) it.next();
                ServiceProduit serviceproduit =new ServiceProduit();
                Produit produit =new Produit();
                produit = serviceproduit.displayById(panier_elem.getId_produit()); 
                produitdanslepaniercontroller.sendData(produit,panier_elem,idpanier);
                ++this.n;
                if (column == 1) {
                column = 0;
                ++row;
            }
                this.gpfxid.add(cardbox,column++,row);
                gpfxid.setMargin(cardbox ,new Insets(0, 20, 20, 20));
        }
            gpfxid.setPadding(new Insets(30, 20, 30, 30));
            
    }

    @FXML
    private void loadpage(MouseEvent event) throws IOException {
        this.gpfxid.getChildren().clear();
        this.listproduit();
        
    }
    
    
}
