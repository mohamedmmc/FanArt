/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.utils;

import com.pidev.dao.EvenementService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monam Ben Gouta
 */
public class MyDb {
    
    private String url="jdbc:mysql://127.0.0.1:3306/piart";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyDb instance;

    

    public Connection getCnx() {
        return cnx;
    }
    
    
    private MyDb() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static MyDb getInstance(){
       
       if(instance==null)
           instance=new MyDb();
       return instance;
   }
    
    
    
    
}
