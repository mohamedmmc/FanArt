
package com.esprit.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class InfoSalleController implements Initializable {

    @FXML
    private TextField numsalle;
    
    @FXML
    private TextField nbreplace;
    
    @FXML
    private TextArea desc;
    
    @FXML
    private Button retour;

    @FXML
    private ImageView imageviewfxid;
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }   
    
    public void initData(ModelTable2 tab)
    {
        System.out.println(tab.getNumsalle());
    }

    @FXML
    private void numerosalle(ActionEvent event) {
    }

    @FXML
    private void place(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
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
    
    public void ShowInformation(String a, String b, String c,String p) throws FileNotFoundException
    {
        numsalle.setText(a);
        nbreplace.setText(b);
        desc.setText(c);
        
        numsalle.setDisable(true);
        nbreplace.setDisable(true);
        desc.setDisable(true);
        
    InputStream stream = new FileInputStream(p);
    Image image = new Image(stream);
      //Creating the image view
      //Setting image to the image view
    imageviewfxid.setImage(image);
    }
    
}
