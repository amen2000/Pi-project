/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.reclamation_Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.BadWords;
import entites.reclamation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class ReclamationCrontroller implements Initializable {

    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableView<reclamation> tab_Reclamation;
    @FXML
     private TableColumn<reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<reclamation, String> col_contenu_rec;
    
    @FXML
    private TableColumn<reclamation, String> col_Traite_rec;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private TextArea txt_contenu_reclamation;
    
   
   
    @FXML
    private Button pdf;
   
    @FXML
    private Label username;
    reclamation_Service service_reclamation = new reclamation_Service();
        private String nomImage_Reclamation = "";
    @FXML
    private ImageView Image_Reclamation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // 1 ttbdl b session id user
            tab_Reclamation.setItems(service_reclamation.Affichertout_user(1));
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCrontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            refrech_reclamation();
        } catch (Exception ex) {
            Logger.getLogger(ReclamationCrontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouter_reclamation(ActionEvent event) throws Exception {
                BadWords.loadConfigs();

        {

            if (txt_contenu_reclamation.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de contenu", AlertDialog.image_cross);

            } else if (BadWords.filterText(txt_contenu_reclamation.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            }  else {



                String traite = "Non Traite";
                
// remplace 1 b id user session
                reclamation rec = new reclamation(0, 1, txt_contenu_reclamation.getText(), traite);

                service_reclamation.Ajouter(rec);
              

               
                refrech_reclamation();

                AlertDialog.showNotification("Recalamtion", "Reclamation ajouter", AlertDialog.image_checked);

            }

        }
    }
    

    

    @FXML
    private void fairepdf_reclalamtion(ActionEvent event) {
         Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("reclamation_user.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(4);

       

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
                table.addCell("Id : ");
            table.addCell("Contenu : ");
            table.addCell("Id utilisateur : ");
            table.addCell("Etat :");
            
// remplace 1  b session id
            service_reclamation.Affichertout_user(1).forEach(e
                    -> {
                String id=""+e.getId();
                table.addCell(id);

                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(String.valueOf(e.getContenu()));
                String idu=""+e.getIdu();
                table.addCell(String.valueOf(idu));
                table.addCell(e.getTraite());

                

                //Scale to new height and new width of image
                //Add to document
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("reclamation pdf");
            AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

    }
      private void refrech_reclamation() throws Exception {

        col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_contenu_rec.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        

       
        col_Traite_rec.setCellValueFactory(new PropertyValueFactory<>("traite"));
        tab_Reclamation.getItems().clear();
// 1 ttbdl b session id user
        tab_Reclamation.setItems(service_reclamation.Affichertout_user(1));

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
