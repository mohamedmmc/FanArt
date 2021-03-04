/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author TheDot
 */
public class Main extends Application {
    
    
    private Stage primaryStage;
    private Parent parentPage;
    
    @Override
   public void start(Stage primaryStage) throws IOException {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        
        parentPage = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
        Scene scene = new Scene(parentPage);
        scene.setFill(Color.TRANSPARENT);
        
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage.setScene(scene);
        //this.primaryStage.showAndWait();
        this.primaryStage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
