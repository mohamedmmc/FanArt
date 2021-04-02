/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.User;
import static com.esprit.utilis.HashCode.generatedHash;
import com.esprit.utilis.ConnexionSingleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
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
import javafx.scene.image.Image;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author splin
 */
public class ServiceUser {

    Connection cnx;
    Statement ste;
    ResultSet rs;
    PreparedStatement pst;

    public ServiceUser() {
        try {
            cnx = ConnexionSingleton.getInstance().getCnx();
            ste = (Statement) cnx.createStatement();
        } catch (SQLException e) {

        }

    }

    public Integer Existance(User u) throws SQLException {
        int a = 0;
        String reqq = "select email,numtel from user where email='" + u.getEmail() + "' OR numtel='" + u.getNumtel() + "'";
        ResultSet srs = ste.executeQuery(reqq);
        while (srs.next()) {
            if (u.getEmail().equals(srs.getString("email"))) {
                a = 1;
                return a;
            } else if (u.getNumtel() == srs.getInt("numtel")) {
                a = 2;
                return a;
            }
        }

        return a;
    }

    public void insert(User u) throws NoSuchAlgorithmException, SQLException {
        //String a = u.getMdp();
        //System.out.println(u.getPhoto());
        String Hashed = generatedHash(u.getMdp(), "SHA-256");
        //System.out.println(Hashed);

        String req = "insert into user (nom,prenom,mdp,email,numtel,photo,type) values ('" + u.getNom() + "','" + u.getPrenom() + "','" + Hashed + "','" + u.getEmail() + "','" + u.getNumtel() + "','" + u.getPhoto() + "','" + u.getType() + "')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public Integer VerifyNum(Integer num) throws SQLException {
        String req = "select id,numtel from user where numtel='" + num + "'";
        pst = cnx.prepareStatement(req);
        rs = pst.executeQuery();
        User u = new User();

        while (rs.next()) {
            u.setId(rs.getInt("id"));
            u.setNumtel(rs.getInt("numtel"));
        }
        if (num == u.getNumtel()) {
            return u.getId();
        } else {
            return 0;
        }

    }

    public Integer verify(String email, String mdp) throws SQLException, NoSuchAlgorithmException {
        String req = "select id,email,mdp from user where email='" + email + "'";
        ResultSet rs = ste.executeQuery(req);
        String hashed2;
        boolean mailtrue = false, mdptrue = false;
        while (rs.next()) {
            if (email.equals(rs.getString("email"))) {
                mailtrue = true;
                hashed2 = generatedHash(mdp, "SHA-256");
                if (rs.getString("mdp").equals(hashed2)) {
                    mdptrue = true;
                    return rs.getInt("id");
                }
            }
        }
        if (mailtrue == false || mdptrue == false) {
            return 0;
        }
        return 0;
    }

    public Boolean verifymdp(String mdp, String a) {
        return false;

    }

    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> eventsList = FXCollections.observableArrayList();
        String query = "select nom, prenom, email, numtel, photo,type from user";
        //ResultSet rs;
        rs = ste.executeQuery(query);
        User user;
        while (rs.next()) {
            user = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"), rs.getString("photo"), rs.getString("type"));
            eventsList.add(user);

        }
        return eventsList;

    }

    public Image affichage(String image) throws SQLException {
        Image pic = null;
        String query = "select photo from user where photo='" + image + "')";
        try {
            ste.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;

    }

    public List<User> displayAllList() {
        String req = "select nom, prenom, email, numtel, photo,type from user";
        List<User> list = new ArrayList<>();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                User p = new User();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                p.setNumtel(rs.getInt("numtel"));
                p.setPhoto(rs.getString("photo"));
                p.setType(rs.getString("type"));

                list.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void Deluser(String u) throws SQLException {
        String query = "delete from user where email = '" + u + "'";
        println(query);
        ste.executeUpdate(query);

    }

    public ObservableList<User> getUserListfiltered(String k) throws SQLException {
        ObservableList<User> eventsList = FXCollections.observableArrayList();
        String query = "Select nom,prenom,email,numtel,type  from user where nom like '" + k + "%'";
        //ResultSet rs;
        rs = ste.executeQuery(query);
        User user;
        while (rs.next()) {
            user = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"), rs.getString("type"));
            eventsList.add(user);

        }
        return eventsList;

    }

    public void chercher(String k) throws SQLException {
        String req = "Select nom,prenom,email,numtel,type from user where nom like '" + k + "%' ";
        ResultSet rs = ste.executeQuery(req);
        rs = ste.executeQuery(req);

    }

    public void sendphp(String che) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        HttpPost httppost = new HttpPost("http://localhost:8080/img/upload2.php");

        File file = new File(che);

        MultipartEntity mpEntity = new MultipartEntity();
        ContentBody cbFile = new FileBody(file, "image/jpeg");
        //System.out.println(cbFile.getFilename());
        mpEntity.addPart("userfile", cbFile);

        httppost.setEntity(mpEntity);
        //System.out.println("executing request " + httppost.getRequestLine());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();

        //System.out.println(response.getStatusLine());
        if (resEntity != null) {
            System.out.println(EntityUtils.toString(resEntity));
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }

        httpclient.getConnectionManager().shutdown();

    }

    public User findBymail(int S) {
        User u = new User();
        String req = "Select * from user where id ='" + S + "' ";
        try {
            pst = cnx.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {

                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setEmail(rs.getString("email"));
                u.setNumtel(rs.getInt("numtel"));
                u.setPhoto(rs.getString("photo"));
                u.setId(rs.getInt("id"));
                u.setType(rs.getString("type"));
            }
        } catch (Exception e) {
        }
        return u;

    }

    public String findmail(int S) {
        String em = "";
        String req = "Select email from user where id ='" + S + "' ";
        try {
            pst = cnx.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                em = (rs.getString("email"));

            }
        } catch (Exception e) {
        }
        return em;

    }

    public void ModifierUser(User u, int id) {
        String sql = "UPDATE user SET `nom`=?,`prenom`=?,`mdp`=?,`email`=?,`numtel`=?,`photo`=? WHERE id='" + id + "'";
        PreparedStatement ste;
        //System.out.println(mail);
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getMdp());
            ste.setString(4, u.getEmail());
            ste.setInt(5, u.getNumtel());
            ste.setString(6, u.getPhoto());

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("La modification de la classe :" + u.getFirst_Name());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ModifierUserWmdp(User u, int id) {
        String sql = "UPDATE user SET `nom`=?,`prenom`=?,`email`=?,`numtel`=?,`photo`=? WHERE id='" + id + "'";
        PreparedStatement ste;
        //System.out.println(mail);
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, u.getNom());
            ste.setString(2, u.getPrenom());
            ste.setString(3, u.getEmail());
            ste.setInt(4, u.getNumtel());
            ste.setString(5, u.getPhoto());

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("La modification de la classe :" + u.getFirst_Name());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void MotdepasseOublie(String u, int id) {
        String sql = "UPDATE user SET `mdp`=? WHERE id='" + id + "'";
        PreparedStatement ste;
        //System.out.println(mail);
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, u);

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("La modification de la classe :" + u.getFirst_Name());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public Boolean verifytype(Integer id) throws SQLException, NoSuchAlgorithmException {
        String req = "select * from user where id='" + id + "'";
         try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                if ("Artiste".equals(rs.getString(8))) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
