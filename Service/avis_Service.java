/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Utils.MyConnexion;
import entites.avis_destination;
import entites.avis_evenement;
import entites.avis_guide;
import entites.avis_evenement;
import entites.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.ir.CallNode;

/**
 *
 * @author HP OMEN
 */
public class avis_Service {
   private Connection c = MyConnexion.getInsCon().getcnx();
        ObservableList<avis_evenement> listAvisEvenement = FXCollections.observableArrayList();
        ObservableList<avis_guide> listAvisGuide = FXCollections.observableArrayList();
   
   
   public void Ajouter_avis_event(avis_evenement t) throws SQLException {

        try {
            String requete = "INSERT INTO `avis_evenement`( `id_evenement_id`, `id_utilisateur_id`, `note`, `commentaire`) VALUES (?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getEvent_id());
            pst.setInt(2, t.getUser_id());
   
            pst.setInt(3, t.getNote());
             pst.setString(4, t.getCommentaire());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    public void Supprimer_avis_event(int t) throws SQLException {

        String requete = "DELETE FROM `avis_evenement` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
     public void Modifier_note_avis_event(int note, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE avis_evenement SET note =?  WHERE id =? ";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setInt(1, note);
        ps.execute();
         
    }
     
     public ObservableList<avis_evenement> Affichertout_user_avis_event() throws SQLException {
         avis_evenement e=new avis_evenement();
      try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM avis_evenement");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listAvisEvenement.add( e=new avis_evenement(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_evenement_id"),
                        resultSet.getInt("id_utilisateur_id"),
                        resultSet.getInt("note"),
                        resultSet.getString("commentaire")
                ));
                e.setLikes(resultSet.getInt("likes"));
                e.setDislikes(resultSet.getInt("dislikes"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur d'affichage (tout) avis event : " + ex.getMessage());
        }
        return listAvisEvenement;
    }
     
       
     public ObservableList<avis_guide> Affichertout_user_avis_guide() throws SQLException {
          avis_guide e=new avis_guide();
      try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM avis_guide");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listAvisGuide.add(e=new avis_guide(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_guide_id"),
                        resultSet.getInt("id_utilisateur_id"),
                        resultSet.getInt("note"),
                        resultSet.getString("commentaire")
                ));
                e.setLikes(resultSet.getInt("likes"));
                e.setDislikes(resultSet.getInt("dislikes"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur d'affichage (tout) avis event : " + ex.getMessage());
        }
        return listAvisGuide;

    }
     
              
        public void Ajouter_avis_dest(avis_destination t) throws SQLException {

        try {
            String requete = "INSERT INTO `avis_destination`( `id_destination_id`, `id_utilisateur_id`, `note`, `commentaire`) VALUES (?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getDestination_id());
            pst.setInt(2, t.getUser_id());
   
            pst.setInt(3, t.getNote());
             pst.setString(4, t.getCommentaire());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
         
         
           public void Ajouter_avis_guide(avis_guide t) throws SQLException {

        try {
            String requete = "INSERT INTO `avis_guide`( `id_guide_id`, `id_utilisateur_id`, `note`, `commentaire`) VALUES (?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getGuide_id());
            pst.setInt(2, t.getUser_id());
   
            pst.setInt(3, t.getNote());
             pst.setString(4, t.getCommentaire());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
         
           public void Supprimer_destination_avis(int t) throws SQLException {

        String requete = "DELETE FROM `avis_destination` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
           
             public void Supprimer_avis_guide(int t) throws SQLException {

        String requete = "DELETE FROM `avis_guide` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
           
           
           public void Modifier_note_avis_destination(int note, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE avis_destination SET note =?  WHERE id =? ";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setInt(1, note);
        ps.execute();
         
    }
           
            public void Modifier_note_avis_guide(int note, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE avis_guide SET note =?  WHERE id =? ";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setInt(1, note);
        ps.execute();
         
    }
            public void update_avis_event_likes( int avis_event_id, int likes) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("UPDATE avis_evenement SET likes =? WHERE id =? ");
            pt.setInt(1, likes);
            pt.setInt(2, avis_event_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(avis_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
                 public void update_avis_event_dislikes( int avis_event_id, int likes) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("UPDATE avis_evenement SET dislikes =? WHERE id =? ");
            pt.setInt(1, likes);
           pt.setInt(2, avis_event_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(avis_Service.class.getName()).log(Level.SEVERE, null, ex);
         }
   }

                      public void update_avis_guide_likes( int avis_event_id, int likes) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("UPDATE avis_guide SET likes =? WHERE id =? ");
            pt.setInt(1, likes);
             pt.setInt(2, avis_event_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(avis_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
                             public void update_avis_guide_dislikes( int avis_event_id, int likes) {
        PreparedStatement pt;
        try {
            pt = c.prepareStatement("UPDATE avis_guide SET dislikes =? WHERE id =? ");
            pt.setInt(1, likes);
             pt.setInt(2, avis_event_id);
            pt.executeUpdate();
            System.out.println("quantite updated");

        } catch (SQLException ex) {
            Logger.getLogger(avis_Service.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
}
