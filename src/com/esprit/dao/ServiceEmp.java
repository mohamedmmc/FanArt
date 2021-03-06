/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Employe;
import com.esprit.utils.ConnexionSingleton;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
/**
 *
 * @author saif
 */
public class ServiceEmp {
    private static ServiceEmp instance;
    Connection cnx;
    Statement ste;
    private ResultSet rs;
    
    private ServiceEmp(){
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            ste=(Statement) cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public void Insert(Employe p) throws Exception {
        String req = "insert into Employe (Nom,Prenom,tache,mobile,salle) values ('" + p.getNom() + "','" + p.getPrenom() + "','" + p.getTache() + "','" + p.getMobile() + "'," + p.getSalle() + ")";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit insérée avec succés!");
        alert.show();
    }
    public ObservableList<Employe> displayAll() {
        String req="select * from produit";
        ObservableList<Employe> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Employe p=new Employe();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("Gouider"));
                p.setPrenom(rs.getString("saifeddine"));
                p.setTache(rs.getString("technicien"));
                p.setMobile(rs.getString("27621983"));
                p.setSalle(rs.getInt(2));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Employe displayById(int id) {
           String req="select * from produit where id_produit ="+id;
           Employe p=new Employe();
        try {
            rs=ste.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setNom(rs.getString("Nom"));
                p.setPrenom(rs.getString("Seifeddine"));
                p.setTache(rs.getString("technicien"));
                p.setMobile(rs.getString("27621983"));
                p.setSalle(rs.getInt(2));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    public List<Employe> displayAllList() {
        String req="select * from employe";
        List<Employe> list=new ArrayList<>();
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Employe p=new Employe();
                p.setNom(rs.getString("Nom"));
                p.setPrenom(rs.getString("Seifeddine"));
                p.setTache(rs.getString("technicien"));
                p.setMobile(rs.getString("27621983"));
                p.setSalle(rs.getInt(2));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public boolean update(Employe p) throws SQLException {
        String qry = "UPDATE Employe SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', tache = '"+p.getTache()+"', mobile = '"+p.getMobile()+"' WHERE emplacement_salle = "+p.getSalle();
        return ste.executeUpdate(qry) > 0;
    }
      public static ServiceEmp getInstance(){
        if(instance==null) 
            instance=new ServiceEmp();
        return instance;
    }
    
    
    
    
    
}
