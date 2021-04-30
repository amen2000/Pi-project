/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import IService.IService;
import Utils.MyConnexion;
import entites.contactus;

import entites.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aymen
 */
public class reclamation_Service implements IService<reclamation> {

    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(reclamation t) throws SQLException {

        try {
            String requete = "INSERT INTO `reclamation`( `id_utilisateur_id`, `description`, `traite`) VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, t.getIdu());
            pst.setString(2, t.getContenu());
   
            pst.setString(3, t.getTraite());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM `reclamation` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Modifier(reclamation t, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE `reclamation` SET `traite`=? where ID = ?";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setString(1, "Traite");
        ps.execute();
    }

    @Override
    public ObservableList<reclamation> Affichertout() throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new reclamation(rs.getInt("ID"), rs.getInt("id_utilisateur_id"), rs.getString("description"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> Affichertout_user(int id) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where id_utilisateur_id = " + String.valueOf(id);
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new reclamation(rs.getInt("ID"), rs.getInt("id_utilisateur_id"), rs.getString("description"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> serach(String cas) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where description LIKE '%" + cas +   "%' or  traite LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new reclamation(rs.getInt("ID"), rs.getInt("id_utilisateur_id"), rs.getString("description"), rs.getString("traite")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
     public void Ajoutercontact(contactus t) throws SQLException {

        try {
            String requete = "INSERT INTO `contactez_nous`( `nom`, `prenom`, `email`, `objet`, `message`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
   
            pst.setString(3, t.getEmail());
             pst.setString(4, t.getObjet()); 
             pst.setString(5, t.getMessage());
             
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     
      public ObservableList<contactus> Affichertout_contacts() throws SQLException {
        ObservableList<contactus> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `contactez_nous`" ;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new contactus(rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("objet"), rs.getString("message")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }
    
}
