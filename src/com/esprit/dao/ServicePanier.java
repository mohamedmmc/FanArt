/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Panier;
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
public class ServicePanier {

    public static ServicePanier instance;
    Connection cnx;
    Statement ste;
    private ResultSet rs;

    public ServicePanier() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            ste = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Insert(Panier p) {
        String req = "insert into Panier (id_panier,id_user,validite) values ('" + p.getId_panier() + "','" + p.getId_user() + "','" + p.getValidite() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("echec de l'insertion!");
            alert.show();
        }
    }

    public Integer verif(int c) {
        String req = "select * from panier where id_user=" + c;
        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                if ("nonvalide".equals(rs.getString(3))) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void delete(Panier o) throws SQLException {
        {
            String req = "delete from panier where id_panier=" + (o.getId_panier());
            Panier p = displayById(o.getId_panier());

            try {

                ste.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ObservableList<Panier> displayAll() {
        String req = "select * from Panier";
        ObservableList<Panier> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setId_user(rs.getInt("id_user"));
                p.setValidite(rs.getString("validite"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Panier> displayList() {
        String req = "select * from Panier";
        List<Panier> list = new ArrayList<Panier>();
        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setId_user(rs.getInt("id_user"));
                p.setValidite(rs.getString("validite"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Panier displayById(int id) {
        String req = "select * from Panier where id_user =" + id;
        Panier p = new Panier();
        try {
            rs = ste.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId_panier(rs.getInt(1));
            p.setId_user(rs.getInt("id_user"));
            p.setValidite(rs.getString("validite"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Panier> displayAllList() {
        String req = "select * from Panier";
        List<Panier> list = new ArrayList<>();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setId_user(rs.getInt("id_user"));
                p.setValidite(rs.getString("validite"));;
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean update(Panier p) {
        String qry = "UPDATE Panier SET id_panier = '" + p.getId_panier() + "', id_user = '" + p.getId_user() + "', validite = '" + p.getValidite() + "' WHERE id_panier = " + p.getId_panier();

        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updatevalidite(Panier p) {
        String qry = "UPDATE Panier SET  validite = 'valide' WHERE id_panier = " + p.getId_panier();

        try {
            if (ste.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

}
