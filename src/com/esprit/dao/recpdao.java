/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;


import com.esprit.entity.recprod;
import com.esprit.utilis.ConnexionSingleton;
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
 * @author splin
 */
public  class recpdao implements ldao<recprod> {
    
    private static recpdao instance;
    private Statement st;
    private ResultSet rs;
    
    private recpdao() throws SQLException {
            ConnexionSingleton cs=ConnexionSingleton.getInstance();
            st=cs.getCnx().createStatement();
    }
    
    public static recpdao getInstance() throws SQLException{
        if(instance==null) 
            instance=new recpdao();
        return instance;
    }

    
    
             
 

    public void insert(recprod o) {
        String req="insert into recprod (nomprod,email,reclprod,status) values ('"+o.getprodnom()+"','"+o.getemail()+"','"+o.getrec()+"','"+"pending"+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void delete(recprod o) {
        String req="delete from recprod where recpid="+o.getId();
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
       public void update(recprod p, String email) {
       String qry = "UPDATE recprod SET reclprod = '"+email+"' WHERE recpid = "+p.getId();        
        try {
            st.executeUpdate(qry);
            
        } catch (SQLException ex) {
            Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
       public void search(String p) {
       String qry = "select * from recprod where reclprod = '"+p+"' ";        
        try {
            st.executeUpdate(qry);
            
        } catch (SQLException ex) {
            Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

   /* @Override
    public ObservableList<recprod> displayAll() {
        String req="select * from recprod";
        ObservableList<recprod> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                
                p.setEmail(rs.getString("Email"));
                p.setType(rs.getString("Type"));
                p.setRec(rs.getString("Recl"));
                p.setSta(rs.getString("Statu"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recldao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Reclamation> displayAllList() {
        String req="select * from personne";
        List<Reclamation> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setEmail(rs.getString("Email"));
               p.setType(rs.getString("Type"));
               p.setRec(rs.getString("Recl"));
               p.setSta(rs.getString("Statu"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recldao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Reclamation displayByEmail(String email) {
           String req="select * from personne where email ="+email;
           Reclamation  p=new Reclamation();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
           p.setEmail(rs.getString("Email"));
               p.setType(rs.getString("Type"));
               p.setRec(rs.getString("Recl"));
               p.setSta(rs.getString("Statu"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(recldao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
*/
   

    /*@Override
    public void delete(recprod o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public recprod displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    
    
}
    

