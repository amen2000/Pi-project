/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.reclamation_Service;
import entites.reclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import entites.contactus;
/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class contactback implements Initializable {

    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<contactus> tabview;
   
    @FXML

    private ImageView Image_rec;
    private reclamation_Service service = new reclamation_Service();
    private TableColumn<reclamation, String> col_btnDelet;

    @FXML
    private Button btn_recclamation;
    @FXML
    private Button btn_avis_evenement;
    @FXML
    private Button btn_avis_guide;
    @FXML
    private Button btn_contact;
    @FXML
    private TableColumn<Integer, contactus>  col_id;
    @FXML
    private TableColumn<String, contactus> colnom;
    @FXML
    private TableColumn<String, contactus>  colprenom;
    @FXML
    private TableColumn<String, contactus>  colmail;
    @FXML
    private TableColumn<String, contactus>  colobj;
    @FXML
    private TableColumn<String, contactus>  colmsg;
    @FXML
    private Label fullName1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        colmail.setCellValueFactory(new PropertyValueFactory<>("email"));
colmsg.setCellValueFactory(new PropertyValueFactory<>("message"));
colobj.setCellValueFactory(new PropertyValueFactory<>("objet"));
        tabview.getItems().clear();

        try {
            tabview.setItems(service.Affichertout_contacts());
        } catch (SQLException ex) {
            Logger.getLogger(contactback.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_avis_evenement.setOnAction((ActionEvent event) -> {
            try {

                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/back_avis_evenement.fxml"));
                Scene scene = new Scene(root);

                Stage st = new Stage();
                st.setScene(scene);

                st.show();
            } catch (IOException ex) {
                Logger.getLogger(home_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btn_avis_guide.setOnAction((ActionEvent event) -> {
            try {

                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/back_avis_guide.fxml"));
                Scene scene = new Scene(root);

                Stage st = new Stage();
                st.setScene(scene);

                st.show();
            } catch (IOException ex) {
                Logger.getLogger(home_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        

        tabview.setEditable(true);

        

        

        // TODO
    }

    

    

    @FXML
    private void handleClicks(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Home_Reclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void frontEnd(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterAvisEvenementBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/back_avis_evenement.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterAvisGuideBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/back_avis_guide.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
