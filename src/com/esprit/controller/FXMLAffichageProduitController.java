/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceProduit;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Juka
 */
public class FXMLAffichageProduitController implements Initializable {

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
    @FXML
    private ImageView imageviewfxid;
    String pathimage="";
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
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updatetable();
        
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
                Logger.getLogger(FXMLAffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
              updatetable();
            
         
    }

    @FXML
    /* private void AddProduit(MouseEvent event) {
        try{
     Parent root= FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLInterfaceproduit.fxml"));
             Stage window =(Stage)addfxid.getScene().getWindow();
             window.setScene(new Scene(root));
     }
 catch (IOException e){
            System.err.println(e);
        }
    }*/

    private void close(MouseEvent event) {
    }

    @FXML
    private void getSelected(){
          index=listproduitfxid.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
       //fid_produitfxid.setText(id_produitfxid.getCellData(index));
       ftitrefxid.setText(titrefxid.getCellData(index));
       fdescriptionfxid.setText(descriptionfxid.getCellData(index));
       //imageviewfxid.setText(titrefxid.getCellData(index));
       fprixfxid.setText(prixfxid.getCellData(index).toString());
        
    }
    @FXML
    private void Modifier(MouseEvent event) {
       Produit p = new Produit(18/*parseInt(fid_produitfxid.getText())*/,ftitrefxid.getText(),11, fdescriptionfxid.getText(),pathimage, Float.parseFloat(fprixfxid.getText()));
            ServiceProduit pdao = ServiceProduit.getInstance();
            pdao.update(p);
            updatetable();
      
    }

    @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image","*.png"));
        List<File> f = fc.showOpenMultipleDialog(null);
        String xx="";
        for (File file:f)
        {
          xx= file.getAbsolutePath();
    }
         Image image = new Image(new FileInputStream(f.get(0)));
         imageviewfxid.setImage(image);
         pathimage=xx;
    }

    @FXML
    private void Insert(MouseEvent event) {
        Produit p = new Produit(ftitrefxid.getText(),11, fdescriptionfxid.getText(),pathimage, Float.parseFloat(fprixfxid.getText()));
            ServiceProduit pdao = ServiceProduit.getInstance();
            pdao.Insert(p);
            updatetable();
    }
}

    