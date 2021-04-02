
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import static com.esprit.dao.Session.pathfile;
import com.esprit.utilis.Connexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ranya
 */
public class AjouterSalleController implements Initializable {
    
    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    String pathimage;
    String filename;
    File source;
    File dest;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField numsalle;

    @FXML
    private TextField place;

    
    @FXML
    private Button return2;
    
    @FXML
    private Button annuler;

    @FXML
    private Button ajout;
    
    @FXML
    private TextArea desc;
    
    @FXML
    private ImageView imageviewxid;
    
    @FXML
    private Button upload;
    
    @FXML
    private ImageView imageviewfxid;
    
   public String verifier(){
    if (numsalle.getText().equals("")|| place.getText().equals("") || desc.getText().equals("") )
       return "true";
    else 
        return "false";
    }   

    @FXML
    void Upload(ActionEvent event) {
                FileChooser f = new FileChooser();
        String img;
        
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            System.out.println(img);
            //System.out.println(fc.getAbsolutePath());
            Image i = new Image(img);
            imageviewfxid.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            
            //source = new File(pathimage);
            
            //dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
            Session.filename="localhost:8080/img/" + filename;
            //su.sendphp(fc.getAbsolutePath());
        }
//        imageviewfxid.setFitHeight(94);
//        imageviewfxid.setFitWidth(94);
        //..\img\google.png
        //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
        pathfile = fc.getAbsolutePath();

    }
   
    @FXML
    void add(ActionEvent event) throws SQLException, IOException {
        ServiceUser su = new ServiceUser();
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String num=numsalle.getText();
        String nbreplace=place.getText();
        String descri=desc.getText();
        
        
        String query ="insert into salle (numsalle,nbreplace,description,image) values (?,?,?,?)";
        
        if (verifier()=="false") { 
        try {
        ps =con.prepareStatement(query);
        ps.setString(1, num);
        ps.setString(2,nbreplace);
        ps.setString(3, descri);
        ps.setString(4,Session.filename);

        ps.execute();
        su.sendphp(pathfile);
        JOptionPane.showMessageDialog(null,"La salle a bien été ajouté ,vous pouvez en mettre un autre.. ");
        numsalle.setText("");
        place.setText("");
        desc.setText("");
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
         Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Welcome.fxml"));
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
