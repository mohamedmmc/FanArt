
package com.esprit.controller;

import com.esprit.utilis.Connexion;
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
 * @author ranya
 */
public class WelcomeController implements Initializable {

    
    @FXML
    private Button ajoutsalle;

    @FXML
    private Button suppsalle;

    @FXML
    private TableView<ModelTable2> tab;

    @FXML
    private TableColumn<ModelTable2, ?> idsalle;

    @FXML
    private TableColumn<ModelTable2, ?> numsalle;

    @FXML
    private TableColumn<ModelTable2, ?> nbreplace;

    @FXML
    private TableColumn<ModelTable2, ?> desc;

    ObservableList<ModelTable2> ob =FXCollections.observableArrayList();
    
    
//------------------------- Bouton Ajouter ----------------------------------------------------------------------------------
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
    
//-------------------------------------- Bouton Supprimer ----------------------------------------------------------------------------------
 
    @FXML
    void delete(ActionEvent event) throws SQLException  {
        
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();

        ModelTable2 song = tab.getSelectionModel().getSelectedItem();
        System.out.println(song);

        if (song != null) {
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

//------------------------------------------ Bouton Modifier ----------------------------------------------------------------------------------

    @FXML
    void modifiersalle(ActionEvent event) throws SQLException{
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        int id;
        String s="",nbr="",desc="";
        ModelTable2 song = tab.getSelectionModel().getSelectedItem();
       
        ResultSet rs;
        PreparedStatement ps = con.prepareStatement("select * FROM salle  WHERE numsalle="+song.getNumsalle() );
        
        
        rs=ps.executeQuery();
        while(rs.next())
        {
        id=rs.getInt("idsalle");
        s=rs.getString("numsalle");
        nbr = rs.getString("nbreplace");
        desc = rs.getString("description");
        }
         
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/ModifierSalle.fxml"));
        Parent root =loader.load();
        ModifierSalleController info = loader.getController();
        info.ShowInformation(s, nbr, desc);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        stage.show();
        } catch(Exception e) {
        e.printStackTrace();
        }
    }
//------------------------- Bouton reservation ----------------------------------------------------------------------------------
 
    @FXML
    void reservation(ActionEvent event) {
       try {
       Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListSalle.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch(Exception e) {
             e.printStackTrace();
          }
    }
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
             
        ResultSet rs=con.createStatement().executeQuery("select * from salle");
        while (rs.next())  {
            ob.add(new ModelTable2(rs.getInt("idsalle"), rs.getString("numsalle"), rs.getString("nbreplace"),rs.getString("description")));
            }
        
        } catch (Exception ex) {
        Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            idsalle.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
            numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
            nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
         

            tab.setItems(ob);
        // TODO
         }    
        // TODO
    
    
}
