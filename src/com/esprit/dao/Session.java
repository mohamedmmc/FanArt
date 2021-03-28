/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import java.io.File;

/**
 *
 * @author splin
 */
public class Session {
    private static String file;
    private static Integer id;
    private static int prix_total_prduit ;

    public static void setPrix_total_prduit(int prix_total_prduit) {
        Session.prix_total_prduit = prix_total_prduit;
    }

    public static int getPrix_total_prduit() {
        return prix_total_prduit;
    }
public static String filename;
    public Session() {
    }

    public static void setFile(String file) {
        Session.file = file;
    }

    public static String getFile() {
        return file;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Session.id = id;
    }
    
    
}
