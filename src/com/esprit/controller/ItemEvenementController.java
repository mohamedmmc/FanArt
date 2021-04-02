/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.Session;
import com.esprit.entity.Evenement;
import com.esprit.entity.ListData;
import com.esprit.entity.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.esprit.utilis.MyListener1;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ItemEvenementController implements Initializable {

    @FXML
    private VBox hbox;
    @FXML
    private Label title;
    @FXML
    private Label prix;
    @FXML
    private ImageView image;
    @FXML
    private Label desc;
    @FXML
    private Label local;
    private Evenement evenement;
    private MyListener1 myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void sendData(Evenement e,MyListener1 myListener) {
    this.evenement=e;
    this.myListener = myListener;
    System.out.println(e.toString());
      Image img = new Image("http://"+e.getImage());
      this.image.setImage(img);
      this.title.setText(e.getTitre());
      this.desc.setText(e.getDescription());
      this.local.setText(e.getLocall());
      this.prix.setText(String.valueOf(e.getPrix()));
      ListData.getEvenement().setPrix(Integer.parseInt(this.prix.getText()));
      this.hbox.setId(e.getTitre());
      
//      this.btn_supprimer.setId(String.valueOf(e.getId_evenement()));
      
   }

  
    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(evenement);
    }
    
}
