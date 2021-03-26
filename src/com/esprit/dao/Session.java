/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

/**
 *
 * @author splin
 */
public class Session {
    private static Integer id;

    public Session() {
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Session.id = id;
    }
    
    
}
