/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.controller;


import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import rec.utils.DB;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import rec.dao.recpdao;
import rec.entity.recprod;
import rec.Rec;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASUS GL703VD
 */
public class TestController implements Initializable {

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
    private ListView<Integer> listpro;
    @FXML
    private TextField lprech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int idd;
         DB cs=DB.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
         update();
           Fillist();
           
            recprodd.setOnAction(event -> {
            
            recprod p = new recprod(cmb.getValue(), Rec.usrr , rec.getText());
            recpdao pdao = recpdao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
        SendEmail();
                   Fillist();

       
        });

        
         
    }    
    private void update(){
       
        String req = "select * from produit";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {
           cmb.getItems().add(rs.getString("nom"));
        }
        
}
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
     private void Fillist(){
         ObservableList<Integer> m =FXCollections.observableArrayList(
          );;
       
        String req = "select * from recprod where email = '"+Rec.usrr+"' ";
    try {
        
        rs=st.executeQuery(req);
        while (rs.next())
        {
           int nom = rs.getInt("recpid");
             
           m.add(nom);
        }
        listpro.setItems(m);
        
}   
    catch (Exception ex)
    { 
        Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
     
     public void SendEmail()
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
                        InternetAddress.parse(Rec.usrr,false));
      msg.setSubject("Hello");
      msg.setText("How are you");
      msg.setSentDate(new Date());
      Transport.send(msg);
      System.out.println("Message sent.");
    }catch (MessagingException e){ 
      System.out.println("Erreur d'envoi, cause: " + e);
    }
  }

}}
