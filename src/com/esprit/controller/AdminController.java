/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.ListData;
import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServiceUser;
import com.esprit.entity.User;
import com.esprit.utilis.Connexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.esprit.dao.Session;
import com.esprit.dao.recedao;
import com.esprit.dao.recpdao;
import com.esprit.entity.Evenement;
import com.esprit.entity.Produit;
import com.esprit.entity.recevent;
import com.esprit.entity.recprod;
import com.esprit.utilis.MailSender;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author splin
 */
public class AdminController implements Initializable {
     private Stage primaryStage;
    private Parent parentPage;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private TableView<User> userTab;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> numerotel;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label numerolabel;
    @FXML
    private Label typelabel;
    @FXML
    private ImageView cross;

    String imagepath, emaildel;
    @FXML
    private AnchorPane parent;
    double x = 0, y = 0;
    @FXML
    private ImageView imagee;
    @FXML
    private TextField chercher;
    @FXML
    private VBox vue;
    @FXML
    private AnchorPane parentt;
    @FXML
    private TableColumn<ModelTable2, ?> numsalle;
    private TextField place;
    @FXML
    private TableColumn<ModelTable2, ?> desc;
    private String pathimage;
    private ImageView imgg;
    private String filename;

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
    MailSender ms = new MailSender();
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
    private TextArea repp;
    @FXML
    private Button env;
    @FXML
    private TextField sta;
    @FXML
    private TextField chid;
    int index = -1;
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
    private ImageView imageviewfxid;
    @FXML
    private TableView<ModelTable2> tab;
    @FXML
    private TableColumn<ModelTable2, ?> idsalle;
    @FXML
    private TableColumn<ModelTable2, ?> nbreplace;
    @FXML
    private Button suppsalle;
    @FXML
    private Button ajoutsalle;
    @FXML
    private Button reservation;
ObservableList<ModelTable2> ob =FXCollections.observableArrayList();
    @FXML
    
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String>  desc1;
    @FXML
    private TableColumn<Evenement, String>  date_debut;
    @FXML
    private TableColumn<Evenement, String>  date_fin;
    @FXML
    private TableColumn<Evenement, String>  salle;
    @FXML
    private TableColumn<Evenement, Integer>  nbplace;
    @FXML
    private TableColumn<Evenement, Integer>  price;
    @FXML
    private TableView<Evenement> lsitevent;
    @FXML
    private TextField titre1;
    @FXML
    private TextField description;
    @FXML
    private DatePicker date_debut1;
    @FXML
    private DatePicker date_fin1;
    @FXML
    private TextField nbplace1;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> artist;
    @FXML
    private ComboBox<String> local;
    @FXML
    private Button btn_enregistrer;
    @FXML
    private Button btn_reinitialiser;
    @FXML
    private Button bt_upload_img;
    @FXML
    private Button btn_supp;
    @FXML
    private Text idselectedevent;
    @FXML
    private Button btnAjout;
    String mail_contenu="";
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnPayer;
    @FXML
    private Button btnPlacer;
    public String verifier() {
        updatetable();
        if (numsalle.getText().equals("") || place.getText().equals("") || desc.getText().equals("")) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            
            btnPayer.setOnAction(event -> {

            try {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLPayerEmp.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    
        } catch(Exception e) {
        e.printStackTrace();
        }
        });
            btnAffiche.setOnAction(event -> {
            try {
       
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLListEmp.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    
        } catch(Exception e) {
        e.printStackTrace();
        }
        });
        btnPlacer.setOnAction(event -> {

            try {
        
Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLPlacerEmp.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    
        } catch(Exception e) {
        e.printStackTrace();
        }
        });
        
        btnAjout.setOnAction(event -> {

            try {
Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/FXMLDocument.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    
        } catch(Exception e) {
        e.printStackTrace();
        }
        });
            imageviewfxid.setImage(new Image("http://localhost:8080/img/user.png"));
            updatetable();
            makeDragable();
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
                //System.out.println(x);

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
                    su.clear();
                    repp.clear();
                }
            });
 try {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
             
        ResultSet rs=con.createStatement().executeQuery("select * from salle");
        while (rs.next())  {
            ob.add(new ModelTable2(rs.getInt("idsalle"), rs.getString("numsalle"), rs.getString("nbreplace"),rs.getString("description")));
            }
        
        } catch (Exception ex) {
        Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            idsalle.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
            numsalle.setCellValueFactory(new PropertyValueFactory<>("numsalle"));
            nbreplace.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
         

            tab.setItems(ob);
        // TODO
         
            recev1.setOnMouseClicked(event -> {
                int y = data1.getPersons()
                        .get(recev1.getSelectionModel().getSelectedIndex())
                        .getId();
                //  System.out.println(y);

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
                    su1.clear();
                    repp1.clear();
                }

                // TODO
            });
            ServiceProduit es = new ServiceProduit();
            ServiceUser suu = new ServiceUser();
            //Image im = new Image(getClass().getResourceAsStream("/com/esprit/img/guestuser.png"));
            imagee.setFitHeight(150);
            imagee.setPreserveRatio(true);
            listproduitfxid.setOnMouseClicked((e) -> {
                //System.out.println("http://" + es.displayAll().get(userTab.getSelectionModel().getSelectedIndex()).getImage());
                imageviewfxid.setImage(new Image("http://" + es.displayAll().get(listproduitfxid.getSelectionModel().getSelectedIndex()).getImage()));

            });
            userTab.setOnMouseClicked((event) -> {
                //vue.getChildren().remove(imagee);
                String imageSource = "http://" + suu.displayAllList().get(userTab.getSelectionModel().getSelectedIndex()).getPhoto();
                System.out.println(imageSource);
                Image imggg = new Image(imageSource);
                imagee.setImage(imggg);
                /*Image img = new Image(getClass().getResourceAsStream(su.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getPhoto()));
                imagee.setImage(img);*/
                //System.out.println(String.valueOf(su.displayAllList().get(userTab.getSelectionModel().getSelectedIndex()).getNom()));
                nomlabel.setText(suu.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getNom());
                prenomlabel.setText(suu.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getPrenom());
                emaillabel.setText(suu.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getEmail());
                typelabel.setText(suu.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getType());
                numerolabel.setText(String.valueOf(suu.displayAllList()
                        .get(userTab.getSelectionModel().getSelectedIndex())
                        .getNumtel()));

            });
            try {
                // TODO

                ObservableList<User> list = suu.getUserList();
                nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));
                numerotel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
                type.setCellValueFactory(new PropertyValueFactory<>("type"));

                userTab.setItems(list);
            } catch (SQLException ex) {

            }
        });
    }

    public void updatetable() {
        ServiceProduit es = new ServiceProduit();
        ListData l = new ListData();
        ObservableList<Produit> d = l.getProduit();
        id_produitfxid.setCellValueFactory(new PropertyValueFactory<>("id"));
        titrefxid.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionfxid.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixfxid.setCellValueFactory(new PropertyValueFactory<>("prix"));
        artistefxid.setCellValueFactory(new PropertyValueFactory<>("artiste"));
        // imagefxid.setCellValueFactory(new PropertyValueFactory<>(setImage("image")));

        listproduitfxid.setItems(d);
        

    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) cross.getScene().getWindow();
        stage.close();
    }

    private void makeDragable() {

        parentt.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));
        parentt.setOnMouseDragged(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));
        parentt.setOnDragDone(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
        parentt.setOnMouseReleased(((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

    }

    @FXML
    private void retour(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "AdminController{" + "emaillabel=" + emaillabel + '}';
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("Voulez vous vraiment supprimer cet utilisateur " + nomlabel.getText() + " " + prenomlabel.getText() + "?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceUser su = new ServiceUser();
            try {
                su.Deluser(emaillabel.getText());
                //System.out.println(emaillabel.toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supprimé");
                alert.setHeaderText(null);
                alert.setContentText("Supprimé avec succés !");
                alert.show();
                userTab.setItems(su.getUserList());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur non supprimé !");
            }

        } else {
            alert2.close();
        }

    }

    @FXML
    private void chercher(KeyEvent event) throws SQLException {
        ServiceUser su = new ServiceUser();
        su.chercher(chercher.getText());
        //tableau.setItems(su.getUserList());
        userTab.setItems(su.getUserListfiltered(chercher.getText()));
    }

    private void Annuler(ActionEvent event) {
        numsalle.setText("");
        place.setText("");
        desc.setText("");
    }

    private void add(ActionEvent event) {
        Connection con;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();

        String num = numsalle.getText();
        String nbreplace = place.getText();
        String descri = desc.getText();

        String query = "insert into salle (numsalle,nbreplace,description) values (?,?,?)";

        if (verifier() == "false") {
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, num);
                ps.setString(2, nbreplace);
                ps.setString(3, descri);
                ps.execute();

                JOptionPane.showMessageDialog(null, "La salle a bien été ajouté ,vous pouvez en mettre un autre.. ");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        } else {
            //ImageIcon img1 = new ImageIcon("C:\\Users\\ranya\\Desktop\\attention.png");
            JOptionPane.showMessageDialog(null, "Veuillez remplir tout les champs ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ajoutImg(ActionEvent event) {
        FileChooser f = new FileChooser();
        String imggg;
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            imggg = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(imggg);

            imgg.setImage(i);
            pathimage = fc.toString();
            //System.out.println(imageviewfxid);
            int index = pathimage.lastIndexOf('\\');
            if (index > 0) {
                filename = pathimage.substring(index + 1);
            }
            //source = new File(pathimage);
            // dest = new File(System.getProperty("user.dir") + "\\src\\com\\esprit\\img\\" + filename);
        }
        imgg.setFitHeight(94);
        imgg.setFitWidth(94);
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

    @FXML
    private void send(MouseEvent event) {
        String ema = em.getText();
        int x = Integer.parseInt(chid.getText());
        String sub = su.getText();
        String re = repp.getText();
        String recev = recc.getText();
        //System.out.println(re);
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

                // System.out.println(p.getstaPropertyy());
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

    @FXML
    private void sende(MouseEvent event) {
        String ema = em1.getText();
        int x = Integer.parseInt(chid1.getText());
        String sub = su1.getText();
        String re = repp1.getText();
        String recev = recc1.getText();
        //System.out.println(re);
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

                //System.out.println(p.getstaProperty());
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
            alert.setContentText("Envoyé");
            alert.show();

        }

    }

    @FXML
    private void deleteRowFromTable(MouseEvent event) {
        Produit p = listproduitfxid.getSelectionModel().getSelectedItems().get(0);
        ServiceProduit es;
        try {
            es = ServiceProduit.getInstance();
            es.delete(p);
            imageviewfxid.setImage(new Image("http://localhost:8080/img/user.png"));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAffichageTouslesProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updatetable();
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();

        ModelTable2 song = tab.getSelectionModel().getSelectedItem();
        System.out.println(song);

        if (song != null) {
        int n = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'élément ? " , "SVP select", JOptionPane.YES_NO_OPTION);
        
        if (n==0) {  
        PreparedStatement statement = con.prepareStatement("DELETE FROM salle WHERE idsalle = ?");
        tab.getItems().remove(song);
        statement.setInt(1, song.getIdsalle());
        statement.executeUpdate();
            }
        }  else
        JOptionPane.showMessageDialog(null, "Vous devez sélecionner une ligne ! ");
    }

    @FXML
    private void modifiersalle(ActionEvent event) throws SQLException, IOException {
        Connection con ;
        Connexion cnx = new Connexion();
        con = cnx.getConnection();
        
        int id;
        String s="",nbr="",desc="";
        ModelTable2 song = tab.getSelectionModel().getSelectedItem();
       
        ResultSet rs;
        PreparedStatement ps = con.prepareStatement("select * FROM salle  WHERE numsalle="+song.getNumsalle() );
        
        
        rs=ps.executeQuery();
        while(rs.next())
        {
        id=rs.getInt("idsalle");
        s=rs.getString("numsalle");
        nbr = rs.getString("nbreplace");
        desc = rs.getString("description");
        }
            Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/ModifierSalle.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    @FXML
    private void addsalle(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/AjouterSalle.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
//        try {
//        Parent page1 = FXMLLoader.load(getClass().getResource("/com/esprit/view/AjouterSalle.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show(); 
//        stage.show();
//    
//        } catch(Exception e) {
//        e.printStackTrace();
//        }
    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/esprit/view/ListSalle.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    @FXML
    private void getselected(MouseEvent event) {
    }

    @FXML
    private void nbpalcemaximal(MouseEvent event) {
    }

    @FXML
    private void initiate(MouseEvent event) {
    }

    @FXML
    private void UploadImage(MouseEvent event) {
    }

    @FXML
    private void Supprimerevenement(MouseEvent event) {
        
    }
}
