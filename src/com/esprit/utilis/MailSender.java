/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author splin
 */
public class MailSender {
        final String username = "fanart3a18@gmail.com";
        final String password = "3A18java123";
        
        public void send (String email, String name) throws InterruptedException{
            

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Tarunsunny143@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("balachandralucky2@gmail.com, "+email)
            );
            message.setSubject("Bienvenue sur l'application FanArt");
            message.setContent(
              "<h1>Bievenue Mr "+name+"</h1>",
             "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
         public void sendpdf (String email, String name,int panier) throws InterruptedException{
            

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Tarunsunny143@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("balachandralucky2@gmail.com, "+email)
            );
            message.setSubject("facture");
            /*message.setContent(
              "<h1>Bievenue Mr "+name+"</h1>",
             "text/html");*/
            
      // Attacher le fichier à l'email
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setContent("<h1>Bievenue Mr "+name+"</h1>","text/html");
      MimeBodyPart mbp2= new MimeBodyPart();
      mbp2.setContent("<h1>Facture</h1>","text/html");
      FileDataSource fichier_joint = new FileDataSource("C:/Users/Juka/Desktop/facture"+panier+".pdf");
      MimeBodyPart mbp3 = new MimeBodyPart();
      mbp3.setDataHandler(new DataHandler(fichier_joint));
      mbp3.setFileName(fichier_joint.getName());
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);
      mp.addBodyPart(mbp3);
      message.setContent(mp);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
        
    
}
