/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.entity.Panier;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */

public class FXMLproduisController implements Initializable {

    @FXML
    private ScrollPane spfxid;
    @FXML
    private GridPane gpfxid;
    @FXML
    private AnchorPane apfxid;
    

    /**
     * Initializes the controller class.
     */
    private List<Produit> getList() {
        List<Produit> list;
        ServiceProduit ps = ServiceProduit.getInstance();
        list = ps.displayList();
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        // TODO
        
        List list = new ArrayList<>(getList());
        for (Iterator it = list.iterator(); it.hasNext();) {
            Produit e = (Produit) it.next();
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
            VBox testbox = new VBox();
            testbox.setPadding(new Insets(15,20, 10,10));
            testbox.setStyle("-fx-background-color: #14242B");
            //ImageView im =new ImageView("/com/esprit/img/insertionimage.png");
            URL imageURL = getClass().getResource(e.getImage()); 
            System.out.println(imageURL);
            Image image = new Image(imageURL.toExternalForm());
            ImageView im =new ImageView(image);
            im.setFitHeight(150);
            im.setPreserveRatio(true);
            Text titre =new Text();
            titre.setFill(Color.WHITE);
            Text description =new Text();
            description.setFill(Color.WHITE);
            Text artiste =new Text();
            artiste.setFill(Color.WHITE);
            Text prix =new Text();
            prix.setFill(Color.WHITE);
            Button btn=new Button("Ajouter au panier");
            btn.setId("btn_aap"+e.getId());
            prix.setText(String.valueOf(e.getPrix()+"DT"));
            description.setText(e.getDescription());
            artiste.setText(String.valueOf(e.getArtiste()));
            titre.setText(e.getTitre());
            testbox.getChildren().addAll(im,titre,description,artiste,prix,btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e1) {
                    ServicePanier p= new ServicePanier();
                    Boolean x=p.verif(1);
                    if(x){
                       Panier_elem pe =new Panier_elem(1,e.getId(),1);
                       ServicePanier_elem spe =new ServicePanier_elem();
                       spe.Insert(pe);
                    }else{
                        Panier panier = new Panier(1,"nonvalide");
                        p.Insert(panier);
                        
                    }
                    /* root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
                    Stage window = (Stage) btn.getScene().getWindow();
                    window.setScene(new Scene(root));*/  
                }
            });
            if (column == 3) {
                
                column = 0;
                ++row;
            }
            gpfxid.add(testbox, column++, row);
            GridPane.setMargin(testbox, new Insets(10));   
        }
    }
    public void ajouter_panier(){
       
    }

}
