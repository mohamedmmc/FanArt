/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.entity;
import com.esprit.entity.Panier;
import com.esprit.entity.Produit;


/**
 *
 * @author Juka
 */
public class Panier_elem {
    public int id_panier;

    public Panier_elem() {
    }

    public Panier_elem(int id_produit, int quantite) {
        this.id_produit = id_produit;
        this.quantite = quantite;
    }
    

    public Panier_elem(int id_panier, int id_produit, int quantite) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_produit() {
        return id_produit;
    }
    private int id_produit;

    public int getQuantite() {
        return quantite;
    }
    private int quantite;

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier_elem{" + "id_panier=" + id_panier + ", id_produit=" + id_produit + ", quantite=" + quantite + '}';
    }
    
}
