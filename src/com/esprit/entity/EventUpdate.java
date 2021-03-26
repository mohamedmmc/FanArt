/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import com.esprit.entity.Evenement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author splint
 */
public class EventUpdate {
    public static List<Evenement> liste=new ArrayList<Evenement>();
    public void add (Evenement e){
        liste.add(e);
        
    }
    
}
