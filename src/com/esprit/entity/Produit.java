/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Produit {
    
    private int id;
    private String titre;
    private int artiste;
    private String description; 
    private String image;
    private float prix;

    public Produit() {
    }

    
    public Produit(int id, String titre, int artiste, String description, String image, float prix) {
        this.id = id;
        this.titre = titre;
        this.artiste = artiste;
        this.prix = prix;
        this.description=description;
        this.image=image;
    }

    public Produit( String titre, int artiste, String description, String image, float prix) {
        this.titre = titre;
        this.artiste = artiste;
        this.prix = prix;
        this.description=description;
        this.image=image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }
    

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getArtiste() {
        return artiste;
    }
     public void setArtiste(int artiste) {
        this.artiste = artiste;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
      public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", titre=" + titre + ", artiste=" + artiste + ", description=" + description + ", image=" + image + ", prix=" + prix + '}';
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
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
