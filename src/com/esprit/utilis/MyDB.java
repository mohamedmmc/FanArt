/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author splin
 */
public class MyDB {
    private Connection cnx;
    private static MyDB instance;
    private String url = "jdbc:mysql://localhost:3306/3a18java";
    
    
    public MyDB() {
        try {
            cnx = (Connection) DriverManager.getConnection(url, "root", "");
            
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println("Connexion non établie");
            System.err.println(ex);
        }
        
    }
    public static MyDB getInstance() throws SQLException{
        if ( instance == null)
        {
            instance = new MyDB();
            return instance;
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
