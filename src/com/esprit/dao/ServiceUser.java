/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.User;
import com.esprit.utilis.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author splin
 */
public class ServiceUser {

    Connection cnx;
    Statement ste;

    public ServiceUser() throws SQLException {
        cnx = MyDB.getInstance().getCnx();
        ste = (Statement) cnx.createStatement();
    }

    public void insert(User u) {
        String req = "insert into user (nom,prenom,mdp,email,numtel) values ('" + u.getNom() + "','" + u.getPrenom() + "','" + u.getMdp() + "','" + u.getEmail() + "','" + u.getNumtel() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verify(String email, String mdp) throws SQLException {
        String req = "select email,mdp from user where email='" + email + "' and mdp='" + mdp + "'";
        ResultSet rs = ste.executeQuery(req);
        Boolean bmail = false, bmdp = false;
        while (rs.next() && bmail==false) {
            if (email.equals(rs.getString("email")) && mdp.equals(rs.getString("mdp"))) {
                System.out.println("réussie");
            }
            else{
                System.out.println("nope");
            }
        }
        
    }

}
