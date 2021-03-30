/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.dao.ServiceProduit;
import com.esprit.entity.Produit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author Juka
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Produit> produit=FXCollections.observableArrayList();

    public ListData() {
        
        ServiceProduit pdao=ServiceProduit.getInstance();
        produit= pdao.displayAll();
        System.out.println(produit);
    }
    
    public ObservableList<Produit> getProduit(){
        return produit;
    }
   
}
