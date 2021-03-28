/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;


import com.esprit.entity.recevent;
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
        public void updatest(recprod p, String email) {
       String qry = "UPDATE recprod SET status = '"+email+"' WHERE recpid = "+p.getId();        
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

    

          public ObservableList<recprod> displayAll() {
        String req="select * from recprod";
        ObservableList<recprod> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                recprod p=new recprod();
                
                p.setprodnom(rs.getString("nomprod"));
                p.setemail(rs.getString("email"));
                p.setrec(rs.getString("reclprod"));
                p.setsta(rs.getString("status"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
              public List<recprod> displayAllList() {
        String req="select * from recevent";
        List<recprod> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                recprod p=new recprod();
                 
                p.setprodnom(rs.getString("nomprod"));
                p.setemail(rs.getString("email"));
                p.setrec(rs.getString("reclprod"));
                p.setsta(rs.getString("status"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
}
    

