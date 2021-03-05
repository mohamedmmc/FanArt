/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

/**
 *
 * @author splin
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private int numtel;

    public User(String nom, String prenom, String mdp, String email, int numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.numtel = numtel;
    }

    public User(int id, String nom, String prenom, String mdp, String email, int numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.numtel = numtel;
    }

    public User(String mdp, String email) {
        this.mdp = mdp;
        this.email = email;
    }

    public User() {
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

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public int getNumtel() {
        return numtel;
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

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", numtel=" + numtel + '}';
    }

}
