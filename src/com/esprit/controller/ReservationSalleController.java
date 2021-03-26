/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.utilis.Connexion;

import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ReservationSalleController implements Initializable {

    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    String tab[];
    int i=0;

    @FXML
    private Button reset;
    
    @FXML
    private Button retour2;
    
    @FXML
    private TextField nomclient;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField numtele;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField hour;
    
    @FXML
    private ComboBox<String> artiste;
    
    @FXML
    private Button confirme;
    
    @FXML
    private TextField numsalle;
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
         
        String query="select * from salle";
        ResultSet rs;
        String s;
     
        PreparedStatement ps;
        try {
         ps = con.prepareStatement(query);
         rs=ps.executeQuery();
         while(rs.next())  {
          //  s=rs.getString("nomclient");
           // artiste.getItems().addAll(s);
         }  
        } catch (SQLException ex) {
            Logger.getLogger(ReservationSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* try {
            fnDate();
        } catch (ParseException ex) {
            Logger.getLogger(ReservationSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

    public void fnDate() throws ParseException
    {
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
         
        String query="select * from evenment";
        ResultSet rs;
        String s;
      
        
        try {
        ps = con.prepareStatement(query);
        rs=ps.executeQuery();
        while(rs.next())  {
        Date d1=rs.getDate("Date_debut");
       //LocalDate d11=  d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d11 = Instant.ofEpochMilli(d1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        Date d2=rs.getDate("Date_fin");
        LocalDate d22= Instant.ofEpochMilli(d2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();          
              
        while (!d11.equals(d22)) 
        {
        disableDate(d11);
        String k=nextDate(d11.toString());
        LocalDate ld = LocalDate.parse( k ) ;
        d11=ld;
        }
        disableDate(d11);
        System.out.println(tab);
        } 
        } catch (SQLException ex) {
            Logger.getLogger(ReservationSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 



    public String nextDate(String d) throws ParseException
    {
       
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     Calendar c = Calendar.getInstance();
     c.setTime(sdf.parse(d));
     c.add(Calendar.DATE, 1);  // number of days to add
     d = sdf.format(c.getTime());
     return  d;
     }
     
    
    public void disableDate(LocalDate dat)
    {
                        // System.out.println(dat);
    int d=dat.getDayOfMonth();
    int m=dat.getMonthValue();
    int y=dat.getYear();
    date.setValue(LocalDate.of(y,m,d));
    tab[0]="kk";
    i++;
    }
*/
    }
    @FXML
    private void client(ActionEvent event) {
    }

    @FXML
    private void mail(ActionEvent event) {
    }

    @FXML
    private void telephone(ActionEvent event) {
    }

    @FXML
    private void heure(ActionEvent event) {
    }
    
     @FXML
    private void numsalle(ActionEvent event) {
    }
        
    public String verifier(){
    if (nomclient.getText().equals("")|| email.getText().equals("") || numtele.getText().equals("") || 
        date.getValue().toString().equals("") || hour.getText().equals("") || numsalle.getText().equals(""))
       return "true";

    else 
         return "false";
    }
       
    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
        
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String nom=nomclient.getText(); 
        String mail=email.getText();
        String tele=numtele.getText();
        String dat=date.getValue().toString();
        String heure=hour.getText();
        String numsall=numsalle.getText();
        String artist=artiste.getSelectionModel().getSelectedItem().toString();
         //System.out.println(dat+" "+artist);
         
        String query ="insert into reservation (nomclient,numtele,email,date,heure,numsalle,artiste) values (?,?,?,?,?,?,?)";      
        
        if (verifier()=="false") { 
        try {
        
        ps =con.prepareStatement(query);
        ps.setString(1,nom);
        ps.setString(2,mail);
        ps.setString(3,tele);
        ps.setString(4,dat);
        ps.setString(5,heure);
        ps.setString(6,numsall);
        ps.setString(7,artist);
        ps.execute();
               
        ImageIcon img2 = new ImageIcon("C:\\Users\\ranya\\Desktop\\modifier.png");
        JOptionPane.showMessageDialog(null," La réservation a bien été enregistré ", "Information", JOptionPane.INFORMATION_MESSAGE, img2);
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            
        } else{
        ImageIcon img1 = new ImageIcon("C:\\Users\\ranya\\Desktop\\attention.png");
        JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs ! ", "Information", JOptionPane.INFORMATION_MESSAGE, img1);
        }
        
        try {		
        String from = "samehbr63@gmail.com";
	String pass = "sameh2016";
        String to ="sameh.benromdhane@esprit.tn";
        String subject = "Reservation" ;
        String messageText = " MRS " +nomclient.getText()+ " Votre réservation a été bien enregistrer avec la date : " +date.getValue().toString()+ " a : "+hour.getText();
	String host = "smtp.gmail.com";

	boolean sessionDebug = false;
	Properties props = System.getProperties();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", "587");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.required", "true");

	Session mailSession = Session.getDefaultInstance(props, null);
	mailSession.setDebug(sessionDebug);
	Message msg = new MimeMessage(mailSession);
	msg.setFrom(new InternetAddress(from));
	InternetAddress[] address = { new InternetAddress(to) };
	msg.setRecipients(Message.RecipientType.TO, address);
	msg.setSubject(subject);
        msg.setSentDate(new Date());
	msg.setText(messageText);
	Transport transport = mailSession.getTransport("smtp");
	transport.connect(host, from, pass);
	transport.sendMessage(msg, msg.getAllRecipients());
	transport.close();
	System.out.println("message send successfully");
	} catch (Exception ex) {
	System.out.println(ex);
	}
    }
    
    @FXML
    private void annuler(ActionEvent event) {
        nomclient.setText("");
        email.setText("");
        numtele.setText("");
        hour.setText("");
        numsalle.setText("");
        
    }
    
    @FXML
    private void retour(ActionEvent event) {
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListeSalles.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch(Exception e) {
              e.printStackTrace();
            }
    }
    
}