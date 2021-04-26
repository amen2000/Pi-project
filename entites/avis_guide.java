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
public class avis_guide {
    private int avis_id ;
     private int guide_id;
     private int user_id;
     private int note;
     private String commentaire; 

    public avis_guide() {
    }

    public avis_guide(int avis_id, int guide_id, int user_id, int note, String commentaire) {
        this.avis_id = avis_id;
        this.guide_id = guide_id;
        this.user_id = user_id;
        this.note = note;
        this.commentaire = commentaire;
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
     * @return the guide_id
     */
    public int getGuide_id() {
        return guide_id;
    }

    /**
     * @param guide_id the guide_id to set
     */
    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
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
