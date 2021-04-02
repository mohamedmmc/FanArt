/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.EvenementService;
import com.esprit.dao.Session;
import com.esprit.entity.Evenement;
import com.esprit.entity.ListData;
import com.esprit.entity.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import com.esprit.utilis.MyListener1;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class ListEvenementController implements Initializable {

    @FXML
    private TextField findtext;
    @FXML
    private VBox chosenProduitCard;
    @FXML
    private Label titre;
    @FXML
    private Label price;
    @FXML
    private ImageView Img;
    @FXML
    private TextField nbplace;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane gridpane;
    Evenement event =new Evenement();
    private MyListener1 myListener;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
         EvenementService es = EvenementService.getInstance();
         List<String> titreevents = (List)es.ListEvent().stream().map((e) -> {
            return e.getTitre();
         }).collect(Collectors.toList());
         TextFields.bindAutoCompletion(this.findtext, titreevents);
         this.IListeEvenement();
         this.gridpane.removeEventFilter(EventType.ROOT, (event) -> {
         });
      } catch (SQLException var5) {
         Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, (String)null, var5);
      }
        
        
        
        
        
        
        
        
        
        
        
    }   
    private List<Evenement> getList(String text) throws SQLException {
      EvenementService es = EvenementService.getInstance();
      List list;
      if (text.isEmpty()) {
         list = es.ListEvent();
      } else {
         list = es.ListEventByChar(text);
      }

      return list;
   }

   private List<Evenement> FindListByChar(String t) throws SQLException {
      EvenementService es = EvenementService.getInstance();
      List<Evenement> list = es.ListEventByChar(t);
      return list;
   }

   private void IListeEvenement() throws SQLException {
      List list = new ArrayList(this.getList(this.findtext.getText()));
      
      int column = 0;
      int row = 1;
      if(list.size()>0){
         setChosenProduit((Evenement) list.get(0));
        }
         myListener = new MyListener1() {
                public void onClickListener(Evenement evenement) {
                    setChosenProduit(evenement);
                    event=evenement;
                }
         };


      try {
         for(int i = 0; i < list.size(); ++i) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("/com/esprit/view/ItemEvenement.fxml"));
            VBox cardbox = (VBox)fxmlLoader.load();
            ItemEvenementController itemevenementcontroller = (ItemEvenementController)fxmlLoader.getController();
            itemevenementcontroller.sendData((Evenement)list.get(i),myListener);
            
            if (column == 3) {
               column = 0;
               ++row;
            }

            this.gridpane.add(cardbox, column++, row);
            GridPane.setMargin(cardbox, new Insets(10.0D));
         }
      } catch (IOException var8) {
      }

   }

    @FXML
    private void Chercher(KeyEvent event) {
        this.gridpane.getChildren().clear();
      

      try {
         this.IListeEvenement();
      } catch (SQLException var3) {
         Logger.getLogger(ListEvenementController.class.getName()).log(Level.SEVERE, (String)null, var3);
      }
    }

    private void setChosenProduit(Evenement evenement) {
       
       titre .setText(evenement.getTitre());
        price.setText(evenement.getPrix()+".00 TND");
        Image image = new Image("http://"+evenement.getImage());
        Img.setImage(image);
        nbplace.setText("1");
        ListData.setPricetotal(evenement.getPrix()*Integer.parseInt(this.nbplace.getText()));
         ListData.getParticiper().setId_participant(Session.getId());
         ListData.getParticiper().setId_evenement(ListData.getEvenement().getId_evenement());

    } 

    @FXML
    private void reserver(MouseEvent event) {
          try {
         FXMLLoader fxmlloader = new FXMLLoader(this.getClass().getResource("/com/esprit/view/ParticiperEvent.fxml"));
         Parent root = (Parent)fxmlloader.load();
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.show();
         ListData.setNombrebillet(Integer.parseInt(nbplace.getText()));
             
//        ListData.getEvenement().setPrix(this.event.getPrix());
//         ListData.getEvenement().setPrix(Integer.parseInt(this.price.getText()));
//         ListData.getParticiper().setId_participant(Session.getId());
//         ListData.getParticiper().setId_evenement(ListData.getEvenement().getId_evenement());
      } catch (IOException var5) {
         Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, (String)null, var5);
      }
    }

    @FXML
    private void updatenbplace(KeyEvent event) {
       // ListData.setNombrebillet(Integer.parseInt(nbplace.getText()));
        //ListData.setPricetotal(Integer.parseInt(nbplace.getText())*Integer.parseInt(price.getText()));
        
        int nombreplace=(nbplace.getText().equals(""))?1:Integer.parseInt(nbplace.getText());
        int prixunitaire=Integer.parseInt(price.getText().substring(0,price.getText().indexOf(".")));
        prixunitaire*=nombreplace;
        ListData.setPricetotal(prixunitaire);
        System.out.println(nbplace.getText()+" xxxx "+price.getText().substring(0,price.getText().indexOf(".")));
    }

    
}
