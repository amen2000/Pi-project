/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.avis_Service;
import Service.reclamation_Service;
import entites.BadWords;
import entites.avis_evenement;
import entites.avis_guide;
import entites.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class AvisEvenementController implements Initializable {

    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableView<avis_evenement> tab_Reclamation;
    @FXML
    private TableColumn<avis_evenement, Integer> col_id_rec;
    @FXML
    private TableColumn<avis_evenement, String> col_contenu_rec;
    @FXML
    private TableColumn<avis_evenement, ?> col_Traite_rec;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private TextArea txt_contenu_reclamation;
    @FXML
    private ImageView Image_Reclamation;
    @FXML
    private Button etoile1;
    @FXML
    private Button etoile2;
    @FXML
    private Button etoile3;
    @FXML
    private Button etoile4;
    @FXML
    private Button etoile5;
    @FXML
    private Label username;
    
     private avis_Service service = new avis_Service();
        public int note = 0;
    ObservableList<avis_evenement> avis_event = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> noteCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try {
            List<avis_evenement> listAvisEvenement = service.Affichertout_user_avis_event();
            
            if (!listAvisEvenement.isEmpty()) {
                for (int i = 0; i < listAvisEvenement.size(); i++) {
                    avis_event.add(listAvisEvenement.get(i));
                    System.out.println(listAvisEvenement.get(i));
                }
            }
            
            col_id_rec.setCellValueFactory(new PropertyValueFactory<>("avis_id"));
            col_contenu_rec.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
             noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
            
            tab_Reclamation.setItems(avis_event);
            System.out.println(avis_event);
        } catch (SQLException ex) {
            Logger.getLogger(AvisEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         setEtoiles(0);
        
    }    
    
    
    @FXML
    private void ajouter_avis_evenement(ActionEvent event) {
        
         avis_Service avc = new avis_Service();
         avis_evenement av_event = new avis_evenement(1, 5, 11, note,txt_contenu_reclamation.getText() );
            
      
         
            if (txt_contenu_reclamation.getText().compareTo("") == 0) {
                AlertDialog.showNotification("Error !", "champ vide de contenu", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_contenu_reclamation.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }  else {
                 try {
            avc.Ajouter_avis_event(av_event);   
        } catch (SQLException ex) {
            Logger.getLogger(AvisEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
              
        
        
       
    }

    @FXML
    private void setetoile1(ActionEvent event) {
        note = 1;
        setEtoiles(1);
    }

    @FXML
    private void setetoile2(ActionEvent event) {
              note = 2;
        setEtoiles(2);
    }

    @FXML
    private void setetoile3(ActionEvent event) {
              note = 3;
        setEtoiles(3);
    }

    @FXML
    private void setetoile4(ActionEvent event) {
              note = 4;
        setEtoiles(4);
    }

    @FXML
    private void setetoile5(ActionEvent event) {
              note = 5;
        setEtoiles(5);
    }
    
    private void setEtoiles(int nbEtoiles){
        Button[] etoiles = {etoile1,etoile2,etoile3,etoile4,etoile5};
        for (int i= 0;i<5;i++){
            if (nbEtoiles <= i){
                ImageView image = new ImageView(new Image("/images/etoile-vide.png"));
                image.setFitHeight(15.0);
                image.setFitWidth(15.0);
                etoiles[i].setGraphic(image);
            }else{
                  ImageView image = new ImageView(new Image("/images/etoile.png"));
                image.setFitHeight(15.0);
                image.setFitWidth(15.0);
                etoiles[i].setGraphic(image);
            }
            
        }
    }


    @FXML
    private void afficherAvisEvenement(ActionEvent event) {
                         try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/home_avis_evenement.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void afficherReclamation(ActionEvent event) {
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
    private void afficherAvisGuide(ActionEvent event) {
                         try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/home_avis_guide.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void backEnd(ActionEvent event) {
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
}
