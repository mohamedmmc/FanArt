/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author splin
 */
public class recevent {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty eventnom;
    private SimpleStringProperty email;
    private SimpleStringProperty rec;
    private SimpleStringProperty status;
    

    public recevent() {
    }

    
    public recevent( String eventnom, String email, String rec) {
        
        this.eventnom = new SimpleStringProperty(eventnom);
        this.email = new SimpleStringProperty(email);
        this.rec = new SimpleStringProperty(rec);
        
    }
    
    public recevent( int id ) {
        
        this.id = new SimpleIntegerProperty(id);
      
        
    }

    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String geteventnom() {
        return eventnom.get();
    }

    public void seteventnom(String nom) {
        this.eventnom = new SimpleStringProperty(nom);
    }
    
    public String getsta() {
        return status.get();
    }

    public void setsta(String status) {
        this.status = new SimpleStringProperty(status);
    }

    public String getemail() {
        return email.get();
    }
    public SimpleStringProperty geteventnomProperty(){
        return eventnom;
    }
    public SimpleStringProperty getemailProperty(){
        return email;
    }
    public SimpleStringProperty getrecProperty(){
        return rec;
    }
        public SimpleIntegerProperty getidProperty(){
        return id;
    }

    
     public SimpleStringProperty getstaProperty(){
        return status;
    }
   
    public void setemail(String em) {
        this.email = new SimpleStringProperty(em);
    }
    
     public String getrec() {
        return rec.get();
    }

    public void setrec(String rc) {
        this.rec= new SimpleStringProperty(rc);
    }

    @Override
    public String toString() {
        return "recprod{" + "id=" + id.get() + ", eventnom=" + eventnom.get() + ", email=" + email.get() + ", rec=" + rec.get()+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final recevent other = (recevent) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}

