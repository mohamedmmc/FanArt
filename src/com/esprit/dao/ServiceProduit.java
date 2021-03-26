/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;
import com.esprit.entity.Produit;
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
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author splin
 */
public class ServiceProduit {
    private static ServiceProduit instance;
    Connection cnx;
    Statement ste;
    private ResultSet rs;

     public ServiceProduit() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            ste=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void Insert(Produit p) {
        String req = "insert into Produit (titre,prix,description,image,id_user) values ('" + p.getTitre() + "','" + p.getPrix() + "','" + p.getDescription() + "','" + p.getImage() + "','" + p.getArtiste() + "')";
        try {
            ste.executeUpdate(req);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit insérée avec succés!");
        alert.show();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("echec de l'insertion!");
        alert.show();
        }
    }
     public void delete(Produit o) throws SQLException {
        {
        String req="delete from produit where id_produit="+(o.getId());
        Produit p=displayById(o.getId());
        
              try {
           
            ste.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
     }
     public ObservableList<Produit> displayAll() {
        String req="select * from produit";
        ObservableList<Produit> list=FXCollections.observableArrayList();       
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setPrix(rs.getFloat("prix"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Produit> displayList() {
        String req="select * from produit";
        List<Produit> list=new ArrayList<Produit>();           
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setPrix(rs.getFloat("prix"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     public Produit displayById(int id) {
           String req="select * from produit where id_produit ="+id;
           Produit p=new Produit();
        try {
            rs=ste.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id_produit"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setArtiste(rs.getInt("id_user"));
                p.setPrix(rs.getFloat("prix"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
     public List<Produit> displayAllList() {
        String req="select * from produit";
        List<Produit> list=new ArrayList<>();
        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                Produit p=new Produit();
                p.setId(rs.getInt("id_produit"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setArtiste(rs.getInt("id_user"));
                p.setPrix(rs.getFloat("prix"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public boolean update(Produit p) {
        String qry = "UPDATE produit SET titre = '"+p.getTitre()+"', description = '"+p.getDescription()+"', prix = '"+p.getPrix()+"', image = '"+p.getImage()+"' WHERE id_produit = "+p.getId();
        
        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      public static ServiceProduit getInstance(){
        if(instance==null) 
            instance=new ServiceProduit();
        return instance;
    }
      
  
     
     
    
}
