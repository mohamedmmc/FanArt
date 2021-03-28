/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.EventUpdate;
import com.esprit.dao.EvenementService;
import com.esprit.entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class UpdateEventController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextArea descrp;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField prix;
    @FXML
    private Text artist;
    @FXML
    private Text local;
    @FXML
    private Button btn_save;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setText(EventUpdate.liste.get(0).getTitre());
        
        descrp.setText(EventUpdate.liste.get(0).getDescription());
        nbplace.setText(String.valueOf(EventUpdate.liste.get(0).getNombre_place()));
        prix.setText(String.valueOf(EventUpdate.liste.get(0).getPrix()));
        local.setText(EventUpdate.liste.get(0).getLocall());
        
    }    

    @FXML
    private void returnlistEvent(MouseEvent event) {
        
            EvenementService es;
        try {
            Evenement E=new Evenement();
            E.setId_evenement(227);
            E.setTitre(titre.getText());
            E.setDescription(descrp.getText());
            E.setNombre_place(Integer.parseInt(nbplace.getText()));
            E.setPrix(Integer.parseInt(prix.getText()));
            E.setLocall(local.getText());
            LocalDate value = date_debut.getValue();
            LocalDate value1 = date_fin.getValue();
            String s = String.valueOf(value.getMonthValue()) + "/" + String.valueOf(value.getDayOfMonth() + "/" + String.valueOf(value.getYear()));
            String s1 = String.valueOf(value1.getMonthValue()) + "/" + String.valueOf(value1.getDayOfMonth() + "/" + String.valueOf(value1.getYear()));
            
            E.setDate_debut(s);
            E.setDate_Fin(s);
            
            System.out.println(EventUpdate.liste.size());
            System.out.println(EventUpdate.liste.get(0));
            System.out.println(EventUpdate.liste.get(0));
            es = EvenementService.getInstance();
             es.update(EventUpdate.liste.get(0));
            
        } catch (SQLException ex) {
            Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
        }        
               try {
            Parent root= FXMLLoader.load(getClass().getResource("/com/esprit/view/ListEvent.fxml"));
             Stage window =(Stage)btn_save.getScene().getWindow();
             window.setScene(new Scene(root));
             window.setFullScreen(true);
             
        } catch (IOException ex) {
            Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

