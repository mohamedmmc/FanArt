/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanart;

import com.esprit.test.Connexion;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ranya
 */
public class ModifierSalleController implements Initializable {
 Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField numsalle;

    @FXML
    private TextField place;

    @FXML
    private Button parcourrir;

    @FXML
    private Button annuler;

    @FXML
    private Button modifier;
    
    @FXML
    private Button retour;
    
    
    @FXML
     void modifiersalle(ActionEvent event) throws SQLException {
         Connection con ;
      Connexion cnx = new Connexion();
      con = cnx.getConnection();
         
         
        String num=numsalle.getText();
        String nbreplace=place.getText();
        String sql="Select idsalle from salle where numsalle = ?";
        String query= "UPDATE salle SET numsalle =?, nbreplace=?";
       
         try{
        ps =con.prepareStatement(query);

        ps.setString(1, num);
        ps.setString(2, nbreplace);
        ps.execute();
        
            JOptionPane.showMessageDialog(null,"bien");
} catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    }
     
      @FXML
            void retour2(ActionEvent event) {
          try {
       Parent page1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
    }    
    
}
