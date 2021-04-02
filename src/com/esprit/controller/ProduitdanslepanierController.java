/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServicePanier_elem;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import java.awt.Color;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class ProduitdanslepanierController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private TextField quantite;
    @FXML
    private Label prixunitaire;
    @FXML
    private Label soustotal;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView iconsupp;
    @FXML
    private Label titrehead;
    @FXML
    private Label quantitehead;
    @FXML
    private Label prixunitairehead;
    @FXML
    private Label soustotalhead;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

   
    public void sendData(Produit p, Panier_elem pe,int idpanier) {
        Image imageBack = new Image("http://localhost:80/img/4813762.jpg");
        Image imagec = new Image("http://" + p.getImage());
        this.iconsupp.setImage(new Image("http://localhost:80/img/red-x.png"));
        this.image.setFitHeight(150);
        this.image.setPreserveRatio(true);
        this.iconsupp.setId(idpanier+"*"+p.getId());
        this.quantite.setId(idpanier+"*"+p.getId());
        titre.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        quantite.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        prixunitaire.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        soustotal.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        titrehead.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        quantitehead.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        prixunitairehead.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        soustotalhead.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
String[] colors = new String[]{"B9E5EF", "BDB2FE", "DA3636", "36DAA3", "FF5056"};
      this.hbox.setStyle("-fx-background-color:#" + colors[(int)(Math.random() * (double)colors.length)] + ";-fx-background-radius: 15;-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0), 10, 0, 0, 10);");
        this.titre.setText(p.getTitre());
        //this.description.setText("Description: "+p.getDescription());
        this.quantite.setText(String.valueOf(pe.getQuantite()));
        this.image.setImage(imagec);
        this.prixunitaire.setText(String.valueOf(p.getPrix()));
        this.soustotal.setText(String.valueOf(p.getPrix() * pe.getQuantite()));
    }

     @FXML
    private void supprimer(MouseEvent event) throws SQLException {
                            String idpanier;
                            String idproduit;
                            idpanier=this.iconsupp.getId().substring(0,this.iconsupp.getId().indexOf("*"));
                            idproduit=this.iconsupp.getId().substring(this.iconsupp.getId().indexOf("*")+1,this.iconsupp.getId().length());
                            System.out.println("id panier"+idpanier);
                            System.out.println("id produit"+idproduit);
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Supprimé ce produit !!");
                            alert.setHeaderText(null);
                            alert.setContentText("êtes-vous sûr de vouloir supprimer ?");
                            ButtonType buttonTypeOne = new ButtonType("Supprimé");
                            ButtonType buttonTypeTwo = new ButtonType("Annulé");
                            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == buttonTypeOne){
                                   ServicePanier_elem spe= new ServicePanier_elem();
                                   spe.delete(Integer.parseInt(idpanier),Integer.parseInt(idproduit));
                                } else if (result.get() == buttonTypeTwo) {
                                 Alert alert1 = new Alert(AlertType.CONFIRMATION);
                                 alert.setTitle("Annulé");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Suppression annuler");
        
    }
    }



    @FXML
    private void modifier(KeyEvent e) {
        if(e.getCode().toString().equals("ENTER"))
        {
        int quantité = Integer.parseInt(this.quantite.getText());
        String idpanier;
        String idproduit;
        idpanier = this.quantite.getId().substring(0, this.quantite.getId().indexOf("*"));
        idproduit = this.quantite.getId().substring(this.quantite.getId().indexOf("*") + 1, this.quantite.getId().length());
        if (quantité > 0) {
            ServicePanier_elem pe = new ServicePanier_elem();
            pe.modifQuantitepanier(quantité,Integer.parseInt(idproduit),Integer.parseInt(idpanier));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("modification effectuée avec succès");
            alert.show();
            this.quantite.setText(String.valueOf(quantité));
            this.soustotal.setText(String.valueOf(Float.parseFloat(this.prixunitaire.getText())* quantité)+"Dt");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Quantite " + quantité + " !!");
            alert.show();
        }
        }
    }
    
    
}
