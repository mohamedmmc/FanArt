/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.User;
import com.esprit.utilis.MyDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author splin
 */
public class ServiceUser {

    Connection cnx;
    Statement ste;
    ResultSet rs;

    public ServiceUser()  {
        try {
            cnx = MyDB.getInstance().getCnx();
        ste = (Statement) cnx.createStatement();
        } catch (SQLException e) {
            
        }
        
        
        
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
            //System.out.println(c);
        }
        //System.out.println(encrypted);
        
        return encrypted;
    }

    public void insert(User u) throws FileNotFoundException, SQLException {
        String a = u.getMdp();
        //System.out.println(u.getPhoto());
        String encrypted = encrypt(a);
        String req = "insert into user (nom,prenom,mdp,email,numtel,photo,type) values ('" + u.getNom() + "','" + u.getPrenom() + "','" + encrypted + "','" + u.getEmail() + "','" + u.getNumtel() + "','"+u.getPhoto()+"','"+u.getType()+"')";
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
    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> eventsList = FXCollections.observableArrayList();
        Statement stm = cnx.createStatement();
        String query = "select nom, prenom, email, numtel, photo,type from user";
        //ResultSet rs;
        rs = stm.executeQuery(query);
        User user;
        while (rs.next()) {
            user = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"), rs.getString("photo"), rs.getString("type"));
            eventsList.add(user);

        }
        return eventsList;

    }
    public Image affichage(String image) throws SQLException{
        Image pic = null;
        Statement stm = cnx.createStatement();
        String query ="select photo from user where photo='"+image+"')";
        try {
            ste.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
        
    }
    public List<User> displayAllList() {
        String req="select nom, prenom, email, numtel, photo,type from user";
        List<User> list=new ArrayList<>();
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                User p=new User();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                p.setNumtel(rs.getInt("numtel"));
                p.setPrenom(rs.getString("photo"));
                p.setType(rs.getString("type"));
                list.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void Deluser(User u) {
       try {
            Statement stm=cnx.createStatement();
            String query="delete from user where email = '"+u.getEmail()+"'";
            println(query);
            stm.executeUpdate(query);
            
       } catch (SQLException ex) {
           Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    

}
