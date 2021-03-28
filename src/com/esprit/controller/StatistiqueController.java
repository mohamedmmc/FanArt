/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.StatistiqueService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> barchart;
    @FXML
    private NumberAxis ychart;
    @FXML
    private CategoryAxis xchar;
    @FXML
    private PieChart piechart;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_list;
    @FXML
    private Button btn_add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            StatistiqueService es;
            es = StatistiqueService.getInstance();
            LinkedHashMap<String, Integer> map = es.Selectstatevent();
            LinkedHashMap<String, Integer> map1 = es.SelectArtist();

            XYChart.Series set1 = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                set1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));

            }

            barchart.getData().addAll(set1);

            ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                piechartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));

            }

            piechart.setClockwise(true);
            piechart.setLegendSide(Side.TOP);
            piechart.setData(piechartData);
            piechart.setStartAngle(90);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_list.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListEvent.fxml"));
                Stage window = (Stage) btn_list.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_add.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/HomeEvent.fxml"));
                Stage window = (Stage) btn_add.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_acceuil.setOnAction((ActionEvent event1) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/Menu.fxml"));
                Stage window = (Stage) btn_acceuil.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(HomeEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    private void changescene(MouseEvent event) {
    }

}
