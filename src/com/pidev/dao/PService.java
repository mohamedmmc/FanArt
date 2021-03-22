/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.dao;

import com.pidev.entity.Participer;
import com.pidev.entity.Evenement;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Ben Gouta Monam
 * @param <T>
 */
public interface PService <T>{
     public void insert(T o) throws SQLException ;
    public void delete(T o) throws SQLException;
    public ObservableList<Participer> displayAll() throws SQLException;
    public T displayById(int id) throws SQLException;
    
 
    
}
