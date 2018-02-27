/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import accesBDD.MaConnexion;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class LigneDeCommande extends OuvrageEva {

    
    private int numLigneDeCommande;
    private int numCommande;
    private int quantiteOuvrage;
    private Float TVAOuvrageLDC;
    private String commentaireLigneDeCommande;//null
    private Float tauxPromotion;

    public LigneDeCommande() {
    }

    public LigneDeCommande(int numLigneDeCommande, int numCommande, int quantiteOuvrage, Float TVAOuvrage, String commentaireLigneDeCommande, Float tauxPromotion, String ISBN) {
        super(ISBN);
        this.numLigneDeCommande = numLigneDeCommande;
        this.numCommande = numCommande;
        this.quantiteOuvrage = quantiteOuvrage;
        this.TVAOuvrageLDC = TVAOuvrage;
        this.commentaireLigneDeCommande = commentaireLigneDeCommande;
        this.tauxPromotion = tauxPromotion;
    }

    
    public LigneDeCommande(int numLigneDeCommande, int quantiteOuvrage, Float TVAOuvrage, String commentaireLigneDeCommande, Float tauxPromotion) {
        this.numLigneDeCommande = numLigneDeCommande;
        this.quantiteOuvrage = quantiteOuvrage;
        this.TVAOuvrageLDC = TVAOuvrage;
        this.commentaireLigneDeCommande = commentaireLigneDeCommande;
        this.tauxPromotion = tauxPromotion;
    }

    public LigneDeCommande(int numLigneDeCommande, int quantiteOuvrage, Float TVAOuvrage, String commentaireLigneDeCommande, Float tauxPromotion, String ISBN, String titre, Float prixHT) {
        super(ISBN, titre, prixHT);
        this.numLigneDeCommande = numLigneDeCommande;
        this.quantiteOuvrage = quantiteOuvrage;
        this.TVAOuvrageLDC = TVAOuvrage;
        this.commentaireLigneDeCommande = commentaireLigneDeCommande;
        this.tauxPromotion = tauxPromotion;
    }


    public LigneDeCommande(int quantiteOuvrage) {
        this.quantiteOuvrage = quantiteOuvrage;
    }

    public LigneDeCommande(int numLigneDeCommande, int numCommande, int quantiteOuvrage, Float TVAOuvrage, Float tauxPromotion, String ISBN) {
        super(ISBN);
        this.numLigneDeCommande = numLigneDeCommande;
        this.numCommande = numCommande;
        this.quantiteOuvrage = quantiteOuvrage;
        this.TVAOuvrageLDC = TVAOuvrage;
        this.tauxPromotion = tauxPromotion;
    }
    
    @Override
    public String toString() {
        return "LigneDeCommande{"+" ISBN: "+ getISBN() + "quantiteOuvrage=" + quantiteOuvrage + ", TVAOuvrage=" + TVAOuvrageLDC + ", commentaireLigneDeCommande=" + commentaireLigneDeCommande + ", tauxPromotion=" + tauxPromotion + '}';
    }
    
   public String afficherLigneDeCommande(){
        return "LigneDeCommande: N° Ligne de commande "+ numLigneDeCommande +"ISBN: "+ getISBN() + "quantiteOuvrage=" + quantiteOuvrage + ", TVAOuvrage=" + TVAOuvrageLDC + ", commentaireLigneDeCommande=" + commentaireLigneDeCommande + ", tauxPromotion=" + tauxPromotion + '}';
    }
   
   public String afficherPrixLigneDeCommande(){
       Float prixTTC = quantiteOuvrage * ((getPrixHT()+(getPrixHT()*(TVAOuvrageLDC/100))-(getPrixHT()*(tauxPromotion/100))));
        return "LigneDeCommande: N° "+ numLigneDeCommande +" ISBN: "+ getISBN() +" Titre: "+getTitre()+ " quantiteOuvrage= " + quantiteOuvrage+ " Prix TTC: "+prixTTC;
    }

    public int getNumLigneDeCommande() {
        return numLigneDeCommande;
    }

    public int getQuantiteOuvrage() {
        return quantiteOuvrage;
    }

    public String getCommentaireLigneDeCommande() {
        return commentaireLigneDeCommande;
    }

    public Float getTauxPromotion() {
        return tauxPromotion;
    }

    public Float getTVAOuvrageLDC() {
        return TVAOuvrageLDC;
    }

    public int getNumCommande() {
        return numCommande;
    }
   
}
