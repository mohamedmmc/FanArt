
package com.esprit.controller;

import com.esprit.test.Connexion;
import com.esprit.controller.ModelTable2;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ranya
 */
public class ListeSallesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button reserver;

    @FXML
    private Button info;
    
    @FXML
    private TableColumn<ModelTable2, ?> numsalle;
    
    @FXML
    private TableColumn<ModelTable2, ?> nbreplace;
    
    @FXML
    private TableColumn<ModelTable2, ?> dispo;
    
    @FXML
    private TableView<ModelTable2> tab;
    
    ObservableList<ModelTable2> ob2 =FXCollections.observableArrayList();
         
     @FXML
     void information(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/InfoSalle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
             } catch(Exception e) {
              e.printStackTrace();
             }
    }
    
    
    @FXML
    void reservation(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ReservationSalle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
             } catch(Exception e) {
              e.printStackTrace();
             }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
         Connection con ;
         Connexion cnx = new Connexion();
         con = cnx.getConnection();
             
         ResultSet rs=con.createStatement().executeQuery("select * from salle");
          while (rs.next())  {
              ob2.add(new ModelTable2(rs.getString("numsalle"), rs.getString("nbreplace"),rs.getString("disponibilite")));
             }
             } catch (Exception ex) {
             Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
             }
         
            numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
            nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
            
            tab.setItems(ob2);
    }    
    
}
