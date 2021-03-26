/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class LocalisationController implements Initializable {

    String htLink ="https://www.google.tn/maps/place/Cin%C3%A9ma+Le+Palace/@35.830537,10.640642,18.6z/data=!4m5!3m4!1s0x13027574e1204b8d:0x9234659b9bd6ee8d!8m2!3d35.8306049!4d10.6405374?hl=fr";
    
    WebEngine engine ;
    @FXML
    private Pane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WebView webview = new WebView();
        webview.minWidth(1920);
        webview.prefWidth(1920);
        webview.minHeight(1080);
        webview.prefHeight(1080);
       
        engine = webview.getEngine();
        String content =
    "Hello World!";
       engine.loadContent(content, "text/html");
        pane.getChildren().add(webview);


    }    
    
}
