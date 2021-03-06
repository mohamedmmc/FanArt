/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author saif
 */
public class Employe {
    Integer id;
    String nom;
    String prenom;
    String tache;
    Integer salle;
    String mobile;

    public Employe(String nom, String prenom, String tache, Integer salle, String mobile) {
        this.nom = nom;
        this.prenom = prenom;
        this.tache = tache;
        this.salle = salle;
        this.mobile = mobile;
    }

    Employe(String gouider, String saif, String technicien, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Employe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTache() {
        return tache;
    }

    public int getSalle() {
        return salle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
