/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.dao;

import rec.entity.recevent;

import rec.utils.DB;
import rec.controller.TestController;
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
 * @author ASUS GL703VD
 */
public  class recedao implements ldao<recevent> {
    
    private static recedao instance;
    private Statement st;
    private ResultSet rs;
    
    private recedao() {
            DB cs=DB.getInstance();
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

    
    
             
 

    @Override
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
}