/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.ModelTable2;
import java.net.URL;
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void ShowInformation(String a, String b, String c)
    {
        numsalle.setText(a);
        nbreplace.setText(b);
        desc.setText(c);
        
        numsalle.setDisable(true);
        nbreplace.setDisable(true);
        desc.setDisable(true);

    }
    
}
