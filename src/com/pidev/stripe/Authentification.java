/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

/**
 *
 * @author Ben Gouta Monam
 */
public class Authentification {
    public static void main(String[] args) {
        try {
            Stripe.apiKey="sk_test_51IYHfGBQ0LLhBexiKiPzJjHM7f7z3koVIrDiiEr4hfUu35iV558XKAIZIiY3Xbm9tkF6zCn0fEjTXRpt4aIYmpww00p9s6z11h";
          //  CustomerListParams params =CustomerListParams.builder().build();
            Customer  customer =Customer.retrieve("cus_JAdxOt3MYaPCuU");
            System.out.println(customer);
        } catch (StripeException e) {
            System.out.println(e);
        }
    }
    
}
