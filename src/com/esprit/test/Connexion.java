/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ranya
 */
public class Connexion {
        Connection con;

    public Connexion (){

    try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver installe");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/fanart","root","");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("erreur connection"+e.getMessage());
	}

}
     public Connection getConnection()
        {
        return con;
        }
}
    

