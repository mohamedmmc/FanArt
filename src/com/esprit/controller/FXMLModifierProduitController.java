/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FXMLModifierProduitController implements Initializable {

    @FXML
    private TextField mtitrefxid;
    @FXML
    private TextField mprixfxid;
    @FXML
    private TextArea mdescriptionfxid;
    @FXML
    private Button ModifierProduitFxid;
    @FXML
    private Button retourfxid;
    @FXML
    private ImageView imageviewfxid;
    String pathimage="";
    String titre;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mtitrefxid.setText(titre);
        // TODO
    }    

    @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException {
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
     public void plonger(String titre)
     {
         this.titre=titre;
     }
    
    
}

   
