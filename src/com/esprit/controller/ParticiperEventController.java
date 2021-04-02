/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.esprit.entity.ListData;
import com.esprit.dao.ParticiperService;
import com.esprit.utilis.MailSender;
import com.esprit.utilis.Newmailevent;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ParticiperEventController implements Initializable {

    @FXML
    private ImageView img_event;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField nb_billet;
    @FXML
    private TextField prix_total;
    @FXML
    private TextField num_compte_id;
    @FXML
    private TextField date_month;
    @FXML
    private TextField date_year;
    @FXML
    private TextField cvc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          btn_valider.setText(String.valueOf("Payer "+ListData.getPricetotal()+".00DT"));
        
    }    

    


 
    @FXML
    private void onCliqPayer() throws AWTException, InterruptedException {
           ParticiperService es;
           
            try {
                es = ParticiperService.getInstance();
                
                
                            
                            ListData.getParticiper().setNbr_reservation(ListData.getNombrebillet());
                           ListData.getParticiper().setPaiement(ListData.getPricetotal());
                           
                
                es.insert(ListData.getParticiper());
            } catch (SQLException ex) {
                Logger.getLogger
        (HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
           
        try{
            Stripe.apiKey = "sk_test_51IYHfGBQ0LLhBexiKiPzJjHM7f7z3koVIrDiiEr4hfUu35iV558XKAIZIiY3Xbm9tkF6zCn0fEjTXRpt4aIYmpww00p9s6z11h";
            Customer a = Customer.retrieve("cus_JAdxOt3MYaPCuU");
            /*CustomerListParams params =CustomerListParams.builder().build();
            CustomerCollection customers =
  Customer.list(params);
            System.out.println(customers);*/
            Map <String, Object> cardParam = new HashMap<String, Object>();
            if(!(num_compte_id.getText().equals("") || date_month.getText().equals("") || date_year.getText().equals("") || cvc.getText().equals(""))){
                cardParam.put("number", num_compte_id.getText());
                cardParam.put("exp_month", Integer.parseInt(date_month.getText()));
                cardParam.put("exp_year", Integer.parseInt(date_year.getText()));
                cardParam.put("cvc", cvc.getText());
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
            int n =ListData.getPricetotal()*100;
            chargeParam.put("amount",n);
            chargeParam.put("currency", "eur");
            chargeParam.put("source", token.getId());
             MailSender s =new MailSender();
             
            //  s.send("monam.bengouta@esprit.tn","monam");
            
             
             if (SystemTray.isSupported()) {
           // TrayIconDemo td = new TrayIconDemo();
                
            
        } else {
            System.err.println("Erreur!!!!");
        }             
          Charge.create(chargeParam);
            
        }catch(StripeException e){
            System.out.println(e.getMessage());   
        }
    }   
    }