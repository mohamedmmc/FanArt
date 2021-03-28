/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

/**
 *
 * @author splin
 */
public class Session {
    private String mails;

    public Session() {
    }

    @Override
    public String toString() {
        return mails;
    }

    public Session(String mails) {
        this.mails = mails;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }
    
}
