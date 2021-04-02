/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceEmp;
import com.esprit.entity.Employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author saif
 */
class ListData {
    
    private ObservableList<Employe> employees=FXCollections.observableArrayList();

    public ListData() {
        
        ServiceEmp pdao= new ServiceEmp();
        employees= pdao.displayAll();
        System.out.println(employees);
    }
    
    public ObservableList<Employe> getEmploye(){
        return employees;
    }
    
}
