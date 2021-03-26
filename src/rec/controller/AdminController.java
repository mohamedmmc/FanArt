/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rec.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rec.controller.ListData;
import rec.entity.recprod;
import rec.entity.recevent;

/**
 * FXML Controller class
 *
 * @author ASUS GL703VD
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<recevent> recev;
    @FXML
    private TableColumn<recevent, String> neve;
    @FXML
    private TableColumn<recevent, String> eemail;
    @FXML
    private TableColumn<recevent, String> eracl;
    @FXML
    private TableColumn<recevent, String> esta;
    
    @FXML
    private TextField ema;
    @FXML
    private TextField obj;
    @FXML
    private TextArea recll;
    @FXML
    private TextArea cont;

     ListData listdata = new ListData();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         /*recev.setItems(listdata.getevs());
        neve.setCellValueFactory(cell -> cell.
                getValue().geteventnomProperty());
        eemail.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
            eracl.setCellValueFactory(cell -> cell.
         getValue().getrecProperty());
          esta.setCellValueFactory(cell -> cell.
                getValue().getstaProperty());*/
                   
        
        
        // TODO
    }    
    
}
