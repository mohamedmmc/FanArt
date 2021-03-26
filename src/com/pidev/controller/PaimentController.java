/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.CustomerListParams;
import static com.stripe.param.checkout.SessionCreateParams.ShippingAddressCollection.AllowedCountry.SC;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class PaimentController implements Initializable {

    @FXML
    private TextField num_compte_id;
    @FXML
    private TextField date_month;
    @FXML
    private TextField cvc;
    @FXML
    private TextField date_year;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void onCliqPayer() throws AWTException {
        try{
            Stripe.apiKey = "sk_test_51IYHfGBQ0LLhBexiKiPzJjHM7f7z3koVIrDiiEr4hfUu35iV558XKAIZIiY3Xbm9tkF6zCn0fEjTXRpt4aIYmpww00p9s6z11h";
            Customer a = Customer.retrieve("cus_JAdxOt3MYaPCuU                                                                                    ");
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
            System.out.println(gson.toJson(token));
            
            Map<String,Object> chargeParam = new HashMap<String, Object>();
            chargeParam.put("amount", 3.00);
            chargeParam.put("currency", "usd");
            chargeParam.put("source", token.getId());
             
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
