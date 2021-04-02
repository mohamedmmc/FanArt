package com.esprit.dao;

import com.esprit.entity.Evenement;
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

public class EvenementService implements EService<Evenement> {
   public static EvenementService instance;
   Connection cnx;
   Statement ste;

   public EvenementService() throws SQLException {
      ConnexionSingleton cs = ConnexionSingleton.getInstance();

      try {
         this.ste = cs.getCnx().createStatement();
      } catch (SQLException var3) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

   }

   public static EvenementService getInstance() throws SQLException {
      if (instance == null) {
         instance = new EvenementService();
      }

      return instance;
   }

   @Override
   public void insert(Evenement t) throws SQLException {
      String requeteInsert = "INSERT INTO `evenement`( `Titre`, `Description`, `Locall`, `Date_debut`, `Date_Fin`, `Nombre_place`, `Prix`,`image`) VALUES ('" + t.getTitre() + "','" + t.getDescription() + "','" + t.getLocall() + "','" + t.getDate_debut() + "','" + t.getDate_Fin() + "'," + t.getNombre_place() + "," + t.getPrix() + ",'" + t.getImage() + "')";

      try {
         this.ste.executeUpdate(requeteInsert);
      } catch (SQLException var4) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   public void delete(Evenement t) throws SQLException {
      String reqdel = "DELETE FROM `evenement` where `Id_evenement`=" + t.getId_evenement();

      try {
         this.ste.executeUpdate(reqdel);
      } catch (SQLException var4) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   public ObservableList displayAll() throws SQLException {
      String req = "SELECT * FROM `evenement`";
      ObservableList list = FXCollections.observableArrayList();

      try {
         ResultSet rs = this.ste.executeQuery(req);

         while(rs.next()) {
            Evenement p = new Evenement();
            p.setId_evenement(rs.getInt("Id_evenement"));
            p.setTitre(rs.getString("Titre"));
            p.setDescription(rs.getString("Description"));
            p.setDate_debut(rs.getString("Date_debut"));
            p.setDate_Fin(rs.getString("Date_Fin"));
            p.setPrix(rs.getInt("Prix"));
            p.setNombre_place(rs.getInt("Nombre_place"));
            p.setLocall(rs.getString("Locall"));
            p.setImage(rs.getString("image"));
            list.add(p);
         }
      } catch (SQLException var5) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      return list;
   }

   public List<Evenement> ListEvent() throws SQLException {
      String req = "SELECT * FROM `evenement`";
      ArrayList list = new ArrayList();

      try {
         ResultSet rs = this.ste.executeQuery(req);

         while(rs.next()) {
            Evenement p = new Evenement();
            p.setId_evenement(rs.getInt("Id_evenement"));
            p.setTitre(rs.getString("Titre"));
            p.setDescription(rs.getString("Description"));
            p.setDate_debut(rs.getString("Date_Debut"));
            p.setDate_Fin(rs.getString("Date_Fin"));
            p.setNombre_place(rs.getInt("Nombre_place"));
            p.setPrix(rs.getInt("Prix"));
            p.setLocall(rs.getString("Locall"));
            p.setImage(rs.getString(9));
            list.add(p);
         }
      } catch (SQLException var5) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      return list;
   }

   public List<Evenement> ListEventByChar(String chaine) throws SQLException {
      String req = "SELECT * FROM `evenement` WHERE `Titre` like'" + chaine + "%'";
      ArrayList list = new ArrayList();

      try {
         ResultSet rs = this.ste.executeQuery(req);

         while(rs.next()) {
            Evenement p = new Evenement();
            p.setId_evenement(rs.getInt("Id_evenement"));
            p.setTitre(rs.getString("Titre"));
            p.setDescription(rs.getString("Description"));
            p.setDate_debut(rs.getString("Date_Debut"));
            p.setDate_Fin(rs.getString("Date_Fin"));
            p.setNombre_place(rs.getInt("Nombre_place"));
            p.setPrix(rs.getInt("Prix"));
            p.setLocall(rs.getString("Locall"));
            p.setImage(rs.getString(9));
            list.add(p);
         }
      } catch (SQLException var6) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return list;
   }

   public Evenement displayById(int id) throws SQLException {
      String req = "Select * from evenement where id_user=" + id;
      Evenement p = new Evenement();

      try {
         ResultSet rs = this.ste.executeQuery(req);

         while(rs.next()) {
            p.setTitre(rs.getString("Titre"));
            p.setDescription(rs.getString("Description"));
            p.setDate_debut(rs.getString("Date_Debut"));
            p.setDate_Fin(rs.getString("Date_Fin"));
            p.setNombre_place(rs.getInt("Nombre_place"));
            p.setPrix(rs.getInt("Prix"));
            p.setLocall(rs.getString("Locall"));
         }
      } catch (SQLException var5) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      return p;
   }

   public boolean update(Evenement t) throws SQLException {
      String qry = " UPDATE `evenement` SET `Titre`='" + t.getTitre() + "',`Description`='" + t.getDescription() + "',`Locall`='" + t.getLocall() + "',`Date_debut`='" + t.getDate_debut() + "',`Date_Fin`='" + t.getDate_Fin() + "',`Nombre_place`=" + t.getNombre_place() + ",`Prix`=" + t.getPrix() + " WHERE `Id_evenement`=" + t.getId_evenement();

      try {
         if (this.ste.executeUpdate(qry) > 0) {
            return true;
         }
      } catch (SQLException var4) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

      return false;
   }

   public List<String> listlocal() throws SQLException {
      List list = new ArrayList();
      String req = "SELECT `idsalle` FROM `salle`";
      ResultSet rs = this.ste.executeQuery(req);

      while(rs.next()) {
         list.add(rs.getString(1));
      }

      return list;
   }

   public List<String> listartist() throws SQLException {
      List list = new ArrayList();
      String req = "SELECT `nom` FROM `user` where type='artist'";
      ResultSet rs = this.ste.executeQuery(req);

      while(rs.next()) {
         list.add(rs.getString(1));
      }

      return list;
   }

   public List<String> listdateevent() throws SQLException {
      List list = new ArrayList();
      String req = "SELECT `Date_debut` FROM `evenement` ";
      ResultSet rs = this.ste.executeQuery(req);

      while(rs.next()) {
         list.add(rs.getString(1));
      }

      return list;
   }

   public int Nombreplace(int n) {
      try {
         String req = "SELECT `nbreplace` FROM `salle` WHERE `idsalle`=15" + n;
         ResultSet rs = this.ste.executeQuery(req);
         return rs.getInt("nbreplace");
      } catch (SQLException var4) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, (String)null, var4);
         return 0;
      }
   }
}
