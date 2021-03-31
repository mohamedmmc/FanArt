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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        int prixtotal=0;
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
            Image imageBack=new Image("http://localhost:80/img/4813762.jpg");
            for (Iterator it = list.iterator(); it.hasNext();) {
            Panier_elem panier_elem = (Panier_elem) it.next();
            ServiceProduit serviceproduit =new ServiceProduit();
            Produit produit =new Produit();
            produit = serviceproduit.displayById(panier_elem.getId_produit()); 
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("/com/esprit/view/FXMLpanier.fxml"));
            VBox testbox = new VBox();
            testbox.setPadding(new Insets(30, 20, 30, 30));
            testbox.setStyle("-fx-border-style: dotted;-fx-alignment:center;-fx-border-color: #ff9900;-fx-border-width: 5px;");
            testbox.setBackground(new Background(new BackgroundImage(imageBack,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
            testbox.setSpacing(10);
            String imageURL = produit.getImage();
            Image image = new Image("http://"+imageURL);
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
            Text quantite = new Text();
            quantite.setFill(Color.WHITE);
            titre.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            description.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            artiste.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            prix.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            quantite.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            titre.setStrokeWidth(1);
            titre.setStroke(Color.YELLOW);
            description.setStrokeWidth(1);
            description.setStroke(Color.YELLOW);
            artiste.setStrokeWidth(1);
            artiste.setStroke(Color.YELLOW);
            prix.setStrokeWidth(1);
            prix.setStroke(Color.YELLOW);
            prix.setStrokeWidth(1);
            quantite.setStroke(Color.YELLOW);
            prix.setText("Prix: "+String.valueOf(produit.getPrix() + "DT"));
            description.setText("Description: "+produit.getDescription());
            User user = new User();
            ServiceUser serviceuser =new ServiceUser();
            user=serviceuser.findBymail(produit.getArtiste());
            artiste.setText("Artsite: "+String.valueOf(user.getNom()+" "+user.getPrenom()));
            titre.setText("Titre: "+produit.getTitre());
            quantite.setText("Quantite: "+String.valueOf(panier_elem.getQuantite()));
            Button btn = new Button("Modifier la quantité");
            btn.setStyle( "-fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;-fx-font: 22 verdana" );
            btn.setMinWidth( 200 );
            btn.setId("btn_aap" + produit.getId());
            Button btn1 = new Button("Supprimer ce produit");
            btn1.setStyle( "-fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;-fx-font: 22 verdana" );
            btn1.setMinWidth( 200 );
            btn1.setId("btn_aap" + produit.getId());
            testbox.getChildren().addAll(im, titre, description, artiste, prix,quantite,btn,btn1);
            int x =produit.getId();
            btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e1) {
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setHeaderText("sélectionner la nouveau quantite");
                            dialog.setContentText("Quantité : ");
                            
                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()) {
                                String test = result.get();
                                int quantité = Integer.parseInt(test);
                                if(quantité>0){
                                    ServicePanier_elem pe = new ServicePanier_elem();
                                    pe.modifQuantitepanier(quantité,x,idpanier);
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation");
                                    alert.setHeaderText(null);
                                    alert.setContentText("modification effectuée avec succès");
                                    alert.show(); 
                                    quantite.setText("Quantite: "+String.valueOf(quantité));
                                }else{
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Quantite "+test+" !!");
                                alert.show(); 
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Donner une quantité");
                                alert.show();
                            }
                        }
                    });
            btn1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e1) {
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
                                try {
                                    spe.delete(idpanier,x);
                                } catch (SQLException ex) {
                                    System.out.println("idpanier"+idpanier);
                                    System.out.println("idproduit"+x);
                                    System.out.println("le probleme ici)");
                                }
                                } else if (result.get() == buttonTypeTwo) {
                                 Alert alert1 = new Alert(AlertType.CONFIRMATION);
                                 alert.setTitle("Annulé");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Suppression annuler");
                        }
                            }
                    });
            if (column == 2) {

                column = 0;
                ++row;
            }
            gpfxid.add(testbox, column++, row);
            GridPane.setMargin(testbox, new Insets(10));
            prixtotal+=panier_elem.getQuantite()*produit.getPrix();
        }
            Session.setPrix_total_prduit(prixtotal);
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
    private void payer(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/esprit/view/paymentproduit.fxml"));
                            Parent root = (Parent) fxmlloader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
}
