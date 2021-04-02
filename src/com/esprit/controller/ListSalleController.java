
package com.esprit.controller;

import com.esprit.utilis.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class ListSalleController implements Initializable {

    @FXML
    private Button reserver;

    @FXML
    private Button info;
    
    @FXML
    private Button retour;
    
    @FXML
    private TableColumn<ModelTable3, ?> numsalle;

    @FXML
    private TableColumn<ModelTable3, ?> nbreplace;

    @FXML
    private TableColumn<ModelTable3, ?> datedebut;
    
    @FXML
    private TableColumn<ModelTable3, ?> datefin;
      
    @FXML
    private TableColumn<ModelTable3, ?> description;
      
    @FXML
    private TableView<ModelTable3> table;
    
    ObservableList<ModelTable3> ob3 =FXCollections.observableArrayList();
                    
                    
    @FXML
    void information(ActionEvent event) throws SQLException, IOException {
        
       Connection con ;
       Connexion cnx = new Connexion();
       con = cnx.getConnection();

        String num="", nbr="",desc="",path="";
        ModelTable3 song = table.getSelectionModel().getSelectedItem();
        System.out.println(song);
        ResultSet rs;

        PreparedStatement ps = con.prepareStatement("select * FROM salle  WHERE numsalle="+song.getNumsalle() );

        rs=ps.executeQuery();
        while(rs.next()){
        num = rs.getString("numsalle");
        nbr = rs.getString("nbreplace");
        desc = rs.getString("description");
        path= rs.getString("image");
        }
         
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/InfoSalle.fxml"));
        Parent root =loader.load();
        InfoSalleController info = loader.getController();
        info.ShowInformation(num, nbr, desc,path);
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(new Scene(root));
        stage.show();
        } catch(Exception e) {
        e.printStackTrace();
        } 
    }
   
//------------------------------------Bouton retour ---------------------------------------------------------
    @FXML
    void retour3(ActionEvent event) {

        try {
         Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Welcome.fxml"));
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

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
    displayData();
        // TODO
    }    
    
    public void displayData()
    {
        try {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
             
        ResultSet rs=con.createStatement().executeQuery("select numsalle,nbreplace,date_debut,date_fin,s.description from salle s ,evenement e where s.idsalle in (select e.locall from evenement)");
        while (rs.next())  {
        ob3.add(new ModelTable3(rs.getInt("numsalle"), rs.getString("nbreplace"), rs.getString("date_debut"),rs.getString("date_fin"),rs.getString("description")));                           
        }
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);        }
         
          numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
          nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
          datedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
          datefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

          table.setItems(ob3);
        // TODO
         
    }
}
