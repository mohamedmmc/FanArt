/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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
        
        
    public void sendHack (String email, String img) throws InterruptedException{
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
            BodyPart messageBodyPart = new MimeBodyPart();
             messageBodyPart.setText("Ci-joint une capture avec la caméra");
               Multipart multipart = new MimeMultipart();
                 multipart.addBodyPart(messageBodyPart);
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("balachandralucky2@gmail.com, "+email)
            );
            message.setSubject("Connexion douteuse");
                             messageBodyPart = new MimeBodyPart();
         
         DataSource source = new FileDataSource(img);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(img);
         multipart.addBodyPart(messageBodyPart);
          message.setContent(multipart);

          Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
