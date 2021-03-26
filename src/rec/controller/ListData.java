/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rec.entity.recprod;
import rec.entity.recevent;
import rec.dao.recedao;
import rec.dao.recpdao;

/**
 *
 * @author ASUS GL703VD
 */
public class ListData {
     private ObservableList<recevent> even=FXCollections.observableArrayList();

    public ListData() {
        
        recedao pdao=recedao.getInstance();
        even= pdao.displayAll();
        System.out.println(even);
    }
    
    public ObservableList<recevent> getevs(){
        return even;
    }
    
}
