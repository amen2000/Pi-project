/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author HP OMEN
 */
public class avis_destination {
     private int avis_id ;
     private int destination_id;
     private int user_id;
     private int note;
     private String commentaire;

    public avis_destination(int avis_id, int destination_id, int user_id, int note, String commentaire) {
        this.avis_id = avis_id;
        this.destination_id = destination_id;
        this.user_id = user_id;
        this.note = note;
        this.commentaire = commentaire;
    }

    public avis_destination() {
    }

    /**
     * @return the avis_id
     */
    public int getAvis_id() {
        return avis_id;
    }

    /**
     * @param avis_id the avis_id to set
     */
    public void setAvis_id(int avis_id) {
        this.avis_id = avis_id;
    }

    /**
     * @return the destination_id
     */
    public int getDestination_id() {
        return destination_id;
    }

    /**
     * @param destination_id the destination_id to set
     */
    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
