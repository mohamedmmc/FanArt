

package com.esprit.controller;

import com.esprit.utilis.Connexion;
import com.esprit.entity.ModelTable2;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author splin
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
    
         
//----------------------------------- Bouton plus information pour la salle -------------------------------------------------------------           
    @FXML
    void information(ActionEvent event) throws SQLException, IOException {
        
       Connection con ;
       Connexion cnx = new Connexion();
       con = cnx.getConnection();

        String s="",nbr="",desc="";
        ModelTable2 song = tab.getSelectionModel().getSelectedItem();
        System.out.println(song);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement("select * FROM salle  WHERE numsalle="+song.getNumsalle() );

         rs=ps.executeQuery();
         while(rs.next()){
            
         s=rs.getString("numsalle");
         nbr = rs.getString("nbreplace");
         desc = rs.getString("description");
         }
         Parent root;
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/InfoSalle.fxml"));
          root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
           Scene scene = new Scene(root);
           InfoSalleController info = loader.getController();
           info.ShowInformation(s, nbr, desc);
           stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            
        
            
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/InfoSalle.fxml"));
//        Parent root =loader.load();
//        InfoSalleController info = loader.getController();
//        info.ShowInformation(s, nbr, desc);
//       
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        stage.setScene(new Scene(root));
//        stage.show();
//        
       
    }
    
//----------------------------Bouton reservation salle -----------------------------------------------------------------------------------
    
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
        //Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
            nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
            
            tab.setItems(ob2);
    }    
    
}
