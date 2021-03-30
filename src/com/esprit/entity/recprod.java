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
public class recprod {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty prodnom;
    private SimpleStringProperty email;
    private SimpleStringProperty rec;
    private SimpleStringProperty status;
    

    public recprod() {
    }

    
    public recprod( String prodnom, String email, String rec) {
        
        this.prodnom = new SimpleStringProperty(prodnom);
        this.email = new SimpleStringProperty(email);
        this.rec = new SimpleStringProperty(rec);
        
    }
    
     public recprod( int id ) {
        
        this.id = new SimpleIntegerProperty(id);
      
        
    }
     
     public recprod( String rec ) {
        
      this.rec = new SimpleStringProperty(rec);
      
        
    }

    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getprodnom() {
        return prodnom.get();
    }

    public void setprodnom(String nom) {
        this.prodnom = new SimpleStringProperty(nom);
    }

    public String getemail() {
        return email.get();
    }
    public SimpleStringProperty getprodnomProperty(){
        return prodnom;
    }
    public SimpleStringProperty getemailProperty(){
        return email;
    }
    public SimpleStringProperty getrecProperty(){
        return rec;
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
        return "recprod{" + "id=" + id.get() + ", prodnom=" + prodnom.get() + ", email=" + email.get() + ", rec=" + rec.get()+ '}';
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
        final recprod other = (recprod) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}

