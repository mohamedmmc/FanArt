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
public class ModelTable {
    int idSalle;
    String numsalle,nbreplace,desc,dated,datef;

    public ModelTable(int idSalle,String numsalle, String nbreplace, String desc, String dated,String datef) {
       this.idSalle=idSalle;
        this.numsalle = numsalle;
        this.nbreplace = nbreplace;
        this.desc = desc;
        this.dated = dated;
        this.datef = datef;
       
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

   
    
}
