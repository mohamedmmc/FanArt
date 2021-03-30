/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
import com.esprit.dao.Session;
import com.esprit.entity.Produit;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class ProduitartisteController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private ScrollPane sp;
    @FXML
    private GridPane gp;
     double x, y;

    /**
     * Initializes the controller class.
     */
      private List<Produit> getList() {
        List<Produit> list;
        ServiceProduit ps = ServiceProduit.getInstance();
        list = ps.displayListbyartiste(Session.getId());
        return list;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        int column = 0;
        int row = 1;
        List list = new ArrayList<>(getList());
        for (Iterator it = list.iterator(); it.hasNext();) {
            Produit e = (Produit) it.next();
            VBox testbox = new VBox();
            testbox.setPadding(new Insets(15, 20, 10, 10));
            testbox.setStyle("-fx-background-color: #14242B");
            String imageURL = e.getImage();
            Image image = new Image(imageURL);
            ImageView im = new ImageView(image);
            im.setFitHeight(150);
            im.setPreserveRatio(true);
            Text titre = new Text();
            titre.setFill(Color.WHITE);
            Text description = new Text();
            description.setFill(Color.WHITE);
            Text artiste = new Text();
            artiste.setFill(Color.WHITE);
            Text prix = new Text();
            prix.setFill(Color.WHITE);
            Button btn = new Button("Supprimer");
            btn.setId("btn_aap" + e.getId());
            prix.setText(String.valueOf(e.getPrix() + "DT"));
            description.setText(e.getDescription());
            artiste.setText(String.valueOf(e.getArtiste()));
            titre.setText(e.getTitre());
            testbox.getChildren().addAll(im, titre, description, artiste, prix, btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e1) {
                    try {
                        ServiceProduit serviceproduit =new ServiceProduit();
                        serviceproduit.deletebyid(e.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(ProduitartisteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if (column == 3) {
                
                column = 0;
                ++row;
            }
            gp.add(testbox, column++, row);
            GridPane.setMargin(testbox, new Insets(10));
        }
    }  
     private void makeDragable() {

        gp.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));
        gp.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        gp.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        gp.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }
    
}
