
package com.esprit.controller;

import com.esprit.utilis.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */

public class ModifierSalleController implements Initializable {
    
    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    
    @FXML
    private TextField numsalle;

    @FXML
    private TextField place;
    
    @FXML
    private TextArea desc;

    @FXML
    private Button parcourrir;

    @FXML
    private Button annuler;

    @FXML
    private Button modifier;
    
    @FXML
    private Button retour;
    
    String num;
       
    @FXML
    void modifiersalle(ActionEvent event) throws SQLException {
       
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
         
        String a=numsalle.getText();
        String nbrplace=place.getText();
        String descr=desc.getText();

        String query= "UPDATE salle SET numsalle=?, nbreplace=?,description=? where numsalle="+num;
        
        try{
        ps =con.prepareStatement(query);
        ps.setString(1, a);
        ps.setString(2, nbrplace);
        ps.setString(3, descr);

        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null,"La mise a jour a été bien effectuée ..");
        
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
        }
    }
     
    @FXML
    void retour2(ActionEvent event) {
       
       try {
       Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch(Exception e) {
             e.printStackTrace();
          }
       }
                
    @FXML
    void Annuler2(ActionEvent event) throws SQLException {
           
        numsalle.setText("");
        place.setText("");
        desc.setText("");
    }
    
    public void ShowInformation(String a, String b, String c)  {
        
        numsalle.setText(a);
        place.setText(b);
        desc.setText(c);

        num=a;
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
