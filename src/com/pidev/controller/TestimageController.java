/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Ben Gouta Monam
 */
public class TestimageController implements Initializable {

    @FXML
    private ImageView imageviex;
    @FXML
    private Button btn_upload;
    String chaine2="C:\\xampp\\htdocs\\img\\events.jpg";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       final FileChooser fileChooser = new FileChooser();
        btn_upload.setOnAction(new EventHandler<ActionEvent>() {
                          @Override
                public void handle(final ActionEvent e) {
                    setExtFilters(fileChooser);
                    File file = fileChooser.showOpenDialog(null);
                    
                    if (file != null) {
                        openNewImageWindow(file);
                    }
                    
                    if (file != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(file.toString());
                File dest = new File("C:\\xampp\\htdocs\\img\\Image");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException er) {
                er.printStackTrace();
            }
        }
                    
                }
            });
        DropShadow shadow=new DropShadow();
   
        btn_upload.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{btn_upload.setEffect(shadow);});
        btn_upload.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)->{btn_upload.setEffect(null);});
       
        
        try {
            imageviex.setImage(new Image(new FileInputStream("C:\\xampp\\htdocs\\img\\events.jpg")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestimageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }    
       private void openNewImageWindow(File file){
        Stage secondStage = new Stage();
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        
        MenuItem menuItem_Save1 = new MenuItem("Enregistrer");
 
        MenuItem menuItem_Save3 = new MenuItem("Quitter");
        menuFile.getItems().addAll(menuItem_Save1,menuItem_Save3);
        menuBar.getMenus().addAll(menuFile);
        
        Label name = new Label(file.getName());
        Image imageu = new Image(file.toURI().toString());
        ImageView image2 = new ImageView();
        imageviex.setImage(imageu);
        
        menuItem_Save1.setOnAction(new EventHandler<ActionEvent>(){
         @Override
            public void handle(ActionEvent event) {

               secondStage.close();
            }
        
        });
        
   
        
        menuItem_Save3.setOnAction(new EventHandler<ActionEvent>(){
         @Override
            public void handle(ActionEvent event) {

               secondStage.close();
            }
        
        });
        
   
        
           
           
           
          final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(name, image2);
         
        image2.setFitHeight(400);
        image2.setPreserveRatio(true);
        image2.setImage(imageu);
        image2.setSmooth(true);
        image2.setCache(true);
         
        Scene scene = new Scene(new VBox(),500, 500);
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, vbox);
         
        secondStage.setTitle(file.getName());
        secondStage.setScene(scene);
        secondStage.show();
        chaine2=file.getName();

}
}

