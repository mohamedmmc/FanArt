/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ranya
 */
public class testmail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       
   
		try {
			
			String from = "samehbr63@gmail.com";
			String pass = "sameh2016";
			String to ="sameh.benromdhane@esprit.tn";
			String subject = "Reclamtion";
			String messageText = "message";

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
}

