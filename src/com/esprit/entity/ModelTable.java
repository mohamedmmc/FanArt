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
public class ModelTable {
    int idsalle;
    String numsalle,nbreplace,dispo,date;

    public ModelTable(int idsalle, String numsalle, String nbreplace, String dispo, String date) {
        this.idsalle =idsalle;
        this.numsalle = numsalle;
        this.nbreplace = nbreplace;
        this.dispo = dispo;
        this.date = date;
       
    }

    ModelTable(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNumsalle() {
        return numsalle;
    }

    public void setNumsalle(String numsalle) {
        this.numsalle = numsalle;
    }

    public String getNbreplace() {
        return nbreplace;
    }

    public void setNbreplace(String nbreplace) {
        this.nbreplace = nbreplace;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }
    
  
    
}
