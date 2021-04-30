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

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_ReclamationController implements Initializable {

    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<reclamation> tabview;
    @FXML
    private TableColumn<reclamation, Integer> col_id;
    @FXML
    private TableColumn<reclamation, String> col_Contenu;
    @FXML
    private TableColumn<reclamation, String> col_traite;
    @FXML
    private ImageView Image_rec;

    private reclamation_Service service = new reclamation_Service();
    private TableColumn<reclamation, String> col_btnDelet;

    @FXML
    private Label fullName;
    @FXML
    private Button btn_recclamation;
    @FXML
    private Button btn_avis_evenement;
    @FXML
    private Button btn_avis_guide;
    @FXML
    private Button btn_contact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search();

        try {
            refreche();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        btn_contact.setOnAction((ActionEvent event) -> {
            try {

                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/contactback.fxml"));
                Scene scene = new Scene(root);

                Stage st = new Stage();
                st.setScene(scene);

                st.show();
            } catch (IOException ex) {
                Logger.getLogger(home_ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        tabview.setEditable(true);

        col_btnDelet = new TableColumn("Supprimer");

        javafx.util.Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactory
                = new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            public TableCell call(final TableColumn<reclamation, String> param) {
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

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
                                alert.setContentText("Etes vous sûr de vouloir supprimer cette reclamation ?");
                                Optional<ButtonType> action = alert.showAndWait();

                                if (action.get() == ButtonType.OK) {
                                    reclamation u = getTableView().getItems().get(getIndex());

                                    try {
                                        service.Supprimer(u.getId());
                                    } catch (SQLException ex) {
                                    }

                                    try {
                                        refreche();
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
        tabview.getColumns().add(col_btnDelet);

        //add btn traite
        TableColumn col_btntr = new TableColumn("Traiter");

        javafx.util.Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactory1
                = new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            public TableCell call(final TableColumn<reclamation, String> param) {
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    final Button btntr = new Button("traiter");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btntr.setOnAction(event -> {
                                reclamation u = getTableView().getItems().get(getIndex());

                                if (u.getTraite().equals("Traite")) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    //alert.setTitle("Confirmer la suppression");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Deja traitée");
                                    alert.showAndWait();
                                }

                                try {
                                    service.Modifier(u, u.getId());
                                } catch (SQLException ex) {
                                }

                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                }

                            });

                            setGraphic(btntr);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btntr.setCellFactory(cellFactory1);
        tabview.getColumns().add(col_btntr);
        //end btn traite

        // TODO
    }

    public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("")) {

                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {

                try {

                    col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    col_Contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));

                    col_traite.setCellValueFactory(new PropertyValueFactory<>("traite"));

                    tabview.getItems().clear();

                    tabview.getItems().clear();

                    tabview.setItems(service.serach(txt_Seach.getText()));

                } catch (SQLException ex) {
                }

            }
        }
        );

    }

    public void refreche() throws SQLException {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        col_traite.setCellValueFactory(new PropertyValueFactory<>("traite"));

        tabview.getItems().clear();

        tabview.setItems(service.Affichertout());

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
