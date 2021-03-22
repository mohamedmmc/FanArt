/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import com.pidev.entity.ListData;
import com.pidev.dao.ParticiperService;
import java.awt.AWTException;
import java.awt.Robot;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class ParticiperEventController implements Initializable {

    @FXML
    private ImageView img_event;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField nb_billet;
    @FXML
    private TextField prix_total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    


    @FXML
    private void getnombrebillet(KeyEvent event) throws AWTException {
        
       int nbr=Integer.parseInt("0"+nb_billet.getText())*ListData.getEvenement().getPrix();
      
       prix_total.setText(String.valueOf(nbr));
    }

    @FXML
    private void valider_participation(MouseEvent event) {
        
            ParticiperService es;
           
            try {
                es = ParticiperService.getInstance();
                
                
                            
                            ListData.getParticiper().setNbr_reservation(Integer.parseInt(nb_billet.getText()));
                           ListData.getParticiper().setPaiement(Integer.parseInt(prix_total.getText()));
                            System.out.println(ListData.getParticiper().toString());
                
                es.insert(ListData.getParticiper());
            } catch (SQLException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Operation validé");
            alert.show();

        }
        
    }

 
    

