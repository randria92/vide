/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author cdi205
 */
public class Ouvrage implements Serializable{
    private String ISBN;
    private String titre;
    private String sousTitre;
    private String image;//null
    private String resume;//null
    private int quantiteDispo;
    private int idPromotion;//
    private Float prixHT;
    private String disponibilite; //Disponibilite a = Disponibilite.Disponible;
    private Float poidsOuvrage;//null
    private Date dateparutionOuvrage;
    private Date parution;
    private Float TVAOuvrage;
    private String commentaire;//null
    //private List <Auteur> auteur;//Tableau ?
    //private Vector <Avis> avisOuvrage;//tableau?
    private List <ThemeEtSousTheme> sousThemeEtTheme;//tableau?
    private Float promotionOuvrage;
    //private List <MotCle> motCleOuvrage;//tableau?
    private String marqueEditeurOuvrage;
    private String Theme;
    
    public Ouvrage() {
        //sousThemeEtTheme = new ArrayList();
    }

//    public OuvrageEva(List<ThemeEtSousTheme> sousTheme) {
//        this.sousThemeEtTheme = sousTheme;
//    }
    
    public Ouvrage(String titre, int quantiteDispo, Float prixHT, String disponibilite, Date dateparutionOuvrage, Float TVAOuvrage) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.quantiteDispo = quantiteDispo;//nombre dobjet creer
        this.prixHT = prixHT;
        this.disponibilite = disponibilite;
        this.dateparutionOuvrage = dateparutionOuvrage;
        this.TVAOuvrage = TVAOuvrage;
    }

    public Ouvrage(String ISBN, String titre, Float prixHT, Float TVAOuvrage) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.prixHT = prixHT;
        this.TVAOuvrage = TVAOuvrage;
    }

    public Ouvrage(String ISBN, String titre, Float prixHT) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.prixHT = prixHT;
    }

    public Ouvrage(String ISBN) {
        this.ISBN = ISBN;
    }
public Ouvrage (String ISBN, String titre, String sousTitre, int quantiteDispo, Float prixHT, String disponibilite, Float TVAOuvrage, Float promotionOuvrage) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.sousTitre = sousTitre;
        this.quantiteDispo = quantiteDispo;
        this.prixHT = prixHT;
        this.disponibilite = disponibilite;
        this.TVAOuvrage = TVAOuvrage;
        this.promotionOuvrage = promotionOuvrage;
    }

public Ouvrage(String ISBN, String titre, String disponibilite, int quantiteDispo, int idPromotion, Float prixHT, Float TVA, java.sql.Date parution) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.disponibilite = disponibilite;
        this.quantiteDispo = quantiteDispo;
        this.idPromotion = idPromotion;
        this.prixHT = prixHT;
        this.TVAOuvrage = TVA;
        this.parution = parution;
    }
//    public Ouvrage(String ISBN, String titre, String sousTitre, int quantiteDispo, Float prixHT, String disponibilite, Float TVAOuvrage,List<ThemeEtSousTheme> sousTheme, Float promotionOuvrage) {
//        this.ISBN = ISBN;
//        this.titre = titre;
//        this.sousTitre = sousTitre;
//        this.quantiteDispo = quantiteDispo;
//        this.prixHT = prixHT;
//        this.disponibilite = disponibilite;
//        this.TVAOuvrage = TVAOuvrage;
//        this.sousThemeEtTheme = sousTheme;
//        this.promotionOuvrage = promotionOuvrage;
//    }

    public Ouvrage(String titre,String ISBN, Float prixHT, Float TVAOuvrage, Float promotionOuvrage, String disponibiliteOuvrage, int quantiteDispo) {
        this.titre =titre;
        this.ISBN = ISBN;
        this.prixHT = prixHT;
        this.TVAOuvrage = TVAOuvrage;
        this.promotionOuvrage = promotionOuvrage;
        this.disponibilite = disponibiliteOuvrage;
        this.quantiteDispo = quantiteDispo;
    }

    public Ouvrage(String ISBN, String titre, int quantiteDispo, Float prixHT, String disponibilite, Date dateparutionOuvrage, Float TVAOuvrage, Float promotionOuvrage) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.quantiteDispo = quantiteDispo;
        this.prixHT = prixHT;
        this.disponibilite = disponibilite;
        this.dateparutionOuvrage = dateparutionOuvrage;
        this.TVAOuvrage = TVAOuvrage;
        this.promotionOuvrage = promotionOuvrage;
    }

    

    
    
    @Override
    public String toString() {
        return "Ouvrage{" + "ISBN=" + ISBN + ", titre=" + titre + ", prixHT=" + prixHT +"Quantité dispo: "+ quantiteDispo + ", promo=" + promotionOuvrage+ 
                '}';
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public String getImage() {
        return image;
    }

    public String getResume() {
        return resume;
    }

    public int getQuantiteDispo() {
        return quantiteDispo;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public Float getPoidsOuvrage() {
        return poidsOuvrage;
    }

    public Date getDateparutionOuvrage() {
        return dateparutionOuvrage;
    }

    public Float getTVAOuvrage() {
        return TVAOuvrage;
    }

    public String getCommentaire() {
        return commentaire;
    }

//    public Vector<Avis> getAvisOuvrage() {
//        return avisOuvrage;
//    }

    public Float getPromotionOuvrage() {
        return promotionOuvrage;
    }

    public int getIdPromotion() {
        return idPromotion;
    }


    public Date getParution() {
        return parution;
    }

//    public List<Auteur> getAuteur() {
//        return auteur;
//    }

//    public List<ThemeEtSousTheme> getDescriptionSousTheme() {
//        return sousThemeEtTheme;
//    }
//
//    public List<MotCle> getMotCleOuvrage() {
//        return motCleOuvrage;
//    }
//
//    public List<ThemeEtSousTheme> getSousTheme() {
//        return sousThemeEtTheme;
//    }
    
    public String getMarqueEditeurOuvrage() {
        return marqueEditeurOuvrage;
    }

//    public List<ThemeEtSousTheme> getSousThemeEtTheme() {
//        return sousThemeEtTheme;
//    }

    public String getTheme() {
        return Theme;
    }
    
}
