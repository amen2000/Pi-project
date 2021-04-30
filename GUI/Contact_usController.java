/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import controllers.home_ReclamationController;
import entites.contactus;
import java.io.IOException;
import java.io.ObjectInput;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.reclamation_Service;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author HP OMEN
 */
public class Contact_usController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private TextField nominput;
    @FXML
    private TextField prenominput;
    @FXML
    private TextField ojetinput;
    @FXML
    private TextField emailinput;
    @FXML
    private TextField messageinput;
    @FXML
    private Button sendbut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                sendbut.setOnAction((ActionEvent event) -> {
            contactus c= new contactus(nominput.getText(), prenominput.getText(), emailinput.getText(),ojetinput.getText(), messageinput.getText());
         reclamation_Service ser=new reclamation_Service();
                    try {
                        ser.Ajoutercontact(c);
                    } catch (SQLException ex) {
                        Logger.getLogger(Contact_usController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ((Node) (event.getSource())).getScene().getWindow().hide();
        });
       
        // TODO
    }    
    
}
