/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Produit;
import com.esprit.utilis.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class ItemproduitController implements Initializable {

    @FXML
    private Label priceLable;
    @FXML
    private Label titreLabel;
    @FXML
    private ImageView image;
    @FXML
    private VBox hbox;
    private Produit produit;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void sendData(Produit p,MyListener myListener) {
        this.produit = p;
        this.myListener = myListener;
        Image imageBack = new Image("http://localhost:80/img/4813762.jpg");
        Image imagec = new Image("http://" + p.getImage());
        this.image.setFitHeight(150);
        this.image.setPreserveRatio(true);
        titreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        priceLable.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
//String[] colors = new String[]{"B9E5EF", "BDB2FE", "DA3636", "36DAA3", "FF5056"};
  //    this.hbox.setStyle("-fx-background-color:#" + colors[(int)(Math.random() * (double)colors.length)] + ";-fx-background-radius: 15;-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0), 10, 0, 0, 10);");
        this.titreLabel.setText(p.getTitre());
        //this.description.setText("Description: "+p.getDescription());
        this.image.setImage(imagec);
        this.priceLable.setText(String.valueOf(p.getPrix()));
    }

    @FXML
    private void click(MouseEvent event) {
         myListener.onClickListener(produit);
    }
    
}
