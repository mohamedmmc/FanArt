/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Participer;
import com.esprit.entity.Evenement;
import com.esprit.utilis.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author splin
 */
public class ParticiperService implements PService<Participer> {
 public static ParticiperService instance ;
    Connection cnx;
    Statement ste;

    public ParticiperService() throws SQLException {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            ste=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ParticiperService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public static ParticiperService getInstance() throws SQLException{
        if(instance==null) 
            instance=new ParticiperService();
        return instance;
    }
/*************************************************************************************************/

    @Override
    public void insert(Participer o) throws SQLException {
         Date uDate =new Date();
         java.sql.Date sdate=new java.sql.Date(uDate.getTime());
       String requeteInsert = "INSERT INTO `participant`(`Id_participant`, `Id_evenement`, `Nbr_reservation`, `Paiement`, `Date_paiement`)VALUES ("+o.getId_participant()+","+o.getId_evenement()+","+o.getNbr_reservation()+","+o.getPaiement()+",NOW())";
        try {
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
/*************************************************************************************************/
    @Override
    public void delete(Participer o) throws SQLException {
     String reqdel = "SELECT * FROM `participant` WHERE `Id_participant`="+o.getId_participant()+"and `Id_evenement`="+o.getId_evenement();
        try {
            ste.executeUpdate(reqdel);

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*************************************************************************************************/
    @Override
    public ObservableList<Participer> displayAll() throws SQLException {
      String req = "SELECT * FROM `participant`";
        ObservableList<Participer> list = FXCollections.observableArrayList();
            
        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Participer p = new Participer();
                
                p.setId_participant(rs.getInt("Id_participant"));
                p.setId_evenement(rs.getInt("Id_evenement"));
                p.setNbr_reservation(rs.getInt("Nbr_reservation"));
                p.setPaiement(rs.getInt("Paiement"));
                p.setDate_paiement(rs.getDate("Date_paiement"));
               
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;    }
/*************************************************************************************************/
    @Override
    public Participer displayById(int id) throws SQLException {
          String req = "SELECT * FROM `participant`where `Id_participant`="+id;
        
            Participer p = new Participer();
        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                
                
                p.setId_participant(rs.getInt("Id_participant"));
                p.setId_evenement(rs.getInt("Id_evenement"));
                p.setNbr_reservation(rs.getInt("Nbr_reservation"));
                p.setPaiement(rs.getInt("Paiement"));
                p.setDate_paiement(rs.getDate("Date_paiement"));
               
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
    
    


   
}
