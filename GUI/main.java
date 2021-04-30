/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.avis_Service;
import entites.avis_destination;
import entites.avis_evenement;
import entites.avis_guide;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aymen
 */
public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Home_Reclamation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        avis_Service avc = new avis_Service();
        avis_evenement av_event = new avis_evenement(1, 5, 11, 3, "avis event5");
        avis_guide avg = new avis_guide(30, 9, 14, 8, "test");
         //avc.Ajouter_avis_event(av_event);                      //add avis event
        //avc.Supprimer_avis_event(16);                          //delete avis event
        //avc.Modifier_note_avis_event(7, 20);                   //modifier note avis event
       
       // avc.Ajouter_avis_guide(avg);                          //add avis guide
        //avc.Supprimer_avis_guide(34);                         //delete avis guide
        //avc.Modifier_note_avis_guide(4, 34);                  //modifier note avis guide
    }

}
