/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Employe;
import com.esprit.utilis.ConnexionSingleton;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.PreparedStatement;
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
public class ServiceEmp implements EmpIdao{
    
    Connection cnx;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

    public ServiceEmp() {
        try {
            cnx = ConnexionSingleton.getInstance().getCnx();
            ste = (Statement) cnx.createStatement();
        } catch (SQLException e) {

        }

    }

    @Override
    public void insert(Employe o) {
        String req="insert into employe (nom,prenom,tache,age,mobile,salaire,num_carte,id_salle) values ("
                + "'"+o.getNom()
                +"','"+o.getPrenom()
                +"','"+o.getTache()
                +"','"+o.getAge()
                +"','"+o.getMobile()
                +"','"+o.getSalaire()
                +"','"+o.getNum_carte()
                +"','"+o.getNum_salle()+"')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(Employe o) {
        String req="delete from personne where id="+o.getId();
        Employe p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            ste.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Employe> displayAll() {
        String req="select * from employe";
        ObservableList<Employe> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Employe p = new Employe();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setTache(rs.getString("tache"));
                p.setDisponible(rs.getString("disponible"));
                p.setMobile(rs.getInt("mobile"));
                p.setNum_salle(rs.getInt("id_salle"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Employe> displayAllList() {
        String req="select * from employe";
        List<Employe> list=new ArrayList<>();
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Employe p=new Employe();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setTache(rs.getString("tache"));
                p.setDisponible(rs.getString("disponible"));
                p.setMobile(rs.getInt("mobile"));
                p.setNum_salle(rs.getInt("id_salle"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    
    @Override
    public Employe displayById(int id) {
        String req="select * from employe where id ="+id;
           Employe p = new Employe();
        try {
            rs=ste.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setTache(rs.getString("tache"));
                p.setNum_salle(rs.getInt("salle"));
                p.setMobile(rs.getInt("mobile"));
                
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    
    
    
    
    
    @Override
    public boolean update(Employe os) {
        String qry = "UPDATE employe SET nom = '"+os.getNom()
                +"', prenom = '"+os.getPrenom()
                +"', tache = '"+os.getTache()
                +"', disponible = '"+os.getDisponible()
                +"', id_salle = '"+os.getNum_salle()
                +"' WHERE id = "+os.getId();
        
        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
