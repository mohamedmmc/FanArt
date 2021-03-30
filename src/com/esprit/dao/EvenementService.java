/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.dao.*;
import com.esprit.entity.Evenement;
import com.esprit.utilis.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ben Gouta Monam
 */
public class EvenementService implements EService<Evenement>{
    public static EvenementService instance ;
    Connection cnx;
    Statement ste;

    public EvenementService() throws SQLException {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            ste=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public static EvenementService getInstance() throws SQLException{
        if(instance==null) 
            instance=new EvenementService();
        return instance;
    }


//****************************************************************************************
    @Override
    public void insert(Evenement t) throws SQLException {
        
        
        
String requeteInsert = "INSERT INTO `evenement`( `Titre`, `Description`, `Locall`, `Date_debut`, `Date_Fin`, `Nombre_place`, `Prix`,`image`) VALUES ('" + t.getTitre() + "','" + t.getDescription() + "','" + t.getLocall() + "','" + t.getDate_debut() + "','" + t.getDate_Fin() + "'," + t.getNombre_place() + "," + t.getPrix() +",'"+t.getImage()+"')";
        try {
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }    }
//****************************************************************************************
    @Override
    public void delete(Evenement t) throws SQLException {
        String reqdel = "DELETE FROM `evenement` where `Id_evenement`=" + t.getId_evenement();
        try {
            ste.executeUpdate(reqdel);

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//****************************************************************************************
    @Override
    public ObservableList displayAll() throws SQLException {
        String req = "SELECT `Id_evenement`,`Titre`,`Description`,`Locall`,`Prix` FROM `evenement`";
        ObservableList<Evenement> list = FXCollections.observableArrayList();
            
        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_evenement(rs.getInt("Id_evenement"));
                p.setTitre(rs.getString("Titre"));
                p.setDescription(rs.getString("Description"));
               /* p.setDate_debut(rs.getDate("Date_Debut"));
                p.setDate_Fin(rs.getDate("Date_Fin"));
                p.setNombre_place(rs.getInt("Nombre_place"));*/
                p.setPrix(rs.getInt("Prix"));
                p.setLocall(rs.getString("Locall"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
      public List<Evenement> ListEvent() throws SQLException {
        String req = "SELECT * FROM `evenement`";
       List<Evenement> list = new ArrayList<Evenement>();
            
        try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                
                p.setId_evenement(rs.getInt("Id_evenement"));
                p.setTitre(rs.getString("Titre"));
                p.setDescription(rs.getString("Description"));
               /* p.setDate_debut(rs.getDate("Date_Debut"));
                p.setDate_Fin(rs.getDate("Date_Fin"));
                p.setNombre_place(rs.getInt("Nombre_place"));*/
                p.setPrix(rs.getInt("Prix"));
                p.setLocall(rs.getString("Locall"));
                p.setImage(rs.getString(9));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
//****************************************************************************************
    @Override
    public Evenement displayById(int id) throws SQLException {
          String req="Select * from evenement where id_user="+id;
          Evenement p=new Evenement();
          try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {               
                p.setTitre(rs.getString("Titre"));
                p.setDescription(rs.getString("Description"));
                p.setDate_debut(rs.getString("Date_Debut"));
                p.setDate_Fin(rs.getString("Date_Fin"));
                p.setNombre_place(rs.getInt("Nombre_place"));
                p.setPrix(rs.getInt("Prix"));
                p.setLocall(rs.getString("Locall"));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
//****************************************************************************************
    @Override
    public boolean update(Evenement t) throws SQLException {
 String qry =" UPDATE `evenement` SET `Titre`='"+t.getTitre()+"',`Description`='"+t.getDescription()+"',`Locall`='"+t.getLocall()+"',`Date_debut`='"+t.getDate_debut()+"',`Date_Fin`='"+t.getDate_Fin()+"',`Nombre_place`="+t.getNombre_place()+",`Prix`="+t.getPrix()+" WHERE `Id_evenement`="+t.getId_evenement();

        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //****************************************************************************************

    public List<String> listlocal() throws SQLException {
        List list = new ArrayList();
        String req = "SELECT `name` FROM `local`";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(rs.getString(1));

        }

        return list;
    }
    //****************************************************************************************
     public List<String> listartist() throws SQLException {
        List list = new ArrayList();
        String req = "SELECT `nom` FROM `user` where type='artist'";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(rs.getString(1));

        }

        return list;
    }
     public List<String> listdateevent() throws SQLException{
         List list = new ArrayList();
           String req = "SELECT `Date_debut` FROM `evenement` ";
        ResultSet rs = ste.executeQuery(req);
        while (rs.next()) {
            list.add(rs.getString(1));
        }
         return list;
         
     }
    
}
