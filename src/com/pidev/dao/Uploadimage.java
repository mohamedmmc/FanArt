/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.dao;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ben Gouta Monam
 */
public class Uploadimage {

    public static void main(String[] args)
            throws Exception {

        HttpURLConnection conn = null;
        BufferedReader br = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;

        InputStream is = null;
        OutputStream os = null;
        boolean ret = false;
        String StrMessage = "";
        String exsistingFileName = "C:\\Users\\Ben Gouta Monam\\Desktop\\trello.png";
        String fileName = "trello.png";

        String lineEnd = "";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;

        byte[] buffer;

        int maxBufferSize = 1 * 1024 * 1024;

        String responseFromServer = "";

        String urlString = "http://127.0.0.1/";

        try {
            //------------------ CLIENT REQUEST

            FileInputStream fileInputStream = new FileInputStream(new File(exsistingFileName));

            // open a URL connection to the Servlet 
            URL url = new URL(urlString);

            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();

            // Allow Inputs
            conn.setDoInput(true);

            // Allow Outputs
            conn.setDoOutput(true);

            // Don't use a cached copy.
            conn.setUseCaches(false);

            // Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"" + fileName + "\"" + lineEnd);
            //dos.writeBytes("Content-Disposition: form-data; name="upload";"+ " filename="" + exsistingFileName +""" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            System.out.println("write complete........");
            // close streams

            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            System.out.println("From ServletCom CLIENT REQUEST:" + ex);
        } catch (IOException ioe) {
            System.out.println("From ServletCom CLIENT REQUEST:" + ioe);
        } catch (Exception e) {
            System.out.println("From ServletCom CLIENT REQUEST:" + e);
        }

    }

}
