/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.dao.Session;
import com.esprit.entity.Panier;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
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
    double x, y;

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
        Platform.runLater(() -> {
        makeDragable();
        int column = 0;
        int row = 1;
        
        List list = new ArrayList<>(getList());
        for (Iterator it = list.iterator(); it.hasNext();) {
            Produit e = (Produit) it.next();
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/com/pidev/view/Event.fxml"));
            VBox testbox = new VBox();
            testbox.setPadding(new Insets(15, 20, 10, 10));
            testbox.setStyle("-fx-background-color: #14242B");
            //ImageView im =new ImageView("/com/esprit/img/insertionimage.png");
            String imageURL = e.getImage();
//System.out.println(e.getImage());
            Image image = new Image(imageURL);
            
            //ImageView im = new ImageView(getClass().getResource(imageURL).toExternalForm());
            
            
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
            Button btn = new Button("Ajouter au panier");
            btn.setId("btn_aap" + e.getId());
            prix.setText(String.valueOf(e.getPrix() + "DT"));
            description.setText(e.getDescription());
            artiste.setText(String.valueOf(e.getArtiste()));
            titre.setText(e.getTitre());
            testbox.getChildren().addAll(im, titre, description, artiste, prix, btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e1) {
                    ServicePanier p = new ServicePanier();
                    Integer x = p.verif(Session.getId());
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setHeaderText("Selectionnez la quantité");
                    dialog.setContentText("Quantité : ");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String test = result.get();
                            Integer quantité = Integer.parseInt(test);
                        if (x != -1) {
                            
                            ServicePanier_elem pe = new ServicePanier_elem();
                            int y = pe.verif( e.getId(),x);
                            //System.out.println(y);
                            if (y != 0) {
                              
                                    pe.modifQuantite(quantité, e.getId(), x);
                                                               
                            } else {
                                Panier_elem pee = new Panier_elem(x,e.getId(), quantité);
                                ServicePanier_elem spe = new ServicePanier_elem();
                                spe.Insert(pee);
                            }

                        } else {
                            Panier panier = new Panier(Session.getId(), "nonvalide");
                            
                            p.Insert(panier);
                            Panier_elem pee = new Panier_elem(p.verif(Session.getId()),e.getId(), quantité);
                                ServicePanier_elem spe = new ServicePanier_elem();
                                spe.Insert(pee);

                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Donner une quantité");
                        alert.show();
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
        });
    }

    private void makeDragable() {

        gpfxid.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));
        gpfxid.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        gpfxid.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        gpfxid.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }
    

}
