/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Panier_elem;
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

/**
 *
 * @author splin
 */
public class ServicePanier_elem {

    private static ServicePanier_elem instance;
    Connection cnx;
    Statement ste;
    private ResultSet rs;

    public ServicePanier_elem() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            ste = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Insert(Panier_elem p) {
        String req = "insert into Panier_elem (id_panier,id_produit,quantite) values ('" + p.getId_panier() + "','" + p.getId_produit() + "','" + p.getQuantite() + "')";
        try {
            ste.executeUpdate(req);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Panier_elem_elem insérée avec succés!");
            alert.show();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("echec de l'insertion!");
            alert.show();
        }
    }

    public void delete(Panier_elem o) throws SQLException {
        {
            String req = "delete from panier where id_panier=" + (o.getId_panier());
            Panier_elem p = displayById(o.getId_panier());

            try {

                ste.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ObservableList<Panier_elem> displayAll() {
        String req = "select * from Panier_elem";
        ObservableList<Panier_elem> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            Panier_elem p = new Panier_elem();
            while (rs.next()) {

                p.setId_panier(rs.getInt(1));
                p.setId_produit(rs.getInt(2));
                p.setQuantite(rs.getInt(3));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Panier_elem> displayList() {
        String req = "select * from Panier_elem";
        List<Panier_elem> list = new ArrayList<Panier_elem>();
        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier_elem p = new Panier_elem();
                p.setId_panier(rs.getInt(1));
                p.setId_produit(rs.getInt(2));
                p.setQuantite(rs.getInt(3));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Panier_elem> displayListById(int id) {
        String req = "select * from Panier_elem where id_panier =" + id;
          List<Panier_elem> list = new ArrayList<Panier_elem>();
        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier_elem p = new Panier_elem();
                p.setId_panier(rs.getInt(1));
                p.setId_produit(rs.getInt(2));
                p.setQuantite(rs.getInt(3));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Panier_elem displayById(int id) {
        String req = "select * from Panier_elem where id_panier =" + id;
        Panier_elem p = new Panier_elem();
        try {
            rs = ste.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId_panier(rs.getInt(1));
            p.setId_produit(rs.getInt(2));
            p.setQuantite(rs.getInt(3));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Panier_elem> displayAllList() {
        String req = "select * from Panier_elem";
        List<Panier_elem> list = new ArrayList<>();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier_elem p = new Panier_elem();
                p.setId_panier(rs.getInt(1));
                p.setId_produit(rs.getInt(2));
                p.setQuantite(rs.getInt(3));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean update(Panier_elem p) {
        String qry = "UPDATE Panier_elem SET id_panier = '" + p.getId_panier() + "', id_user = '" + p.getId_produit() + "', validite = '" + p.getQuantite() + "' WHERE id_panier = " + p.getId_panier();

        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ServicePanier_elem getInstance() {
        if (instance == null) {
            instance = new ServicePanier_elem();
        }
        return instance;
    }

    public int verif(int c, int a) {
        int i = 0;
        String req = "select * from panier_elem where id_produit= '" + c + "' and id_panier='" + a + "'";
        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                i++;
                return i;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public void modifQuantite(int quantite, int id_produit, int id_panier) {
        String req = "update panier_elem set quantite= quantite+'" + quantite + "'  where id_produit='" + id_produit + "'and id_panier='" + id_panier+"'";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier_elem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
