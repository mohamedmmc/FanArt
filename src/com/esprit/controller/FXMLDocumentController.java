/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.controller.FXMLMenuController;
import com.esprit.dao.ServiceEmp;
import com.esprit.entity.Employe;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button cancel_button;
    @FXML
    private Button btn_ajouter_emp;
    @FXML
    private Label job_label;
    @FXML
    private Label ins_tache;
    @FXML
    private ComboBox combo;
    @FXML
    private TextField ins_nom;
    @FXML
    private TextField ins_prenom;
    @FXML
    private TextField ins_age;
    @FXML
    private TextField ins_mobile;
    @FXML
    private TextField ins_num_carte;
    @FXML
    private TextField ins_salaire;
    
    
    public Boolean verifier(){
        if(ins_nom.getText().equals("")||
                ins_prenom.getText().equals("")||
                ins_age.getText().equals("")||
                ins_salaire.getText().equals("")||
                ins_mobile.getText().equals("")||
                ins_mobile.getText().length()>8||
                ins_num_carte.getText().length()>16||
                ins_num_carte.getText().equals("")
                )
            return true;
        else 
            return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Technicien","Ingenieur","Designer","Architecte");
        combo.setItems(list);
        
        // TODO
        btn_ajouter_emp.setOnAction(event -> {
            if(verifier()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Merci de vérifier vos informations de saisie");
        alert.show();
            }else{
                Employe p = new Employe(
                    ins_nom.getText(),
                    ins_prenom.getText(),
                    job_label.getText(),                 
                    Integer.parseInt(ins_age.getText()),
                    Integer.parseInt(ins_mobile.getText()),
                    Float.parseFloat(ins_salaire.getText()),
                    ins_num_carte.getText(),0);
            
            ServiceEmp pdao= new ServiceEmp();
            //System.out.println(p.toString());
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        ins_nom.setText("");
        ins_prenom.setText("");
        ins_mobile.setText("");
        ins_prenom.setText("");
        job_label.setText("job");
        ins_salaire.setText("");
        ins_num_carte.setText("");
            }
            
        });
        
        
         cancel_button.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLMenu.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void Select(ActionEvent event) {
        String s = combo.getSelectionModel().getSelectedItem().toString();
        job_label.setText(s);
    }
    
    
    
}
