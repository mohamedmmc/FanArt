/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.test.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ranya
 */
public class ReservationSalleController implements Initializable {

    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button retour2;
    
    @FXML
    private TextField nomclient;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField numtele;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField hour;
    
    @FXML
    private ComboBox<String> artiste;
    
    @FXML
    private Button confirme;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* artiste.getItems().addAll(
            "cat"
            
        );
       */
       
       /*
        try {
            String query="select * from salle";
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery("select *
                String s=rs.getString("numsal);\n" +
"            while (rs.next()) {le");
                System.out.println(s);
            }
                       
        } catch (SQLException ex) {
            Logger.getLogger(ReservationSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
  */
    

        // TODO
    }    

    @FXML
    private void client(ActionEvent event) {
    }

    @FXML
    private void mail(ActionEvent event) {
    }

    @FXML
    private void telephone(ActionEvent event) {
    }

    @FXML
    private void heure(ActionEvent event) {
    }
    
     @FXML
    private void confirmer(ActionEvent event) throws SQLException {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String nom=nomclient.getText();
        String mail=email.getText();
        String tele=numtele.getText();
        String dat=date.getValue().toString();
        String heure=hour.getText();
        String artist=artiste.getSelectionModel().getSelectedItem().toString();
         //System.out.println(dat+" "+artist);

        
        String query ="insert into reservation (nomclient,numtele,email,date,heure,artiste) values (?,?,?,?,?,?)";
        
        ps =con.prepareStatement(query);
        ps.setString(1,nom);
        ps.setString(2,mail);
        ps.setString(3,tele);
        ps.setString(4,dat);
        ps.setString(5,heure);
        ps.setString(6,artist);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"La reservation a bien été ajouté ");
    }
    
    @FXML
    private void annuler(ActionEvent event) {
        nomclient.setText("");
        email.setText("");
        numtele.setText("");
        hour.setText("");
        
}
    
    @FXML
    private void retour(ActionEvent event) {
             try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListeSalles.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch(Exception e) {
              e.printStackTrace();
            }
    }
    
}