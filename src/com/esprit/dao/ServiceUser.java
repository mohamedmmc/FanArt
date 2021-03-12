/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.User;
import static com.esprit.utilis.HashCode.generatedHash;
import com.esprit.utilis.MyDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    PreparedStatement pst;

    public ServiceUser() {
        try {
            cnx = MyDB.getInstance().getCnx();
            ste = (Statement) cnx.createStatement();
        } catch (SQLException e) {

        }

    }

    public void insert(User u) throws NoSuchAlgorithmException {
        //String a = u.getMdp();
        //System.out.println(u.getPhoto());
        String Hashed = generatedHash(u.getMdp(), "SHA-256");
        //System.out.println(Hashed);

        String req = "insert into user (nom,prenom,mdp,email,numtel,photo,type) values ('" + u.getNom() + "','" + u.getPrenom() + "','" + Hashed + "','" + u.getEmail() + "','" + u.getNumtel() + "','" + u.getPhoto() + "','" + u.getType() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean verify(String email, String mdp) throws SQLException, NoSuchAlgorithmException {
        String req = "select email,mdp from user where email='" + email + "'";
        ResultSet rs = ste.executeQuery(req);
        String hashed2;
        boolean mailtrue = false, mdptrue = false;
        while (rs.next()) {
            if (email.equals(rs.getString("email"))) {
                mailtrue = true;
                hashed2 = generatedHash(mdp, "SHA-256");
                if (rs.getString("mdp").equals(hashed2)) {
                    mdptrue = true;
                    return true;
                }
            }
        }
        if (mailtrue == false || mdptrue == false) {
            return false;
        }
        return false;
    }

    public Boolean verifymdp(String mdp, String a) {
        return false;

    }

    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> eventsList = FXCollections.observableArrayList();
        String query = "select nom, prenom, email, numtel, photo,type from user";
        //ResultSet rs;
        rs = ste.executeQuery(query);
        User user;
        while (rs.next()) {
            user = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"), rs.getString("photo"), rs.getString("type"));
            eventsList.add(user);

        }
        return eventsList;

    }

    public Image affichage(String image) throws SQLException {
        Image pic = null;
        String query = "select photo from user where photo='" + image + "')";
        try {
            ste.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;

    }

    public List<User> displayAllList() {
        String req = "select nom, prenom, email, numtel, photo,type from user";
        List<User> list = new ArrayList<>();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                User p = new User();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                p.setNumtel(rs.getInt("numtel"));
                p.setPhoto(rs.getString("photo"));
                p.setType(rs.getString("type"));

                list.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void Deluser(String u) throws SQLException {
        String query = "delete from user where email = '" + u + "'";
        println(query);
        ste.executeUpdate(query);

    }
    public ObservableList<User> getUserListfiltered(String k) throws SQLException {
        ObservableList<User> eventsList = FXCollections.observableArrayList();
        String query = "Select nom,prenom,email,numtel,type  from user where nom like '"+k+"%'";
        //ResultSet rs;
        rs = ste.executeQuery(query);
        User user;
        while (rs.next()) {
            user = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"), rs.getString("type"));
            eventsList.add(user);

        }
        return eventsList;

    }
     public void chercher(String k) throws SQLException{
        String req = "Select nom,prenom,email,numtel,type from user where nom like '"+k+"%' ";
        ResultSet rs = ste.executeQuery(req);
        rs = ste.executeQuery(req);
        
        
    }
    public  User findBymail(String S){
        User u = new User();
        String req = "Select * from user where email ='"+S+"' ";
        try {
            pst = cnx.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {                
                
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setEmail(rs.getString("email"));
                u.setNumtel(rs.getInt("numtel"));
                u.setPhoto(rs.getString("photo"));
            }
        } catch (Exception e) {
        }
        return u;
        
    }
     public void ModifierUser(User u, String mail){
        String sql = "UPDATE user SET `nom`=?,`prenom`=?,`mdp`=?,`email`=?,`numtel`=? WHERE email='"+ mail+"'";
        PreparedStatement ste;
        //System.out.println(mail);
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getMdp());
            ste.setString(4, u.getEmail());
            ste.setInt(5, u.getNumtel());

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("La modification de la classe :" + u.getFirst_Name());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
