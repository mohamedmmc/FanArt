
package com.esprit.controller;


import com.esprit.utilis.Connexion;
import java.io.FileInputStream;
import java.io.InputStream;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class AjouterSalleController implements Initializable {
    
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
    private Button return2;
    
    @FXML
    private Button annuler;

    @FXML
    private Button ajout;
    
    @FXML
    private TextArea desc;
    
   public String verifier(){
    if (numsalle.getText().equals("")|| place.getText().equals("") || desc.getText().equals("") )
       return "true";

    else 
         return "false";
} 
    @FXML
    void add(ActionEvent event) throws SQLException {
        
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String num=numsalle.getText();
        String nbreplace=place.getText();
        String descri=desc.getText();
        
        
        String query ="insert into salle (numsalle,nbreplace,description) values (?,?,?)";
        
        if (verifier()=="false") { 
        try {
        ps =con.prepareStatement(query);
        ps.setString(1, num);
        ps.setString(2,nbreplace);
        ps.setString(3, descri);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"La salle a bien été ajouté ,vous pouvez en mettre un autre.. ");
    
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
        }
    
        } else{
     ImageIcon img1 = new ImageIcon("C:\\Users\\ranya\\Desktop\\attention.png");
    JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs ! ", "Information", JOptionPane.INFORMATION_MESSAGE, img1);
}

    }
       
    
      @FXML
      void Annuler(ActionEvent event) throws SQLException {
           
        numsalle.setText("");
        place.setText("");
        desc.setText("");
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
      
      
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
    }    
    
}
