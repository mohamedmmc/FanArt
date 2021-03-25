/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import rec.dao.recpdao;

/**
 * FXML Controller class
 *
 * @author ASUS GL703VD
 */
public class AjoutRecController implements Initializable {

    @FXML
    private ComboBox<?> receve;
    @FXML
    private TextArea Rectxt1;
    @FXML
    private Button recadd1;
    @FXML
    private ComboBox<String> Recprod;
    @FXML
    private TextArea Rectxt;
    @FXML
    private Button recadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
    private static recpdao instance;
    private Statement st;
    private ResultSet rs;
    
    public void updatecombo (){
    String req = "select * from produit";
    try {
        st.executeUpdate(req);
        rs = st.executeQuery(req);
        while (rs.next())
        {
           Recprod.getItems().add(rs.getString("nom"));
        }
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
}}
