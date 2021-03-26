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
public class ModelTable2 {
      String numsalle,nbreplace,dispo;
      
     public ModelTable2( String numsalle, String nbreplace, String dispo) {
       
        this.numsalle = numsalle;
        this.nbreplace = nbreplace;
        this.dispo = dispo;
             
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
}
