/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.Panier;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import com.esprit.entity.User;
import com.esprit.utilis.MyListener;
//import static com.sun.tools.doclint.Entity.image;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juka
 */
public class ProduitController implements Initializable{


    @FXML
    private ImageView Img;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane gpfxid;
    @FXML
    private VBox chosenProduitCard;
    @FXML
    private Label ProduitNameLable;
    @FXML
    private Label ProduitPriceLabel;
    @FXML
    private TextField quantite;
    @FXML
    private Label artiste;
    private MyListener myListener;
    @FXML
    private TextField searchfxid;
    
    Produit prod=new Produit();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.listproduit();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
      private List<Produit> getList() {
        List<Produit> list;
        ServiceProduit ps = ServiceProduit.getInstance();
        list = ps.displayList();
        return list;
    }
     private void setChosenProduit(Produit produit) {
        ProduitNameLable.setText(produit.getTitre());
        ProduitPriceLabel.setText(produit.getPrix()+" Dt");
        Image image = new Image("http://"+produit.getImage());
        Img.setImage(image);
        User u=new User();
        ServiceUser su =new ServiceUser();
        u=su.findBymail(produit.getArtiste());
        artiste.setText(u.getNom()+" "+u.getPrenom());
    } 
      
    private void listproduit() throws IOException{
        
        
        List list = new ArrayList<>(getList());
        int column = 0;
        int row = 1;
        if(list.size()>0){
         setChosenProduit((Produit) list.get(0));
        }
         myListener = new MyListener() {
                public void onClickListener(Produit produit) {
                    setChosenProduit(produit);
                    prod=produit;
                    System.out.println(prod.toString());
                }
         };
            for (Iterator it = list.iterator(); it.hasNext();) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/esprit/view/itemproduit.fxml"));
                VBox cardbox = (VBox) fxmlLoader.load();
                ItemproduitController itemproduitController = (ItemproduitController) fxmlLoader.getController();         
                ServiceProduit serviceproduit =new ServiceProduit();
                Produit produit =(Produit) it.next();
                itemproduitController.sendData(produit,myListener);
                if (column == 3) {
                column = 0;
                ++row;
            }
                this.gpfxid.add(cardbox,column++,row);
                 gpfxid.setMinWidth(Region.USE_COMPUTED_SIZE);
                gpfxid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gpfxid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gpfxid.setMinHeight(Region.USE_COMPUTED_SIZE);
                gpfxid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gpfxid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(cardbox, new Insets(10));

        }


            
    }
     private void listproduitStart() throws IOException{
        List<Produit> list = new ArrayList<>(getList());
        list=(List<Produit>) list.stream().filter((t) -> {
           return t.getTitre().startsWith(searchfxid.getText()); //To change body of generated lambdas, choose Tools | Templates.
        }).collect(Collectors.toList());
        int column = 0;
        int row = 1;
        if(list.size()>0){
         setChosenProduit((Produit) list.get(0));
        }
         myListener = new MyListener() {
                public void onClickListener(Produit produit) {
                    setChosenProduit(produit);
                    prod = produit;
                    
                }
         };
            for (Iterator it = list.iterator(); it.hasNext();) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/com/esprit/view/itemproduit.fxml"));
                VBox cardbox = (VBox) fxmlLoader.load();
                ItemproduitController itemproduitController = (ItemproduitController) fxmlLoader.getController();         
                ServiceProduit serviceproduit =new ServiceProduit();
                Produit produit =(Produit) it.next();
                itemproduitController.sendData(produit,myListener);
                if (column == 3) {
                column = 0;
                ++row;
            }
                this.gpfxid.add(cardbox,column++,row);
                 gpfxid.setMinWidth(Region.USE_COMPUTED_SIZE);
                gpfxid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gpfxid.setMaxWidth(Region.USE_PREF_SIZE);
                gpfxid.setMinHeight(Region.USE_COMPUTED_SIZE);
                gpfxid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gpfxid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(cardbox, new Insets(10));

        }


            
    }

    @FXML
    private void Chercher(KeyEvent event) throws IOException {
         this.gpfxid.getChildren().clear();
         this.listproduitStart();
    }

    @FXML
    private void AjouterPanier(ActionEvent event) {
        ServicePanier p = new ServicePanier();
        Integer x = p.verif(Session.getId());
        Integer quantité = Integer.parseInt(quantite.getText());
        if (x != -1) {
            ServicePanier_elem pe = new ServicePanier_elem();
            int y = pe.verif(prod.getId(), x);
            //System.out.println(y);
            if (y != 0) {
                pe.modifQuantite(quantité, prod.getId(), x);
            } else {
                Panier_elem pee = new Panier_elem(x, prod.getId(), quantité);
                ServicePanier_elem spe = new ServicePanier_elem();
                spe.Insert(pee);
            }

        } else {
            Panier panier = new Panier(Session.getId(), "nonvalide");
            p.Insert(panier);
            Panier_elem pee = new Panier_elem(p.verif(Session.getId()), prod.getId(), quantité);
            ServicePanier_elem spe = new ServicePanier_elem();
            spe.Insert(pee);

        }
    }
    
    
}
