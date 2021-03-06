
package com.esprit.controller;

import com.esprit.controller.ModelTable;
import com.esprit.test.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ranya
 */

public class AcceuilController implements Initializable {
    
    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
  
    @FXML
    private Label label;

    @FXML
    private TableView<ModelTable> tab;
    
    @FXML
    private TableColumn<ModelTable, ?> idsalle;

    @FXML
    private TableColumn<ModelTable, ?> numsalle;

    @FXML
    private TableColumn<ModelTable, ?> nbreplace;

    @FXML
    private TableColumn<ModelTable, ?> dispo;

    @FXML
    private TableColumn<ModelTable, ?> date;

    @FXML
    private Button ajoutsalle;

    @FXML
    private Button suppsalle;

    ObservableList<ModelTable> ob =FXCollections.observableArrayList();
 
      
      
// ------------------------------------------Bouton supprimer salle ----------------------------------------------------------------              
    
     @FXML
     void delete(ActionEvent event) throws SQLException {
             
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();

        ModelTable song = tab.getSelectionModel().getSelectedItem();
        System.out.println(song);

        if (song != null) {
    // there is a selection -> delete
    
        int n = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'élément ? " , "SVP select", JOptionPane.YES_NO_OPTION);
        if (n==0) { 
            
        PreparedStatement statement = con.prepareStatement("DELETE FROM salle WHERE idsalle = ?");
        tab.getItems().remove(song);
        statement.setInt(1, song.getIdsalle());
        statement.executeUpdate();
            }
        }  else
        JOptionPane.showMessageDialog(null, "Vous devez sélecionner une ligne ! ");
  }
    
    
//------------------------------------------- Bouton ajouter salle-----------------------------------------------------  
         
    @FXML
     void addsalle(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/AjouterSalle.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
        stage.show();
        } catch(Exception e) {
        e.printStackTrace();
         }
    }
        
        
// -------------------------------------------------Bouton modifier salle ----------------------------------------------------------
    @FXML
    void modifiersalle(ActionEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ModifierSalle.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        } catch(Exception e) {
         e.printStackTrace();
        } 
    }
            
    private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked me!");
    label.setText("Hello World!");
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
         Connection con ;
         Connexion cnx = new Connexion();
         con = cnx.getConnection();
             
         ResultSet rs=con.createStatement().executeQuery("select * from salle");
          while (rs.next())  {
              ob.add(new ModelTable(rs.getInt("idsalle"), rs.getString("numsalle"), rs.getString("nbreplace"),rs.getString("disponibilite"),rs.getString("date")));
             }
             } catch (Exception ex) {
             Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
            idsalle.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
            numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
            nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));

            tab.setItems(ob);
        // TODO
         }    
    
}
