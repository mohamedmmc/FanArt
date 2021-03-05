/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
import com.esprit.entity.Produit;
import static java.awt.SystemColor.window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FXMLInterfaceproduitController implements Initializable {

    @FXML
    private TextField titrefxid;
    @FXML
    private TextField prixfxid;
    @FXML
    private TextArea descriptionfxid;
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView imageviewfxid;
    
    String pathimage;
    @FXML
    private Button retourfxid;
    @FXML
    private Button AjouterProduitFxid;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        makeDragable();
        
       /* AjouterProduitFxid.setOnAction(event -> {  
            //String image=UploadImage(event);
          
 
        });*/
        
        
        
    } 
     double x=0,y=0;
     private void makeDragable() {

        parent.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));

        parent.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);

        }));

    }

    @FXML
    private void Insert(MouseEvent event) {
          Produit p = new Produit(titrefxid.getText(),11, descriptionfxid.getText(),pathimage, Float.parseFloat(prixfxid.getText()));
            ServiceProduit pdao = ServiceProduit.getInstance();
            pdao.Insert(p);
    }

    @FXML
    private String UploadImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png"));
        List<File> f = fc.showOpenMultipleDialog(null);
        String x="";
        for (File file:f)
        {
          x= file.getAbsolutePath();
    }
         Image image = new Image(new FileInputStream(f.get(0)));
         imageviewfxid.setImage(image);
         pathimage=x;
        return x;
    }

    @FXML
    private void retour(ActionEvent event) {
   try{
     Parent root= FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLAffichageProduit.fxml"));
             Stage window =(Stage)retourfxid.getScene().getWindow();
             window.setScene(new Scene(root));
     }
 catch (IOException e){
            System.err.println(e);
        }
    }


    @FXML
    private void retour(MouseEvent event) {
    }
}
      
         

