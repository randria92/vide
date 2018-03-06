package Beans;

/**
 *
 * @author cdi211
 */

import java.io.Serializable;
import java.sql.Date;

public class client implements Serializable{
    
   private int idClient; 
   private String civiliteClient;
   private String nomClient;          
   private String prenomClient;     
   private Date  dateNaissanceClient; 
   private String societeClient;       
   private String telephoneClient;
   private String emailClient;      
   private String motDePasseClient;
   private Date dateAdhesionClient;
   private Date dateFinAdhesionClient;
   private String commentaireClient;
   private int statusClient;
   private int statusConnexionClient;
   
   public client(){       
   }

    public client(int idClient, String civiliteClient, String nomClient, String prenomClient, Date dateNaissanceClient, String societeClient, String telephoneClient, String emailClient, String motDePasseClient, Date dateAdhesionClient, Date dateFinAdhesionClient, String commentaireClient, int statusClient, int statusConnexionClient) {
        this.idClient = idClient;
        this.civiliteClient = civiliteClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.dateNaissanceClient = dateNaissanceClient;
        this.societeClient = societeClient;
        this.telephoneClient = telephoneClient;
        this.emailClient = emailClient;
        this.motDePasseClient = motDePasseClient;
        this.dateAdhesionClient = dateAdhesionClient;
        this.dateFinAdhesionClient = dateFinAdhesionClient;
        this.commentaireClient = commentaireClient;
        this.statusClient = statusClient;
        this.statusConnexionClient = statusConnexionClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCiviliteClient() {
        return civiliteClient;
    }

    public void setCiviliteClient(String civiliteClient) {
        this.civiliteClient = civiliteClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public Date getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(Date dateNaissanceClient) {
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public String getSocieteClient() {
        return societeClient;
    }

    public void setSocieteClient(String societeClient) {
        this.societeClient = societeClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getMotDePasseClient() {
        return motDePasseClient;
    }

    public void setMotDePasseClient(String motDePasseClient) {
        this.motDePasseClient = motDePasseClient;
    }

    public Date getDateAdhesionClient() {
        return dateAdhesionClient;
    }

    public void setDateAdhesionClient(Date dateAdhesionClient) {
        this.dateAdhesionClient = dateAdhesionClient;
    }

    public Date getDateFinAdhesionClient() {
        return dateFinAdhesionClient;
    }

    public void setDateFinAdhesionClient(Date dateFinAdhesionClient) {
        this.dateFinAdhesionClient = dateFinAdhesionClient;
    }

    public String getCommentaireClient() {
        return commentaireClient;
    }

    public void setCommentaireClient(String commentaireClient) {
        this.commentaireClient = commentaireClient;
    }

    public int getStatusClient() {
        return statusClient;
    }

    public void setStatusClient(int statusClient) {
        this.statusClient = statusClient;
    }

    public int getStatusConnexionClient() {
        return statusConnexionClient;
    }

    public void setStatusConnexionClient(int statusConnexionClient) {
        this.statusConnexionClient = statusConnexionClient;
    }

    @Override
    public String toString() {
        return "client{" + "idClient=" + idClient + ", civiliteClient="
                + civiliteClient + ", nomClient=" + nomClient + ", prenomClient=" 
                + prenomClient + ", dateNaissanceClient=" + dateNaissanceClient 
                + ", societeClient=" + societeClient + ", telephoneClient=" 
                + telephoneClient + ", emailClient=" + emailClient + ", motDePasseClient=" 
                + motDePasseClient + ", dateAdhesionClient=" + dateAdhesionClient 
                + ", dateFinAdhesionClient=" + dateFinAdhesionClient + ", commentaireClient=" 
                + commentaireClient + ", statusClient=" + statusClient + ", statusConnexionClient=" 
                + statusConnexionClient + '}';
    }
 
    
   
}
