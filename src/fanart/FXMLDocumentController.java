/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanart;

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
public class FXMLDocumentController implements Initializable {
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
      
      
// ------------------------- Supprimer salle ----------------------------------------------
    /*  @FXML
                
     void suppsalle(ActionEvent event) throws SQLException {
         Connection con ;
      Connexion cnx = new Connexion();
      con = cnx.getConnection();
      String val = (tab.getModel().getValueAt(row, 0).toString());
      int d=Integer.parseInt(val);
        
      String query = "DELETE FROM `salle` WHERE  idsalle = ?";
        try {
        ps = con.prepareStatement(query);
            ps.setString(1, val);
            ps.execute();
        
       
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }

     */
    
      //-------------------------------- Ajouter salle-----------------------------------------------------  
         @FXML
        void addsalle(ActionEvent event) {
          try {
  Parent page1 = FXMLLoader.load(getClass().getResource("AjouterSalle.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show(); 
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
// ------------------------------------Modifier salle -----------------------------------------------------
    @FXML
            void modifiersalle(ActionEvent event) {
          try {
       Parent page1 = FXMLLoader.load(getClass().getResource("ModifierSalle.fxml"));
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
             while (rs.next())
             {
                 ob.add(new ModelTable(rs.getInt("idsalle"), rs.getString("numsalle"), rs.getString("nbreplace"),rs.getString("disponibilite"),rs.getString("date")));
             }
         } catch (Exception ex) {
             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
