/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alert;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author aymen
 */
public class AlertDialog {
    
     public static final Image image_checked=new Image("/images/checked.png");
    public static final Image image_cross=new Image("/images/cross.png");

     public static void showNotification(String Title,String Message,Image img)
     {
                Notifications notify=Notifications.create()
                        .darkStyle()
                        .graphic(new ImageView(img))
                        .title(Title)
                        .text(Message)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                notify.show();
     }
    
}
