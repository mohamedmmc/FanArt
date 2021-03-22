/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ben Gouta Monam
 */
public class Projetinteg extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/pidev/view/Menu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        }catch (IOException e){
            System.err.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
