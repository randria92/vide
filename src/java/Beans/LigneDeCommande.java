/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import accesBDD.CommandeDAO;
import accesBDD.LigneDeCommandeDAO;
import accesBDD.OuvrageEvaDAO;
import java.io.Serializable;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class LigneDeCommande extends Ouvrage implements Serializable {

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

    public LigneDeCommande(String titre, int quantiteOuvrage, String ISBN, Float prixHT, Float TVAOuvrage, Float promotionOuvrage, String disponibiliteOuvrage, int quantiteDispo) {
        super(titre, ISBN, prixHT, TVAOuvrage, promotionOuvrage, disponibiliteOuvrage, quantiteDispo);
        this.quantiteOuvrage = quantiteOuvrage;
    }

    @Override
    public String toString() {
        return "LigneDeCommande{" + " ISBN: " + getISBN() + "quantiteOuvrage=" + quantiteOuvrage + ", TVAOuvrage=" + TVAOuvrageLDC + ", commentaireLigneDeCommande=" + commentaireLigneDeCommande + ", tauxPromotion=" + tauxPromotion + '}';
    }

    public String afficherLigneDeCommande() {
        return "LigneDeCommande: N° Ligne de commande " + numLigneDeCommande + "ISBN: " + getISBN() + "quantiteOuvrage=" + quantiteOuvrage + ", TVAOuvrage=" + TVAOuvrageLDC + ", commentaireLigneDeCommande=" + commentaireLigneDeCommande + ", tauxPromotion=" + tauxPromotion + "titre" + getTitre() + '}';
    }

    public String afficherPrixLigneDeCommande() {
        Float prixTTC = quantiteOuvrage * ((getPrixHT() + (getPrixHT() * (TVAOuvrageLDC / 100)) - (getPrixHT() * (tauxPromotion / 100))));
        return "LigneDeCommande: N° " + numLigneDeCommande + " ISBN: " + getISBN() + " Titre: " + getTitre() + " quantiteOuvrage= " + quantiteOuvrage + " Prix TTC: " + prixTTC;
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

    public void setQuantiteOuvrage(int quantiteOuvrage) {
        this.quantiteOuvrage = quantiteOuvrage;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void changeQty(int quantiteOuvrage) {
        this.quantiteOuvrage += quantiteOuvrage;
    }
//    public Float prixTTCUneLigne (int numCommande, int numLigneCommande ) throws ClassNotFoundException, NamingException, SQLException{
//        GestionLigneCommande gLCom = new GestionLigneCommande();
//        Float prix = gLCom.prixTTCUneLigne(numCommande, numLigneCommande);
//        return prix;
//    }
//    
//    public Float economiRealise(int numCommande, int numLigneDeCommande) throws SQLException, ClassNotFoundException, NamingException{
//        GestionLigneCommande gLCom = new GestionLigneCommande();
//        Float eco = gLCom.economiRealise(numCommande, numLigneDeCommande);
//        return eco;
//    }

    public String prixTTCUneLigne(int numCommande, int numLigneCommande) throws ClassNotFoundException, SQLException, NamingException {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        OuvrageEvaDAO ouvDAO = new OuvrageEvaDAO();
        CommandeDAO comDAO = new CommandeDAO();
        List<Commande> listNumCommande = comDAO.listeCommandeNumCom(numCommande);
        Float prixTTCLigneCom = 0f;
        String prixTTCLigneComS = null;
        for (Commande c : listNumCommande) {
            if (c.getNumCommande() == numCommande) {

                //PersistanceLigneDeCommande PLDC = new PersistanceLigneDeCommande();
                List<LigneDeCommande> lLDC = c.getListeLigneDecommande();

               // PersistanceOuvrages PO = new PersistanceOuvrages();
                Float prixHT = 0.00f;
                List<Ouvrage> lO = ouvDAO.objetTousLesOuvrage();
                int quantite = 0;

                for (LigneDeCommande ldc : lLDC) {
                    if (ldc.getNumLigneDeCommande() == numLigneCommande) {

                        for (Ouvrage ouv : lO) {
                            String ISBNOuv = ouv.getISBN();
                            String ISBNLdc = ldc.getISBN();
                            if (ISBNOuv.equalsIgnoreCase(ISBNLdc)) {

                                prixHT = ouv.getPrixHT();
                                quantite = ldc.getQuantiteOuvrage();
                                if (ldc.getTauxPromotion() != null || ldc.getTauxPromotion() != 0 || ldc.getTVAOuvrageLDC() != null || ldc.getTVAOuvrageLDC() != 0) {
                                    prixTTCLigneCom = quantite * (prixHT + (prixHT * (ldc.getTVAOuvrageLDC() / 100)) - (prixHT * (ldc.getTauxPromotion() / 100)));
                                    prixTTCLigneComS = df.format(prixTTCLigneCom);
                                } else {
                                    prixTTCLigneCom = quantite * (prixHT + (prixHT * (ldc.getTVAOuvrageLDC() / 100)));
                                    prixTTCLigneComS = df.format(prixTTCLigneCom);
                                }
                            }
                        }

                    }
                }
            }
        }
        return prixTTCLigneComS;
    }

    public Float economiRealise(int numCommande, int numLigneDeCommande) throws SQLException, ClassNotFoundException, NamingException {
        OuvrageEvaDAO ouvDAO = new OuvrageEvaDAO();
        CommandeDAO comDAO = new CommandeDAO();
        LigneDeCommandeDAO lComDAO = new LigneDeCommandeDAO();

        List<Commande> listNumCommande = comDAO.listeCommandeNumCom(numCommande);
        Float economieRealise = 0f;
        for (Commande c : listNumCommande) {
            if (c.getNumCommande() == numCommande) {
                List<LigneDeCommande> lLDC = lComDAO.listeLigneCommande(numCommande);

                Float prixHT = 0.00f;
                List<Ouvrage> lO = ouvDAO.objetTousLesOuvrage();
                int quantite = 0;
                for (LigneDeCommande ldc : lLDC) {
                    if (ldc.getNumLigneDeCommande() == numLigneDeCommande) {
                        for (Ouvrage ouv : lO) {
                            if (ouv.getISBN().equalsIgnoreCase(ldc.getISBN())) {
                                prixHT = ouv.getPrixHT();
                                //ouv.toString();
                                quantite = ldc.getQuantiteOuvrage();
                                if (ldc.getTauxPromotion() != null && ldc.getTauxPromotion() != 0) {
                                    economieRealise = quantite * (prixHT + (prixHT * (ldc.getTVAOuvrageLDC() / 100)) * (ldc.getTauxPromotion() / 100));

                                } else {
                                    economieRealise = 0f;
                                }
                            }
                        }

                    }
                }
            }
        }
        return economieRealise;
    }

}
