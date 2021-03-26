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
public static String filenae;
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
