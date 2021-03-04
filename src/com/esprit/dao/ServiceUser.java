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
    public String decrypt(String mdp){
        char [] chars = mdp.toCharArray();
        String decrypted = "";
        for (char c : chars){
            c -=25;
            decrypted +=c;
        }
        
        
        return decrypted;
    }
    public String encrypt(String mdp){
        char [] chars = mdp.toCharArray();
        String encrypted = "";
        for (char c : chars){
            c +=25;
            encrypted +=c;
            System.out.println(c);
        }
        System.out.println(encrypted);
        
        return encrypted;
    }

    public void insert(User u) {
        String a = u.getMdp();
        String encrypted = encrypt(a);
        String req = "insert into user (nom,prenom,mdp,email,numtel) values ('" + u.getNom() + "','" + u.getPrenom() + "','" + encrypted + "','" + u.getEmail() + "','" + u.getNumtel() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verify(String email, String mdp) throws SQLException {
        String req = "select email,mdp from user where email='" + email + "'";
        ResultSet rs = ste.executeQuery(req);
        String decrypted;
        boolean mailtrue = false, mdptrue = false;
        while (rs.next()) {

            if (email.equals(rs.getString("email"))) {
                mailtrue = true;
                decrypted = decrypt(rs.getString("mdp"));
                if (mdp.equals(decrypted)) {
                    mdptrue = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie");
                    alert.show();
                }
            }
        }
        if (mailtrue == false || mdptrue == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe ou login incorrect");
            alert.show();
        }
    }
}
