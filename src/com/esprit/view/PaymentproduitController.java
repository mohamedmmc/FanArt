/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.view;

import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.Panier;
import com.esprit.entity.User;
import com.esprit.utilis.MailSender;
import com.esprit.utilis.pdf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.awt.SystemTray;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class PaymentproduitController implements Initializable {

    @FXML
    private TextField num_compte_id;
    @FXML
    private TextField date_month;
    @FXML
    private TextField date_year;
    @FXML
    private TextField cvc;
    @FXML
    private Button btn_valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_valider.setText(String.valueOf("Payer " + Session.getPrix_total_prduit() + ".00DT"));
        // TODO
    }

    @FXML
    private void onCliqPayer(MouseEvent event) throws InterruptedException {

        try {
            Stripe.apiKey = "sk_test_51IYHfGBQ0LLhBexiKiPzJjHM7f7z3koVIrDiiEr4hfUu35iV558XKAIZIiY3Xbm9tkF6zCn0fEjTXRpt4aIYmpww00p9s6z11h";
            Customer a = Customer.retrieve("cus_JDFzWx2FzcItg4");
            /*CustomerListParams params =CustomerListParams.builder().build();
            CustomerCollection customers =
  Customer.list(params);
            System.out.println(customers);*/
            Map<String, Object> cardParam = new HashMap<String, Object>();
            if (!(num_compte_id.getText().equals("") || date_month.getText().equals("") || date_year.getText().equals("") || cvc.getText().equals(""))) {
                cardParam.put("number", num_compte_id.getText());
                cardParam.put("exp_month", Integer.parseInt(date_month.getText()));
                cardParam.put("exp_year", Integer.parseInt(date_year.getText()));
                cardParam.put("cvc", cvc.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Please check your card information!");
                return;
            }
            Map<String, Object> tokenParam = new HashMap<String, Object>();
            tokenParam.put("card", cardParam);

            Token token = Token.create(tokenParam);

            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(token));
              pdf pdf =new pdf();
            pdf.creepdf();

            ServicePanier servicepanier = new ServicePanier();
            User user =new User();
            ServiceUser serviceuser = new ServiceUser();
            user = serviceuser.findBymail(Session.getId());
            Panier panier = new Panier();
            panier = servicepanier.displayByIdinvalide(Session.getId());;
            servicepanier.updatevalidite(panier);
            MailSender s = new MailSender();
            s.sendpdf(user.getEmail(),user.getNom()+" "+user.getPrenom(),panier.getId_panier());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("commande");
            alert.setHeaderText(null);
            alert.setContentText("commande passer avec succée");
            alert.show();
            Map<String, Object> chargeParam = new HashMap<>();
            chargeParam.put("amount",Session.getPrix_total_prduit()*100 );
            chargeParam.put("currency", "eur");
            chargeParam.put("source", token.getId());
            if (SystemTray.isSupported()) {
                // TrayIconDemo td = new TrayIconDemo();

            } else {
                System.err.println("Erreur!!!!");
            }
            Charge.create(chargeParam);

        } catch (StripeException e) {
            System.out.println(e.getMessage());
        }
    }
}
