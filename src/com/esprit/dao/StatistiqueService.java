/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.dao.EvenementService;
import com.esprit.dao.StatistiqueService;
import com.esprit.utilis.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author splin
 */
public class StatistiqueService {
    public static StatistiqueService instance ;
    Connection cnx;
    Statement ste;

    public StatistiqueService() throws SQLException {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            ste=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public static StatistiqueService getInstance() throws SQLException{
        if(instance==null) 
            instance=new StatistiqueService();
        return instance;
    }
/*********************************************************************************************************/
    public LinkedHashMap<String,Integer> Selectstatevent(){
           LinkedHashMap<String,Integer> map = new LinkedHashMap<String,Integer>();
             String req = "SELECT e.Titre, (SELECT SUM(Nbr_reservation) FROM participant where e.Id_evenement=participant.Id_evenement   ) as taux_reserve FROM `participant` as p inner join evenement as e on e.Id_evenement=p.Id_evenement GROUP BY Titre limit 6";
             try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                map.put(rs.getString("Titre"),rs.getInt("taux_reserve"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return map;
    }
      public LinkedHashMap<String,Integer> SelectArtist(){
           LinkedHashMap<String,Integer> map = new LinkedHashMap<String,Integer>();
             String req = "SELECT e.specialite,(SELECT COUNT(*) FROM artist WHERE`specialite` =e.specialite) AS taux FROM `artist`as e ";
             try {
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                map.put(rs.getString("specialite"),rs.getInt("taux"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return map;
    }
}
