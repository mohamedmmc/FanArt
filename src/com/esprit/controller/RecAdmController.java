/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import com.esprit.dao.recedao;
import com.esprit.dao.recedao;
import com.esprit.dao.recpdao;
import com.esprit.utilis.MailSender;
        
import com.esprit.entity.recevent;        
import com.esprit.entity.recprod;
import com.esprit.utilis.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
//import com.esprit.entity.recprod;

/**
 * FXML Controller class
 *
 * @author ASUS GL703VD
 */
public class RecAdmController implements Initializable {

    @FXML
    private TableView<recevent> recev;
    @FXML
    private TableColumn<recevent, String> neve;
    @FXML
    private TableColumn<recevent, String> eemail;
    @FXML
    private TableColumn<recevent, String> eracl;
    @FXML
    private TableColumn<recevent, String> esta;
  ListD data = new ListD();
    @FXML
    private TextArea recc;
    @FXML
    private Label rep;
    @FXML
    private TextField em;
    @FXML
    private TextField su;
    @FXML
    private Button env;
    @FXML
    private TextField ch;
    @FXML
    private TableView<?> recev1;
    @FXML
    private TableColumn<?, ?> neve1;
    @FXML
    private TableColumn<?, ?> eemail1;
    @FXML
    private TableColumn<?, ?> eracl1;
    @FXML
    private TableColumn<?, ?> esta1;
    MailSender ms = new MailSender();
        private Statement st;
    private ResultSet rs;
    @FXML
    private TextArea repp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO    /*recev.setItems(listdata.getevs());
        recev.setItems(data.getPersons());
        neve.setCellValueFactory(cell -> cell.
                getValue().geteventnomProperty());
        eemail.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
            eracl.setCellValueFactory(cell -> cell.
         getValue().getrecProperty());
          esta.setCellValueFactory(cell -> cell.
                getValue().getstaProperty());
          
          
          recev.setOnMouseClicked(event->{
              
        em.setText(String.valueOf(data.getPersons()
                .get(recev.getSelectionModel().getSelectedIndex())
                .getemail()));
        recc.setText(data.getPersons()
                .get(recev.getSelectionModel().getSelectedIndex())
                .getrec());
         su.setText(String.valueOf(data.getPersons()
                .get(recev.getSelectionModel().getSelectedIndex())
                .getId()));
        
        
                   
        
        
        // TODO
        });    
    
}

    @FXML
    private void send(MouseEvent event) {
        String ema= em.getText();
        String sub= su.getText();
        String re= repp.getText();
        String recev=recc.getText();
        System.out.println(re);
                   if (sub.isEmpty() || re.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veillez remplir les champs");
                alert.show();

            }
                    else 
                    {
                        try {
                ms.Recadm(ema,sub,re);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
       
          
                String req = "select * from recevent where reclevent= '" + recev + "'";
                try {

                    rs = st.executeQuery(req);

                    if (rs.next()) {
                        int id = rs.getInt("recpid");
                recprod p = new recprod(id);
                recpdao pdao = recpdao.getInstance();
                pdao.updatest(p, "repondu");
                        
                        } 
                    
                } catch (Exception ex) {
                    Logger.getLogger(recpdao.class.getName()).log(Level.SEVERE, null, ex);
                }

          
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Envoyez");
                alert.show();  
                        
                    }
    }
}
