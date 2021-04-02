/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

/**
 *
 * @author ranya
 */
public class ModelTable3 {
      String nbreplace,date_debut,date_fin,desc;
      int numsalle;
      
     public ModelTable3( int numsalle, String nbreplace, String date_debut,String date_fin,String desc) {
       
        this.numsalle = numsalle;
        this.nbreplace = nbreplace;
        this.date_debut = date_debut ;
        this.date_fin=date_fin;
        this.desc=desc;

             
    }

    public String getNbreplace() {
        return nbreplace;
    }

    public void setNbreplace(String nbreplace) {
        this.nbreplace = nbreplace;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getNumsalle() {
        return numsalle;
    }

    public void setNumsalle(int numsalle) {
        this.numsalle = numsalle;
    }

   

}
