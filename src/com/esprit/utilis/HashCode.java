/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author splin
 */
public class HashCode {
    public static String generatedHash(String data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        byte[] hash = digest.digest(data.getBytes());
        return BytesToStringHex(hash);

    }

    public final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String BytesToStringHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];

        }
        return new String(hexChars);
    }
    
}
