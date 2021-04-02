/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.recedao;
import com.esprit.dao.recpdao;

        
import com.esprit.entity.recevent;        
import com.esprit.entity.recprod;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author ASUS GL703VD
 */
public class ListP {
    private ObservableList<recprod> persons=FXCollections.observableArrayList();
    

    public ListP() {
        
        recpdao pdao=recpdao.getInstance();
        persons= pdao.displayAll();
        System.out.println(persons);
    }
    
    public ObservableList<recprod> getPersons(){
        
        return persons;
    }
    
    public ObservableList<recprod> clr() {
        persons.clear();
        recpdao pdao=recpdao.getInstance();
        persons= pdao.displayAll();
        System.out.println(persons);
        return persons;
    }
    
}
