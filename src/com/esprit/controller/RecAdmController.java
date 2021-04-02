/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.controller.ListP;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import com.esprit.dao.recedao;
import com.esprit.dao.recedao;
import com.esprit.dao.recpdao;
import com.esprit.utilis.MailSender;

import com.esprit.entity.recevent;
import com.esprit.entity.recprod;
import com.esprit.utilis.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
//import com.esprit.entity.recprod;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class RecAdmController implements Initializable {

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
    ListD data = new ListD();
    ListP data1 = new ListP();
    @FXML
    private TextArea recc;
    @FXML
    private Label rep;
    @FXML
    private TextField em;
    @FXML
    private TextField su;
    @FXML
    private Button env;
    @FXML
    private TableView<recprod> recev1;
    @FXML
    private TableColumn<recprod, String> neve1;
    @FXML
    private TableColumn<recprod, String> eemail1;
    @FXML
    private TableColumn<recprod, String> eracl1;
    @FXML
    private TableColumn<recprod, String> esta1;
    MailSender ms = new MailSender();
    private Statement st;
    private ResultSet rs;
    @FXML
    private TextArea repp;
    @FXML
    private TextField sta;
    @FXML
    private TextField chid;
    @FXML
    private TextArea recc1;
    @FXML
    private Label rep1;
    @FXML
    private TextField em1;
    @FXML
    private TextField su1;
    @FXML
    private TextArea repp1;
    @FXML
    private Button env1;
    @FXML
    private TextField sta1;
    @FXML
    private TextField chid1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO    /*recev.setItems(listdata.getevs());

        recev.setItems(data.getPersons());
        neve.setCellValueFactory(cell -> cell.
                getValue().geteventnomProperty());
        eemail.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
        eracl.setCellValueFactory(cell -> cell.
                getValue().getrecProperty());
        esta.setCellValueFactory(cell -> cell.
                getValue().getstaPropertyy());

        recev1.setItems(data1.getPersons());
        neve1.setCellValueFactory(cell -> cell.
                getValue().getprodnomProperty());
        eemail1.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
        eracl1.setCellValueFactory(cell -> cell.
                getValue().getrecProperty());
        esta1.setCellValueFactory(cell -> cell.
                getValue().getstaProperty());

        recev.setOnMouseClicked(event -> {
            int x = data.getPersons()
                    .get(recev.getSelectionModel().getSelectedIndex())
                    .getId();
            System.out.println(x);

            em.setText(String.valueOf(data.getPersons()
                    .get(recev.getSelectionModel().getSelectedIndex())
                    .getemail()));
            recc.setText(data.getPersons()
                    .get(recev.getSelectionModel().getSelectedIndex())
                    .getrec());
            chid.setText(String.valueOf(data.getPersons()
                    .get(recev.getSelectionModel().getSelectedIndex())
                    .getId()));

            sta.setText(data.getPersons()
                    .get(recev.getSelectionModel().getSelectedIndex())
                    .getsta());
            if (sta.getText().equals("repondu")) {
                env.setDisable(true);
            } else {
                env.setDisable(false);
            }
        });

        recev1.setOnMouseClicked(event -> {
            int y = data1.getPersons()
                    .get(recev1.getSelectionModel().getSelectedIndex())
                    .getId();
            System.out.println(y);

            em1.setText(String.valueOf(data1.getPersons()
                    .get(recev1.getSelectionModel().getSelectedIndex())
                    .getemail()));
            recc1.setText(data1.getPersons()
                    .get(recev1.getSelectionModel().getSelectedIndex())
                    .getrec());
            chid1.setText(String.valueOf(data1.getPersons()
                    .get(recev1.getSelectionModel().getSelectedIndex())
                    .getId()));

            sta1.setText(data1.getPersons()
                    .get(recev1.getSelectionModel().getSelectedIndex())
                    .getsta());
            if (sta1.getText().equals("repondu")) {
                env1.setDisable(true);
            } else {
                env1.setDisable(false);
            }

            // TODO
        });

    }

    @FXML
    private void send(MouseEvent event) {
        String ema = em.getText();
        int x = Integer.parseInt(chid.getText());
        String sub = su.getText();
        String re = repp.getText();
        String recev = recc.getText();
        System.out.println(re);
        if (sub.isEmpty() || re.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veillez remplir les champs");
            alert.show();

        } else {
            try {
                ms.Recadm(ema, sub, re);
                recevent p = new recevent(x);
                recedao pdao = recedao.getInstance();
                pdao.updatest(p, "repondu");

                System.out.println(p.getstaPropertyy());

                em.clear();
                chid.clear();
                su.clear();
                repp.clear();
                recc.clear();
                sta.clear();

                updatet();

            } catch (InterruptedException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Envoyez");
            alert.show();

        }
    }

    public void updatet() {
        data.clr();
        recev.setItems(data.getPersons());
        neve.setCellValueFactory(cell -> cell.
                getValue().geteventnomProperty());
        eemail.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
        eracl.setCellValueFactory(cell -> cell.
                getValue().getrecProperty());
        esta.setCellValueFactory(cell -> cell.
                getValue().getstaPropertyy());

    }

    public void updatep() {
        data1.clr();
        recev1.setItems(data1.getPersons());
        neve1.setCellValueFactory(cell -> cell.
                getValue().getprodnomProperty());
        eemail1.setCellValueFactory(cell -> cell.
                getValue().getemailProperty());
        eracl1.setCellValueFactory(cell -> cell.
                getValue().getrecProperty());
        esta1.setCellValueFactory(cell -> cell.
                getValue().getstaProperty());

    }

    /* public void se(){
     data.getevv(ch.getText());
     updatet();
     
     }*/
    @FXML
    private void sende(MouseEvent event) {
        String ema = em1.getText();
        int x = Integer.parseInt(chid1.getText());
        String sub = su1.getText();
        String re = repp1.getText();
        String recev = recc1.getText();
        System.out.println(re);
        if (sub.isEmpty() || re.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Veillez remplir les champs");
            alert.show();

        } else {
            try {
                ms.Recadm(ema, sub, re);
                recprod p = new recprod(x);
                recpdao pdao = recpdao.getInstance();
                pdao.updatest(p, "repondu");

                System.out.println(p.getstaProperty());

                em1.clear();
                chid1.clear();
                su1.clear();
                repp1.clear();
                recc1.clear();
                sta1.clear();

                updatep();

            } catch (InterruptedException ex) {
                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Envoyez");
            alert.show();

        }
    }

}
