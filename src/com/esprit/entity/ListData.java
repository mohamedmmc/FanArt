/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import com.esprit.dao.EvenementService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author splin
 */
public class ListData {

    private ObservableList<Evenement> events = FXCollections.observableArrayList();
    private static Evenement evenement = new Evenement();
    private static Participer participer = new Participer();
    private static User user=new User();
   

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ListData.user = user;
    }

    public static Participer getParticiper() {
        return participer;
    }

    public static void setParticiper(Participer participer) {
        ListData.participer = participer;
    }

    public static Evenement getEvenement() {
        return evenement;
    }

    public static void setEvenement(Evenement evenement) {
        ListData.evenement = evenement;
    }

    public ListData() {
        try {

            EvenementService pdao = EvenementService.getInstance();
            events = pdao.displayAll();
            System.out.println(events);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public ObservableList<Evenement> getEvents() {
        return events;
    }

}
