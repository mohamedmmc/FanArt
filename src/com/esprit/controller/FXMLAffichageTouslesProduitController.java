/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ListData;
import com.esprit.dao.ServiceProduit;
import com.esprit.dao.Session;
import com.esprit.entity.Produit;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FXMLAffichageTouslesProduitController implements Initializable {

    @FXML
    private TableView<Produit> listproduitfxid;
    @FXML
    private TableColumn<Produit, String> id_produitfxid;
    @FXML
    private TableColumn<Produit, String> titrefxid;
    @FXML
    private TableColumn<Produit, String> descriptionfxid;
    @FXML
    private TableColumn<Produit, Float> prixfxid;
    @FXML
    private TableColumn<Produit, String> artistefxid;
    @FXML
    private TableColumn<Produit, Image> imagefxid;
    private Button addfxid;
    int index=-1;
    double xx,y;
    @FXML
    private ImageView imageviewfxid;
    @FXML
    private Button AjouterProduitFxid;
    @FXML
    private TextField ftitrefxid;
    @FXML
    private TextField fprixfxid;
    @FXML
    private TextArea fdescriptionfxid;
    String x="";
    @FXML
    private TextField fid_produitfxid;
    String pathimage;
    String filename;
    String stringfinal;
    File source;
    File dest;
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            
        
        updatetable();
        makeDragable();
        });
    } 
    private void makeDragable() {

        parent.setOnMousePressed(((event) -> {
            xx = event.getSceneX();
            y = event.getSceneY();
        }));
        parent.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - xx);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        parent.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        parent.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }
    public void updatetable()
    {
     ListData l =new ListData();
    ObservableList<Produit> d=l.getProduit();
        id_produitfxid.setCellValueFactory(new PropertyValueFactory<>("id"));
         titrefxid.setCellValueFactory(new PropertyValueFactory<>("titre"));
          descriptionfxid.setCellValueFactory(new PropertyValueFactory<>("description"));
           prixfxid.setCellValueFactory(new PropertyValueFactory<>("prix"));
           artistefxid.setCellValueFactory(new PropertyValueFactory<>("artiste"));
          // imagefxid.setCellValueFactory(new PropertyValueFactory<>(setImage("image")));
           /* Image image = new Image(new FileInputStream(f.get(0)));
         imageviewfxid.setImage(image);
         pathimage=x;*/
        listproduitfxid.setItems(d);
    }
    @FXML
        private void deleteRowFromTable(MouseEvent event) {
             Produit p=listproduitfxid.getSelectionModel().getSelectedItems().get(0);
            ServiceProduit es;
            try {
                es = ServiceProduit.getInstance();
                es.delete(p);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLAffichageTouslesProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
              updatetable();
            
         
    }


    @FXML
    private void getSelected(){
          index=listproduitfxid.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
       fid_produitfxid.setText( String.valueOf(id_produitfxid.getCellData(index)));
       ftitrefxid.setText(titrefxid.getCellData(index));
       fdescriptionfxid.setText(descriptionfxid.getCellData(index));
       //imageviewfxid.setText(titrefxid.getCellData(index));
       fprixfxid.setText(prixfxid.getCellData(index).toString());
        
    }
    @FXML
    private void Modifier(MouseEvent event) {
       Produit p = new Produit(parseInt(fid_produitfxid.getText()),ftitrefxid.getText(),11, fdescriptionfxid.getText(),pathimage, Float.parseFloat(fprixfxid.getText()));
            ServiceProduit pdao = ServiceProduit.getInstance();
            pdao.update(p);
            updatetable();
      
    }

    @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException {
        FileChooser f = new FileChooser();
        String img;
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageviewfxid.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index= pathimage.lastIndexOf('\\');
            if (index>0)
            {
                filename = pathimage.substring(index+1);
            }
            source = new File(pathimage);
             dest = new File(System.getProperty("user.dir")+"\\src\\com\\esprit\\img\\" + filename);
        }
       /*FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File SelectedFile = fc.showOpenDialog(null);
        if (SelectedFile != null)
        {
            pathimage = SelectedFile.toString();
            //System.out.println(pathimage);
            int index= pathimage.lastIndexOf('\\');
            if (index>0)
            {
                filename = pathimage.substring(index+1);
            }
             source = new File(pathimage);
             dest = new File(System.getProperty("user.dir")+"\\src\\com\\esprit\\img\\" + filename);
//            System.out.println(dest);
//            System.out.println(source);
            stringfinal="/com/esprit/img/" + filename;
            Image image = new Image(stringfinal);
            imageviewfxid.setImage(image);
            
            //..\img\google.png
                    //C:\Users\splin\Documents\NetBeansProjects\FanArt\\com\esprit\img
            
            
        }*/
    }

    @FXML
    private void Insert(MouseEvent event) {
        try {
                FileUtils.copyFile(source, dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (ftitrefxid.getText().isEmpty() || fdescriptionfxid.getText().isEmpty() || fprixfxid.getText().isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Tout les champs sont obligatoires !");
                alert.show();
            }
        
        Produit p = new Produit(ftitrefxid.getText(),Session.getId(), fdescriptionfxid.getText(),stringfinal, Float.parseFloat(fprixfxid.getText()));
            ServiceProduit pdao = ServiceProduit.getInstance();
            pdao.Insert(p);
            updatetable();
    }
}


    