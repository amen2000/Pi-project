/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author aymen
 */
public class reclamation {
    
    
    private int id;
    private int idu;
    private String contenu;
    private String traite;

    public reclamation(int id, int idu, String contenu, String traite) {
        this.id = id;
        this.idu = idu;
        this.contenu = contenu;
        this.traite = traite;
    }

    public reclamation() {
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.idu;
        hash = 47 * hash + Objects.hashCode(this.contenu);
       
        hash = 47 * hash + Objects.hashCode(this.traite);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final reclamation other = (reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idu != other.idu) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
    
        if (!Objects.equals(this.traite, other.traite)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    



    public String getTraite() {
        return traite;
    }

    public void setTraite(String traite) {
        this.traite = traite;
    }
    
    
    
    
    
}
