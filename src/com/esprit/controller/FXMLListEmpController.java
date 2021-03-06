/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.entity.Employe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class FXMLListEmpController implements Initializable {
    
    @FXML
    private TableView<Employe> table;
    
    @FXML
    private TableColumn<Employe, String> Nom;
    @FXML
    private TableColumn<Employe, String> Prenom;
    @FXML
    private TableColumn<Employe, String> tache;
    @FXML
    private TableColumn<Employe, String> Mobile;
    @FXML
    private TableColumn<Employe, Integer> Salle;
    /**
     * Initializes the controller class.
     */
    
    ObservableList<Employe> list = FXCollections.observableArrayList(
            new Employe("Gouider","Saif","Technicien",2,"27621983"),
            new Employe("Ben Gouta","Monam","Technicien",2,"27055177"),
            new Employe("Zaibi","ahmed","ingenieur",1,"98525362"),
            new Employe("eddine","baha","Technicien",1,"40402121"),
            new Employe("Ben gouta","Naim","architect",5,"26265555")
    );
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Nom.setCellValueFactory(new PropertyValueFactory<Employe,String>("Nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<Employe,String>("Nom"));
        tache.setCellValueFactory(new PropertyValueFactory<Employe,String>("Nom"));
        Mobile.setCellValueFactory(new PropertyValueFactory<Employe,String>("Nom"));
        Salle.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("Nom"));
        
        table.setItems(list);
        
    }    
    
}
