/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author splin
 */
public class User {

    public User(String nom, String prenom, String email, int numtel, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.type = type;
    }

    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private int numtel;

    public User(long l, String jennifer, String jenniferdomaincom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPhoto() {
        return photo;
    }

    public String getType() {
        return type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User(String photo) {
        this.photo = photo;
    }

    public User(String nom, String prenom, String mdp, String email, int numtel, String photo, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.numtel = numtel;
        this.photo = photo;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(String nom, String prenom, String email, int numtel, String photo, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.photo = photo;
        this.type = type;
    }
    private String photo;
    private String type;

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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", numtel=" + numtel + ", photo=" + photo + ", type=" + type + '}';
    }

 



}
