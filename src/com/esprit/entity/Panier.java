/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;

/**
 *
 * @author wiemhjiri
 */
public class Panier {
    
    private int id_panier;
    private int id_user;
    private String Validite;

    public Panier() {
    }

    public Panier(int id_user,String Validite) {
        this.id_user = id_user;
        this.Validite = Validite;
    }

    public Panier(int id_panier, int id_user, String Validite) {
        this.id_panier = id_panier;
        this.id_user = id_user;
        this.Validite = Validite;
    }

    public int getId_panier() {
        return id_panier;
    }

    public int getId_user() {
        return id_user;
    }

    public String getValidite() {
        return Validite;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setValidite(String Validite) {
        this.Validite = Validite;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", titre_user=" + id_user + ", Validite=" + Validite + '}';
    }

     @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id_panier);
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
        final Panier other = (Panier) obj;
        if (!Objects.equals(this.id_panier, other.id_panier)) {
            return false;
        }
        return true;
    }

    
 
    
    
    
}
