/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<User> userTab;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> numerotel;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private ImageView image;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label numerolabel;
    @FXML
    private Label typelabel;
    @FXML
    private ImageView cross;
    
    
    String imagepath,emaildel;
    @FXML
    private AnchorPane parent;
    double x=0,y=0;
    @FXML
    private Label path;

    /**
     * Initializes the controller class.
     */

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
        ServiceUser su = new ServiceUser();
        
        userTab.setOnMouseClicked((event) -> {
            nomlabel.setText(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                    .getNom());
            prenomlabel.setText(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                    .getPrenom());
            emaillabel.setText(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                    .getEmail());
            typelabel.setText(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                   .getType());
            numerolabel.setText(String.valueOf(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                    .getNumtel()));
            path.setText(String.valueOf(su.displayAllList()
                    .get(userTab.getSelectionModel().getSelectedIndex())
                    .getPhoto()));
            
            
                    
        });
        try {
            // TODO

            ObservableList<User> list = su.getUserList();
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            numerotel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));

            userTab.setItems(list);
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }
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
    private void retour(ActionEvent event) {
        try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public String toString() {
        return "AdminController{" + "emaillabel=" + emaillabel + '}';
    }

    @FXML
    private void supprimer(ActionEvent event) {
        /*ServiceUser su = new ServiceUser();
        try {
            su.Deluser(emaillabel.toString()); 
             System.out.println(emaillabel.toString());
        } catch (Exception e) {
            System.out.println(false);
        }*/
        
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
}
