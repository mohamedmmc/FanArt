/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;


import com.esprit.dao.Session;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.recedao;
import com.esprit.dao.recpdao;
import com.esprit.entity.recevent;
import com.esprit.entity.recprod;
import com.esprit.utilis.ConnexionSingleton;
import com.esprit.utilis.MailSender;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.DefaultListModel;

import java.util.Date;
import java.util.Properties;
import java.util.stream.IntStream;
import javafx.beans.value.ChangeListener;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.scene.input.MouseButton;
/**
 * FXML Controller class
 *
 * @author ASUS GL703VD
 */
public class TestController implements Initializable {

    String emaill = "";
    
    @FXML
    private ComboBox<String> cmb;
    
    private static recpdao instance;
    private Statement st;
    private ResultSet rs;
    @FXML
    private ComboBox<String> cmb1;
    @FXML
    private TextArea rec;
    @FXML
    private Button recprodd;
    @FXML
    private ListView<String> listpro;
    @FXML
    private TextArea rec1;
    @FXML
    private Button recev;
    @FXML
    private ListView<String> listpro1;
    @FXML
    private Button modev;
    @FXML
    private Button modprodd;
    @FXML
    private Button modprodd1;
    @FXML
    private TextField sea;
    @FXML
    private TextField sea1;
    @FXML
    private Button supeve;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int idd;
        ServiceUser su = new ServiceUser();
        emaill = su.findmail(Session.getId());
         ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
         updatec();
         Fillist();
         updateev();
         Fillisteve();
         
           
        

           
            recprodd.setOnAction(event -> {
            
            recprod p = new recprod(cmb.getValue(), emaill , rec.getText());
            recpdao pdao = null;
            try {
                pdao = recpdao.getInstance();
            } catch (SQLException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        updatec();
        MailSender ms = new MailSender();
            try {
                ms.send(emaill, emaill);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
          cmb.valueProperty().set(null);
         rec.clear();
                   Fillist();
                   

       
        });
        
              } 
             
    private void updatec(){
    
        String req = "select * from produit p left join recprod rp on p.titre = rp.nomprod and rp.email= '"+emaill+"' WHERE rp.nomprod IS NULL ";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {
           cmb.getItems().add(rs.getString("titre"));
        }
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
        private void updateev(){
           DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
           Date date = new Date();
        String req = "select * from evenement p left join recevent rp on p.titre = rp.nomevent and rp.email= '"+emaill+"'   WHERE rp.nomevent IS NULL ";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {  String y = rs.getString("date");
         DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
         Date datecom = format.parse(y);
         int x= date.compareTo(datecom);
         if (x>0)
         {
        
           cmb1.getItems().add(rs.getString("titre"));
        }}
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
     private void Fillist(){
         ObservableList<String> m =FXCollections.observableArrayList(
          );;
          
      FilteredList<String> filteredData = new FilteredList<>(m, s -> true);
         
       
        String req = "select * from recprod where email = '"+emaill+"' ";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {
           String nom = rs.getString("nomprod");
             
           m.add(nom);
        }
        listpro.setItems(m);
        
        
        
}   
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
     
           
           
          
       
         String tmp = sea.getText();
         
         sea.textProperty().addListener(obs->{
        String filter = sea.getText(); 
        if(filter == null || filter.length() == 0) {
            filteredData.setPredicate(s -> true);
        }
        else {
            filteredData.setPredicate(s -> s.contains(filter));
            listpro.setItems(filteredData);
            
        }
    
    });}
     
     private void Fillisteve(){
         ObservableList<String> m =FXCollections.observableArrayList(
          );;
          
      FilteredList<String> filteredData = new FilteredList<>(m, s -> true);
         
       
        String req = "select * from recevent where email = '"+emaill+"' ";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {
           String nom = rs.getString("nomevent");
             
           m.add(nom);
        }
        listpro1.setItems(m);
        
        
        
}   
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
     
           
           
          
       
         String tmp = sea1.getText();
         
         sea1.textProperty().addListener(obs->{
        String filter = sea1.getText(); 
        if(filter == null || filter.length() == 0) {
            filteredData.setPredicate(s -> true);
        }
        else {
            filteredData.setPredicate(s -> s.contains(filter));
            listpro1.setItems(filteredData);
            
        }
    
    });}
     
/*     public void SendEmail()
{
 {
       final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 // Get a Properties object
    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.store.protocol", "pop3");
    props.put("mail.transport.protocol", "smtp");
    final String username = "mhabybs@gmail.com";//
    final String password = "driptoohard";
    try{
      Session session = Session.getDefaultInstance(props, 
                          new Authenticator(){
                             protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                             }});

   // -- Create a new message --
      Message msg = new MimeMessage(session);

   // -- Set the FROM and TO fields --
      msg.setFrom(new InternetAddress("mhabybs@gmail.com"));
      msg.setRecipients(Message.RecipientType.TO, 
                        InternetAddress.parse(emaill,false));
      msg.setSubject("Hello");
      msg.setText("How are you");
      msg.setSentDate(new Date());
      Transport.send(msg);
      System.out.println("Message sent.");
    }catch (MessagingException e){ 
      System.out.println("Erreur d'envoi, cause: " + e);
    }
  }

}*/

    @FXML
    private void Display(MouseEvent event) {
      if(event.getButton().equals(MouseButton.PRIMARY)){  
          if(event.getClickCount() == 1){
                
        String tmp = listpro.getSelectionModel().getSelectedItem();
         String req = "select * from recprod where nomprod= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next())
            
            if (rs.getString("status").equals("repondu") )
        {
            rec.setEditable(false);
            modprodd.setDisable(true);
            modprodd1.setDisable(false);
             String add1= rs.getString("nomprod");
         cmb.getSelectionModel().select(add1);
         
         cmb.setDisable(true);
         
         String add2 = rs.getString("reclprod");
         rec.setText(add2);
        }
        else
        { 
            modprodd.setDisable(false);
            recprodd.setDisable(true);
          String add1= rs.getString("nomprod");
         cmb.getSelectionModel().select(add1);
         
         cmb.setDisable(true);
         
         String add2 = rs.getString("reclprod");
         rec.setText(add2);
         
          rec.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                    modprodd1.setDisable(false);
                    
        
         
         
          
          
          
          
          
        
        
    
         
                 
        }
        
        
});}}
      
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
                    
                    }
      
          else  if(event.getClickCount() == 2) 
          { 
         recprodd.setDisable(false);
         cmb.setDisable(false);
         cmb.valueProperty().set(null);
         rec.clear();
     
         modprodd.setDisable(true);
         rec.textProperty().addListener(new ChangeListener<String>() {
                  @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                    modprodd.setDisable(true);}});}
    
    }
    
    }
    
    
        
    
            
                    

                    
          
          
          
          
        
        
    
         
                 
        
        
        

   

    @FXML
    
    private void del(MouseEvent event) {
        String tmp = listpro.getSelectionModel().getSelectedItem();
         String req = "select * from recprod where nomprod= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next())
        {
          int id = rs.getInt("recpid");
         recprod p = new recprod(id);
            recpdao pdao = recpdao.getInstance();
            pdao.delete(p);
        Fillist();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("deleted");
        alert.show();
        rec.clear();
        updatec();
        cmb.valueProperty().set(null);
        cmb.setDisable(false);
        recprodd.setDisable(false);
         
         
          
          
          
          
          
        
        
    
         
                 
        }
        
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void modi(MouseEvent event) {
        
         String tmp = listpro.getSelectionModel().getSelectedItem();
         String req = "select * from recprod where nomprod= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next())
        {
          int id = rs.getInt("recpid");
         recprod p = new recprod(id);
            recpdao pdao = recpdao.getInstance();
            pdao.update(p,rec.getText());
        Fillist();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("modi");
        alert.show();
        
                rec.clear();
      
        cmb.valueProperty().set(null);
        cmb.setDisable(false);
        
        recprodd.setDisable(false);
        modprodd.setDisable(true);
        modprodd1.setDisable(true);
        
         
         
          
          
          
          
          
        
        
    
         
                 
        }
        
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void addeve(MouseEvent event) throws InterruptedException {
        
        recevent p = new recevent(cmb1.getValue(), emaill , rec1.getText());
            recedao pdao = recedao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
      
         Fillisteve();
         MailSender ms = new MailSender();
        ms.send(emaill, emaill);
        updateev();
          cmb1.valueProperty().set(null);
         rec1.clear();
             
    }

    @FXML
    private void modieve(MouseEvent event) {
        String tmp = listpro1.getSelectionModel().getSelectedItem();
         String req = "select * from recevent where nomevent= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next())
        {  
            rec1.setEditable(false);
          int id = rs.getInt("receid");
         recevent p = new recevent(id);
         recedao pdao = recedao.getInstance();
         pdao.update(p,rec1.getText());
        Fillist();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("modi");
        alert.show();
        
                rec1.clear();
        updateev();
        cmb1.valueProperty().set(null);
        cmb1.setDisable(false);
        modev.setDisable(true);
        recev.setDisable(false);
         
         
          
          
          
          
        }
        
        
    
         
                 
        }
        
        

    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }

    @FXML
    private void deleve(MouseEvent event) {
        
        String tmp = listpro1.getSelectionModel().getSelectedItem();
         String req = "select * from recevent where nomevent= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next())
        {
          int id = rs.getInt("receid");
         recevent p = new recevent(id);
            recedao pdao = recedao.getInstance();
            pdao.delete(p);
        Fillist();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("deleted");
        alert.show();
        rec1.clear();
        updateev();
                 Fillisteve();

        cmb1.valueProperty().set(null);
        cmb1.setDisable(false);
        recev.setDisable(false);
         
         
          
          
          
          
          
        
        
    
         
                 
        }
        
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void dsipeve(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){  
          if(event.getClickCount() == 1){
                
        String tmp = listpro1.getSelectionModel().getSelectedItem();
         String req = "select * from recevent where nomevent= '"+tmp+"'";
    try {
        
        rs=st.executeQuery(req);
        
        if (rs.next()){
            if (rs.getString("status").equals("repondu") )
        {
            rec1.setEditable(false);
            modev.setDisable(true);
            recev.setDisable(true);
            String add1= rs.getString("nomevent");
         cmb1.getSelectionModel().select(add1);
         
         cmb1.setDisable(true);
         
         String add2 = rs.getString("reclevent");
         rec1.setText(add2);
        }
            
            else if (rs.getString("status").equals("pending")){
            
        { recev.setDisable(true);
        supeve.setDisable(false);
          String add1= rs.getString("nomevent");
         cmb1.getSelectionModel().select(add1);
         
         cmb1.setDisable(true);
         
         String add2 = rs.getString("reclevent");
         rec1.setText(add2);
         
          rec1.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                    modev.setDisable(false);
                    
        
         
         
          
          
          
          
          
        
        
    
         
                 
        }
        
        
});}}}}
      
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
                    
                    }
      
          else  if(event.getClickCount() == 2) 
          { supeve.setDisable(true);
         recev.setDisable(false);
         cmb1.setDisable(false);
         cmb1.valueProperty().set(null);
         rec1.clear();
         modev.setDisable(true);
    
    }
    
    }
    }
}
    
        
   
    
    

