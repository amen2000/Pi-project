/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.avis_Service;
import entites.avis_evenement;
import entites.avis_guide;
import entites.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP OMEN
 */
public class Back_avis_evenementController implements Initializable {

    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableView<avis_evenement> tab_Reclamation;
    @FXML
    private TableColumn<avis_evenement, Integer> col_id_rec;
    @FXML
    private TableColumn<avis_evenement, String> col_contenu_rec;

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
    @FXML
    private Button refrechbut;
    @FXML
    private TableColumn<avis_evenement, Integer> likecol;
    @FXML
    private TableColumn<avis_evenement, Integer> dislikecol;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_recclamation;
    @FXML
    private Button btn_avis_evenement;
    @FXML
    private Button btn_avis_guide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refrechbut.setOnAction((ActionEvent event) -> {
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
            likecol.setCellValueFactory(new PropertyValueFactory<>("likes"));
            dislikecol.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
            tab_Reclamation.setItems(avis_event);
            System.out.println(avis_event);
        } catch (SQLException ex) {
            Logger.getLogger(AvisEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tab_Reclamation.setEditable(true);
        //btn delete
        TableColumn col_btnDelet = new TableColumn("Supprimer");

        javafx.util.Callback<TableColumn<avis_evenement, String>, TableCell<avis_evenement, String>> cellFactory
                = new Callback<TableColumn<avis_evenement, String>, TableCell<avis_evenement, String>>() {
            public TableCell call(final TableColumn<avis_evenement, String> param) {
                final TableCell<avis_evenement, String> cell = new TableCell<avis_evenement, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmer la suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Etes vous sûr de vouloir supprimer cet avis ?");
                                Optional<ButtonType> action = alert.showAndWait();

                                if (action.get() == ButtonType.OK) {
                                    avis_evenement u = getTableView().getItems().get(getIndex());
                                    try {
                                        service.Supprimer_avis_event(u.getAvis_id());

                                        System.out.println("id supp =" + u.getAvis_id());
                                    } catch (SQLException ex) {
                                    }
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tab_Reclamation.getColumns().add(col_btnDelet);
        //btn delete end

    }

    @FXML
    private void setetoile1(ActionEvent event) {
    }

    @FXML
    private void setetoile2(ActionEvent event) {
    }

    @FXML
    private void setetoile3(ActionEvent event) {
    }

    @FXML
    private void setetoile4(ActionEvent event) {
    }

    @FXML
    private void setetoile5(ActionEvent event) {
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
