/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS GL703VD
 */
public class DB {
    
    private String url="jdbc:mysql://localhost/pidev";
    private String login="root";
    private String pwd="root";
    private Connection conn;
    private static DB instance;

    public Connection getCnx() {
        return conn;
    }
    
    
    private DB() {
        try {
            conn=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static DB getInstance(){
       
       if(instance==null)
           instance=new DB();
       return instance;
   }
    
    
}
