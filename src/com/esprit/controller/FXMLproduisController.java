/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.Panier;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import com.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
 * @author splin
 */
public class FXMLproduisController implements Initializable {

    @FXML
    private ScrollPane spfxid;
    @FXML
    private GridPane gpfxid;
    @FXML
    private AnchorPane apfxid;
    double x, y;
    @FXML
    private Button ajouter_produit;
    @FXML
    private Button mes_produit;

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
        try {
            ServiceUser serviceuser =new ServiceUser();
            if(!serviceuser.verifytype(Session.getId()))
            {ajouter_produit.setVisible(false);
            mes_produit.setVisible(false);}
            Platform.runLater(() -> {
                makeDragable();
                int column = 0;
                int row = 1;
                spfxid.setFitToHeight(true);
                spfxid.setFitToWidth(true);
                List list = new ArrayList<>(getList());
                Image imageBack=new Image("http://localhost:80/img/4813762.jpg");
                for (Iterator it = list.iterator(); it.hasNext();) {
                    Produit e = (Produit) it.next();
                    VBox testbox = new VBox();
                    testbox.setPrefSize(600, 600);
                    testbox.setPadding(new Insets(15, 20, 10, 10));
                    testbox.setStyle("-fx-border-style: dotted;-fx-alignment:center;-fx-border-color: #ff9900;-fx-border-width: 5px;");
                    testbox.setBackground(new Background(new BackgroundImage(imageBack,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
                    testbox.setSpacing(10);
                    String imageURL = e.getImage();
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
                    titre.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    description.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    artiste.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    prix.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                    titre.setStrokeWidth(1);
                    titre.setStroke(Color.YELLOW);
                    description.setStrokeWidth(1);
                    description.setStroke(Color.YELLOW);
                    artiste.setStrokeWidth(1);
                    artiste.setStroke(Color.YELLOW);
                    prix.setStrokeWidth(1);
                    prix.setStroke(Color.YELLOW);
                    Button btn = new Button("Ajouter au panier");
                    btn.setStyle( "-fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5;-fx-font: 22 verdana" );
                    btn.setMinWidth( 200 );
                    btn.setId("btn_aap" + e.getId());
                    prix.setText("Prix: "+String.valueOf(e.getPrix() + "DT"));
                    description.setText(e.getDescription());
                    User user = new User();
                    ServiceUser serviceuser1 =new ServiceUser();
                    user=serviceuser1.findBymail(e.getArtiste());
                    artiste.setText("Artsite: "+String.valueOf(user.getNom()+" "+user.getPrenom()));
                    titre.setText("Titre: "+e.getTitre());
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
                    if (column == 2) {
                        
                        column = 0;
                        ++row;
                    }
                    gpfxid.add(testbox, column++, row);
                    GridPane.setMargin(testbox, new Insets(10));
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLproduisController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLproduisController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void Ajouter_produit(ActionEvent event) throws IOException {
         FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/esprit/view/FXMLAffichageProduit.fxml"));
                            Parent root = (Parent) fxmlloader.load();

                            Stage stage = new Stage();

                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void Mes_produit(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/esprit/view/produitartiste.fxml"));
                            Parent root = (Parent) fxmlloader.load();

                            Stage stage = new Stage();

                            stage.setScene(new Scene(root));
                            stage.show();
    }
    

}
