package com.esprit.controller;

import com.esprit.dao.Session;
import com.esprit.entity.Evenement;
import com.esprit.entity.ListData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EvenementController implements Initializable {
   @FXML
   private HBox hbox;
   @FXML
   private ImageView image;
   @FXML
   private Label title;
   @FXML
   private Label desc;
   @FXML
   private Label local;
   @FXML
   private Label prix;
   @FXML
   private Button btn_participer;
   @FXML
//   private Button btn_supprimer;
   Evenement e = new Evenement();

   public void initialize(URL url, ResourceBundle rb) {
   }

   public void sendData(Evenement e) {
      Image img = new Image(this.getClass().getResourceAsStream(e.getImage()));
      this.image.setImage(img);
      this.title.setText(e.getTitre());
      this.desc.setText(e.getDescription());
      this.local.setText(e.getLocall());
      this.prix.setText(String.valueOf(e.getPrix()));
      ListData.getEvenement().setPrix(Integer.parseInt(this.prix.getText()));
      this.hbox.setId(e.getTitre());
      this.btn_participer.setId(String.valueOf(e.getId_evenement()));
//      this.btn_supprimer.setId(String.valueOf(e.getId_evenement()));
      String[] colors = new String[]{"B9E5EF", "BDB2FE", "DA3636", "36DAA3", "FF5056"};
      this.hbox.setStyle("-fx-background-color:#" + colors[(int)(Math.random() * (double)colors.length)] + ";-fx-background-radius: 15;-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0), 10, 0, 0, 10);");
   }

   public Evenement getData() {
      ListData.getEvenement().setId_evenement(Session.getId());
      return this.e;
   }

   @FXML
   private void loadpaiment(MouseEvent event) {
      try {
         FXMLLoader fxmlloader = new FXMLLoader(this.getClass().getResource("/com/esprit/view/ParticiperEvent.fxml"));
         Parent root = (Parent)fxmlloader.load();
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.show();
         ListData.getEvenement().setPrix(this.e.getPrix());
         ListData.getEvenement().setPrix(Integer.parseInt(this.prix.getText()));
         ListData.getParticiper().setId_participant(Session.getId());
         ListData.getParticiper().setId_evenement(ListData.getEvenement().getId_evenement());
      } catch (IOException var5) {
         Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

   }

//   @FXML
//   private void supprimerevent(MouseEvent event) {
//      try {
//         ListData.setEvenement(this.e);
//         EvenementService es = EvenementService.getInstance();
//         ListData.getEvenement().setId_evenement(Integer.parseInt(this.btn_supprimer.getId()));
//         System.out.println(ListData.getEvenement());
//         es.delete(ListData.getEvenement());
//      } catch (SQLException var3) {
//         Logger.getLogger(ListEventController.class.getName()).log(Level.SEVERE, (String)null, var3);
//      }
//
//   }
}
