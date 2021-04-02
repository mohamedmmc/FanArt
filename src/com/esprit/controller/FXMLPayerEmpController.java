/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.view.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author saif
 */
public class FXMLPayerEmpController implements Initializable {

    @FXML
    private Button btn_payer;
    @FXML
    private Button button11;
    @FXML
    private TextField num_compte_id;
    @FXML
    private TextField salaire;
    @FXML
    private Label nom_label;
    @FXML
    private Label tache_label;
    @FXML
    private Label prenom_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        nom_label.setText(Session.getS1());
        tache_label.setText(Session.getS3());
        prenom_label.setText(Session.getS2());
        
        btn_payer.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLListEmp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
            Stripe.apiKey = "sk_test_51IYHfGBQ0LLhBexiKiPzJjHM7f7z3koVIrDiiEr4hfUu35iV558XKAIZIiY3Xbm9tkF6zCn0fEjTXRpt4aIYmpww00p9s6z11h";
            Customer a = Customer.retrieve("cus_JAdxOt3MYaPCuU");
            /*CustomerListParams params =CustomerListParams.builder().build();
            CustomerCollection customers =
            Customer.list(params);
            System.out.println(customers);*/
            Map <String, Object> cardParam = new HashMap<String, Object>();
            String date_month="12";
            String date_year="2025";
            String cvc="123";
            if(!(num_compte_id.getText().equals("") || date_month.equals("") || date_year.equals("") || cvc.equals(""))){
                cardParam.put("number", num_compte_id.getText());
                cardParam.put("exp_month", Integer.parseInt(date_month));
                cardParam.put("exp_year", Integer.parseInt(date_year));
                cardParam.put("cvc", cvc);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please check your card information!");
                return;
            }
            Map <String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);
            
            Token token = Token.create(tokenParam);
            
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson);
                  
            
            Map<String,Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount",salaire.getText());
            chargeParam.put("currency", "eur");
            chargeParam.put("source", token.getId());
             //MailSender s =new MailSender();
            //  s.send("monam.bengouta@esprit.tn","monam");//,"chrit o khrit ffyh ");
            
             
             if (SystemTray.isSupported()) {
           // TrayIconDemo td = new TrayIconDemo();
                
            
        } else {
            System.err.println("Erreur!!!!");
        }             
          Charge.create(chargeParam);
            
        }catch(StripeException e){
            System.out.println(e.getMessage());   
        }
        });
        button11.setOnAction(event -> {

            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLMenu.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // TODO
    }    


    
}
