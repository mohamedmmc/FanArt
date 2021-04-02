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
public class ModelTable2 {
    int idsalle;
    String numsalle,nbreplace,desc;
      
public ModelTable2( int idsalle, String numsalle, String nbreplace, String desc) {
    this.idsalle =idsalle;
    this.numsalle = numsalle;
     this.nbreplace = nbreplace;
     this.desc = desc;       
    }

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int numsalle) {
        this.idsalle = idsalle;
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
     
    
}
