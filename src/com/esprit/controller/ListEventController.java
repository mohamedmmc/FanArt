/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.ListData;
import com.esprit.dao.EvenementService;
import com.esprit.entity.Evenement;
import com.esprit.entity.User;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ListEventController implements Initializable {

    @FXML
    private AnchorPane anchorparent;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_addevent;

    /**
     * Initializes the controller class.
     */
    private List<Evenement> getList() throws SQLException {
        List<Evenement> list;
        EvenementService es;
        es = EvenementService.getInstance();
        list = es.ListEvent();
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            // TODO

            List list = new ArrayList<>(getList());

            for (Iterator it = list.iterator(); it.hasNext();) {
                Evenement e = (Evenement) it.next();

              

                VBox testbox = new VBox();
                testbox.setPadding(new Insets(15, 20, 10, 10));
                testbox.setStyle("-fx-background-color: #13dcd3;");

                URL imageURL = getClass().getResource(e.getImage());
               
                Image image = new Image(imageURL.toExternalForm());
                ImageView im = new ImageView(image);
                im.setFitHeight(150);
                im.setPreserveRatio(true);
                //im.setScaleX(0.5d);
                // im.setScaleY(0.5d);

                Text titre = new Text();
                titre.setFill(Color.WHITE);
                Text discription = new Text();
                Button local = new Button();
                Text prix = new Text();
                discription.setFill(Color.WHITE);
                
                prix.setFill(Color.WHITE);
                testbox.setAlignment(Pos.CENTER);
                Button btn = new Button("Participer");
                //*************
                ListData.getUser().setType("artist");

                //***************
                btn.setStyle("-fx-background-radius:10;-fx-font-size:10");
                btn.setId("btn_participer" + e.getId_evenement());
                prix.setText(String.valueOf(e.getPrix() + "DT"));
                discription.setText(e.getDescription());
                local.setText(e.getLocall());
                local.setId(String.valueOf(e.getId_evenement()));
                
                titre.setText(e.getTitre());
                Button btn_supp = new Button("Supprimer");
                //testbox.setVgrow(im, Priority.ALWAYS);
                if (ListData.getUser().getType().toLowerCase().equals("artist")) {

                    btn_supp.setStyle("-fx-background-radius:10;-fx-font-size:10");
                    btn_supp.setId("btn_sup" + e.getId_evenement());
                    testbox.getChildren().addAll(im, titre, discription, local, prix, btn, btn_supp);
                } else {
                    testbox.getChildren().addAll(im, titre, discription, local, prix, btn);
                }

                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e1) {

                        try {
                            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/esprit/view/ParticiperEvent.fxml"));
                            Parent root = (Parent) fxmlloader.load();

                            Stage stage = new Stage();

                            stage.setScene(new Scene(root));
                            stage.show();
                            ListData.setEvenement(e);
                            // Hide this current window (if this is what you want)

                            User u = new User();
                            u.setId(3);
                            ListData.getParticiper().setId_participant(u.getId());
                            ListData.getParticiper().setId_evenement(ListData.getEvenement().getId_evenement());

                        } catch (IOException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                local.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                         try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Localisation.fxml"));
                Stage window = (Stage) local.getScene().getWindow();
                window.setScene(new Scene(root));
                

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
                });
                btn_supp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e1) {

                        try {
                            // Hide this current window (if this is what you want)

                            ListData.setEvenement(e);
                            EvenementService es;
                            es = EvenementService.getInstance();
                            es.delete(ListData.getEvenement());
                            

                        } catch (SQLException ex) {
                            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                if (column == 3) {

                    column = 0;
                    ++row;
                }
                gridpane.add(testbox, column++, row);
                GridPane.setMargin(testbox, new Insets(10));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btn_stat.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Statistique.fxml"));
                Stage window = (Stage) btn_stat.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_addevent.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/HomeEvent.fxml"));
                Stage window = (Stage) btn_addevent.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_acceuil.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Menu.fxml"));
                Stage window = (Stage) btn_acceuil.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
