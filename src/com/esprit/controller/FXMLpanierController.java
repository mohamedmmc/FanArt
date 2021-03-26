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
import com.esprit.entity.Panier;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        ServicePanier panier =new ServicePanier();
        int idpanier=panier.verif(Session.getId());
        List list = new ArrayList<>(getList(idpanier));
        if (idpanier==-1)
        {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText(null);
                alert.setContentText("Votre Panier est vide !");
                alert.show();  
        }else{
            for (Iterator it = list.iterator(); it.hasNext();) {
            Panier_elem panier_elem = (Panier_elem) it.next();
            ServiceProduit serviceproduit =new ServiceProduit();
            Produit produit =new Produit();
            produit = serviceproduit.displayById(panier_elem.getId_produit()); 
            
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/com/esprit/view/FXMLpanier.fxml"));
            
            VBox testbox = new VBox();
            testbox.setPadding(new Insets(30, 20, 30, 30));
            testbox.setStyle("-fx-background-color: #14242B");
            ImageView im =new ImageView("/com/esprit/img/insertionimage.png");
/*            URL imageURL = getClass().getResource(produit.getImage());
            System.out.println(imageURL);
            Image image = new Image(imageURL.toExternalForm());
            ImageView im = new ImageView(image);
            im.setFitHeight(150);
            im.setPreserveRatio(true);*/
            Text titre = new Text();
            titre.setFill(Color.WHITE);
            Text description = new Text();
            description.setFill(Color.WHITE);
            Text artiste = new Text();
            artiste.setFill(Color.WHITE);
            Text prix = new Text();
            prix.setFill(Color.WHITE);
            Text quantite = new Text();
            quantite.setFill(Color.WHITE);
            /*Button btn = new Button("Ajouter au panier");
            btn.setId("btn_aap" + produit.getId());*/
            prix.setText(String.valueOf(produit.getPrix() + "DT"));
            description.setText(produit.getDescription());
            artiste.setText(String.valueOf(produit.getArtiste()));
            titre.setText(produit.getTitre());
            quantite.setText(String.valueOf(panier_elem.getQuantite()));
            
            testbox.getChildren().addAll(im, titre, description, artiste, prix,quantite);//, btn);
            if (column == 3) {

                column = 0;
                ++row;
            }
            gpfxid.add(testbox, column++, row);
            GridPane.setMargin(testbox, new Insets(10));
        }
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
    private void payer(ActionEvent event) {
        ServicePanier servicepanier =new ServicePanier();
        Panier panier=new Panier();
        panier=servicepanier.displayById(Session.getId());
        servicepanier.updatevalidite(panier);
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("commande");
            alert.setHeaderText(null);
            alert.setContentText("commande passer avec succée");
            alert.show();
        
        
        
        
    }
    
    
}
