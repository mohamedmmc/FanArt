/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;

/**
 *
 * @author saif
 */
public class Employe {
    Integer id;
    String nom;
    String prenom;
    String tache;
    String disponible;
    int age;
    int mobile;
    float salaire;
    String num_carte;
    int num_salle;

    public Employe() {
    }

    public Employe(String nom, String prenom, String tache, int age, int mobile, float salaire, String num_carte) {
        this.nom = nom;
        this.prenom = prenom;
        this.tache = tache;
        this.age = age;
        this.mobile = mobile;
        this.salaire = salaire;
        this.num_carte = num_carte;
    }

    public Employe(String nom, String prenom, String tache, int mobile, int num_salle) {
        this.nom = nom;
        this.prenom = prenom;
        this.tache = tache;
        this.mobile = mobile;
        this.num_salle = num_salle;
    }

    public Employe(Integer id, String nom, String prenom, String tache, String disponible, int mobile, int num_salle) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tache = tache;
        this.disponible = disponible;
        this.mobile = mobile;
        this.num_salle = num_salle;
    }
    

    
    public Employe(Integer id, String tache, String disponible) {
        this.id = id;
        this.tache = tache;
        this.disponible = disponible;
    }
    
    
    

    public Employe(String nom, String prenom, String tache, int age, int mobile, float salaire, String num_carte, int num_salle) {
        this.nom = nom;
        this.prenom = prenom;
        this.tache = tache;
        this.age = age;
        this.mobile = mobile;
        this.salaire = salaire;
        this.num_carte = num_carte;
        this.num_salle = num_salle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public int getAge() {
        return age;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public int getNum_salle() {
        return num_salle;
    }

    public void setNum_salle(int num_salle) {
        this.num_salle = num_salle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tache=" + tache + ", disponible=" + disponible + ", age=" + age + ", mobile=" + mobile + ", salaire=" + salaire + ", num_carte=" + num_carte + ", num_salle=" + num_salle + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    

}