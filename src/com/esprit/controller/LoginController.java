/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.User;
import static com.esprit.entity.User.validate;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static org.openqa.grid.common.SeleniumProtocol.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author TheDot
 */
public class LoginController implements Initializable {

    private static String session;
   // private String current;

    @FXML
    AnchorPane parent;
    double x = 0, y = 0;

    @FXML
    private TextField email;
    @FXML
    private Button creationCompte;
    @FXML
    private TextField mdp;
    @FXML
    private Button connexion;
    @FXML
    private ImageView cross;
    @FXML
    private Button admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        makeDragable();

        creationCompte.setOnAction(event -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/CreateCompte.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        admin.setOnAction(event -> {
            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Admin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void makeDragable() {

        parent.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
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

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void verify(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        ServiceUser sp = new ServiceUser();
        User u = new User();
        
        if (email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login vide");
            alert.setHeaderText(null);
            alert.setContentText("Login vide");
            alert.show();
        } else if (mdp.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe vide");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe vide");
            alert.show();
        } else {
            if (validate(email.getText())) {
                if (sp.verify(email.getText(), mdp.getText())!=0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie");
                    
                    alert.show();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/Menu.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(loader.load()));
                        Session.setId(sp.verify(email.getText(), mdp.getText()));
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Mot de passe ou login incorrect");
                    alert.show();
                }
            }else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Email incorrect");
                    alert.show();
            }
        }
    }

    @FXML
    private void fb(ActionEvent event) {
                String domain = "https://localhost/fblocal/";
        String appId = "197623945102545";
        String appidsecret = "577564482e4db4474577db2e570dadb9";
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=email";
        
        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        //String accessToken;
        while(true){
        
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            //accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
            FacebookClient client = new DefaultFacebookClient(Version.LATEST);
               
            String loginDialogUrlString = client.getLoginDialogUrl(appId, domain, scopeBuilder);
  
            System.out.println(loginDialogUrlString);
            //accessToken ="EAAHZAuVpnjpoBAKUx3smusRvTUZAR7BaBt0vngXgEkcwx3puzwSZCLiK7B8ZBY0hZBSyyRhUzat52cv9v4mVPGpdvlB3jwHZBXBUPdlBTI9Oo3UVtTIQuCePOWCMd4xZCmYtampZAITATCj3ZCvrUhQJznC2R8ZAWykCfkbHnTYHaT2WrdB2XmZCzAMg6uAXoZAaHmZB9KZBTjqH6Ppv8R8SMS3q6mOhnhCx0ZBSqZAUiGEw5wi3xqetsuE7QyEB";
            //System.out.println(accessToken);
            
            
             User user = client.fetchObject("me",User.class);
                
                System.out.println(user.getEmail());
                
            
            }
        
        }
    }


}
