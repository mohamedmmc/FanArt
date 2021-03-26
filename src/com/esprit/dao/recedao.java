/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.recevent;

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
public  class recedao implements ldao<recevent> {
    
    private static recedao instance;
    private Statement st;
    private ResultSet rs;
    
    private recedao() {
            ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static recedao getInstance(){
        if(instance==null) 
            instance=new recedao();
        return instance;
    }

    
    
             
 

    public void insert(recevent o) {
        String req="insert into recevent (nomevent,email,reclevent,status) values ('"+o.geteventnom()+"','"+o.getemail()+"','"+o.getrec()+"','"+"pending"+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void delete(recevent o) {
        String req="delete from recevent where receid="+o.getId();
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
       public void update(recevent p, String email) {
       String qry = "UPDATE recevent SET reclevent = '"+email+"' WHERE receid = "+p.getId();        
        try {
            st.executeUpdate(qry);
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
       public void search(String p) {
       String qry = "select * from recevent where reclevent = '"+p+"' ";        
        try {
            st.executeUpdate(qry);
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
       public ObservableList<recevent> displayAll() {
        String req="select * from recevent";
        ObservableList<recevent> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                recevent p=new recevent();
                
                p.seteventnom(rs.getString("nomevent"));
                p.setemail(rs.getString("email"));
                p.setrec(rs.getString("reclevent"));
                p.setsta(rs.getString("reclevent"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
              public List<recevent> displayAllList() {
        String req="select * from recevent";
        List<recevent> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                recevent p=new recevent();
                 
                p.seteventnom(rs.getString("nomevent"));
                p.setemail(rs.getString("email"));
                p.setrec(rs.getString("reclevent"));
                p.setsta(rs.getString("reclevent"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
       
       

}