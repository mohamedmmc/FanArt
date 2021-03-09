/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.test.Connexion;

import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ranya
 */
public class ReservationSalleController implements Initializable {

    Connection con;
    PreparedStatement ps ;
    ResultSet rs;
    
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

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       
       
       
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        try {
            
            String query="select * from reservation";
            Statement ps =con.createStatement();
             ResultSet rs =ps.executeQuery(query);
           
             if (rs.next()) {
                String s=rs.getString("artiste");
                 System.out.println(rs.getString("nomclient"));    
                artiste.getItems().addAll(s);
             }
                       
        } catch (SQLException ex) {
            Logger.getLogger(ReservationSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    

        // TODO
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
    private void confirmer(ActionEvent event) throws SQLException {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        String nom=nomclient.getText();
        String mail=email.getText();
        String tele=numtele.getText();
        String dat=date.getValue().toString();
        String heure=hour.getText();
        String artist=artiste.getSelectionModel().getSelectedItem().toString();
         //System.out.println(dat+" "+artist);

        
        String query ="insert into reservation (nomclient,numtele,email,date,heure,artiste) values (?,?,?,?,?,?)";
        
        ps =con.prepareStatement(query);
        ps.setString(1,nom);
        ps.setString(2,mail);
        ps.setString(3,tele);
        ps.setString(4,dat);
        ps.setString(5,heure);
        ps.setString(6,artist);
        ps.execute();
        
        JOptionPane.showMessageDialog(null,"La reservation a bien été ajouté ");
        
        try {
			
			String from = "samehbr63@gmail.com";
			String pass = "sameh2016";
			String to ="sameh.benromdhane@esprit.tn";
			String subject = "Reservation" ;
			String messageText = " Votre réservation a été bien enregistré.. Merci ";

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

		} catch (Exception ex)

		{
			System.out.println(ex);

		}
	
    }
    
    
  
    @FXML
    private void annuler(ActionEvent event) {
        nomclient.setText("");
        email.setText("");
        numtele.setText("");
        hour.setText("");
        
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