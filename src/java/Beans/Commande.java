/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import accesBDD.MaConnexion;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import traitement.GestionCommande;


public class Commande extends LigneDeCommande implements Serializable{
    private int numClient;
    List<LigneDeCommande> listeLigneDecommande;
    private int numCommande;
    private Date dateCommande;
    private int statusCommande;
    private Date delaiDatePaiement;//null
    private String commentaireCommande;//null
    private String adresseIPCommande;//null
    private int numFormuleDeLivraison;//mettre un objet 
    private String descriptionFormuleDeLivraison;//dans l'objet
    private int numAdresseLivraison;//tableau d'objet ou un seul objet pour livraisonn
    private Float prixTTC ;
    
    private MaConnexion mc;
    
    public Commande() throws NamingException {
    mc = new MaConnexion();
    }

    public Commande(int numCommande, int statusCommande, int numLigneDeCommande, int quantiteOuvrage, Float TVAOuvrage, String commentaireLigneDeCommande, Float tauxPromotion, String ISBN, String titre, Float prixHT) {
        super(numLigneDeCommande, quantiteOuvrage, TVAOuvrage, commentaireLigneDeCommande, tauxPromotion, ISBN, titre, prixHT);
        this.numCommande = numCommande;
        this.statusCommande = statusCommande;
    }

    public Commande(int numCommande, int statusCommande, String commentaireCommande, String adresseIPCommande) {
        this.numCommande = numCommande;
        this.statusCommande = statusCommande;
        this.commentaireCommande = commentaireCommande;
        this.adresseIPCommande = adresseIPCommande;
    }

    public Commande(int numCommande, Date dateCommande, int statusCommande, String commentaireCommande, int numFormuleDeLivraison) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.statusCommande = statusCommande;
        this.commentaireCommande = commentaireCommande;
        this.numFormuleDeLivraison = numFormuleDeLivraison;
    }

    public Commande(int numCommande, int statusCommande, String commentaireCommande, int numFormuleDeLivraison) {
        this.numCommande = numCommande;
        this.statusCommande = statusCommande;
        this.commentaireCommande = commentaireCommande;
        this.numFormuleDeLivraison = numFormuleDeLivraison;
    }

    public Commande(int numClient,List<LigneDeCommande> listeLigneDecommande, int numCommande, Date dateCommande, int statusCommande, int numFormuleDeLivraison, int numAdresseLivraison) {
        this.numClient = numClient;
        this.listeLigneDecommande = listeLigneDecommande;
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.statusCommande = statusCommande;
        this.numFormuleDeLivraison = numFormuleDeLivraison;
        this.numAdresseLivraison = numAdresseLivraison;
    }

    public Commande(int numCommande, int statusCommande, String commentaireCommande, Float prixTTC) {
        this.numCommande = numCommande;
        this.statusCommande = statusCommande;
        this.commentaireCommande = commentaireCommande;
        this.prixTTC = prixTTC;
    }
    
    public int getNumClient() {
        return numClient;
    }

    public List<LigneDeCommande> getListeLigneDecommande() {
        return listeLigneDecommande;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public String getDateCommande(String recherche) throws ParseException {
        String dateCommandeS = null;
        SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd");
        
        SimpleDateFormat sdf01 = new SimpleDateFormat("dd MMMM yyyy");
        
        try {
        Date dateCommandeSQL = new SimpleDateFormat("yyyy-MM-dd").parse(recherche);
        dateCommandeS = spdf.format(dateCommandeSQL);
        return dateCommandeS;
        }catch(ParseException ex){
        System.out.println("NoN sql");
        try{
        Date dateCommandeSQL = new SimpleDateFormat("dd/MM/yyyy").parse(recherche);
        dateCommandeS = sdf01.format(dateCommandeSQL);
        return dateCommandeS;
        }catch(ParseException exj){
            System.out.println("nonJava");
        }
        }
        return dateCommandeS;
    }
    
    public int getStatusCommande() {
        return statusCommande;
    }

    public Date getDelaiDatePaiement() {
        return delaiDatePaiement;
    }

    public String getCommentaireCommande() {
        return commentaireCommande;
    }

    public String getAdresseIPCommande() {
        return adresseIPCommande;
    }

    public int getNumFormuleDeLivraison() {
        return numFormuleDeLivraison;
    }

    public String getDescriptionFormuleDeLivraison() {
        return descriptionFormuleDeLivraison;
    }

    public int getNumAdresseLivraison() {
        return numAdresseLivraison;
    }

    public Float getPrixTTC() {
        return prixTTC;
    }

    public MaConnexion getMc() {
        return mc;
    }

    @Override
    public String toString() {
        return "Commande{" + "numClient=" + numClient + ", numCommande=" + numCommande + ", dateCommande=" + dateCommande + ", statusCommande=" + statusCommande + ", numFormuleDeLivraison=" + numFormuleDeLivraison + ", numAdresseLivraison=" + numAdresseLivraison + ", prixTTC=" + prixTTC + '}';
    }
    public int quantiteTotalExpedieCommande(int numCommande, int numClient) throws ClassNotFoundException, SQLException, NamingException {
        //List<LigneDeCommande> numCommandeLigneCom = new ArrayList();
        GestionCommande pC = new GestionCommande();
        List<Commande> listNumCommande = pC.listCommande(numClient);

        int quantite = 0;

        for (Commande c : listNumCommande) {
            if (c.getNumCommande() == numCommande) {
                List<LigneDeCommande> lldc = c.getListeLigneDecommande();
                for (LigneDeCommande ldc : lldc) {
                    int quantiteLdc = ldc.getQuantiteOuvrage();
                    quantite += quantiteLdc;
                }
            }
        }
        return quantite;
    }
    
    Float prixTTCPromoTVAFLiv = 0f;

        for (Commande c : listNumCommande) {

            int numFormuleLiv = c.getNumFormuleDeLivraison();
            PersistanceFormuleLivraison PFDL = new PersistanceFormuleLivraison();
            Float PrixFDL = PFDL.prixFLV(numFormuleLiv);
            
            if (c.getNumCommande() == numCommande) {
                List<LigneDeCommande> lldc = c.getListeLigneDecommande();
                prixTTCPromoTVAFLiv += PrixFDL;
                for (LigneDeCommande ldc : lldc) {

                    int quantiteLdc = ldc.getQuantiteOuvrage();
                    Float TVAouv = ldc.getTVAOuvrageLDC();
                    Float promotionOuv = ldc.getTauxPromotion();

                    Float prixHT = 0.00f;
                    PersistanceOuvrages PO = new PersistanceOuvrages();

                    List<Ouvrage> lO = null;
                    try {
                        lO = PO.objetTousLesOuvrage();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(PersistanceCommande.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PersistanceCommande.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (Ouvrage ouv : lO) {
                        if (ouv.getISBN().equalsIgnoreCase(ldc.getISBN())) {
                            prixHT = ouv.getPrixHT();
                            ouv.toString();
                            
                        }
                    }

                    if (promotionOuv != null || promotionOuv != 0 || TVAouv != null || TVAouv != 0) {
                        prixTTCPromoTVAFLiv += quantiteLdc * (prixHT +  (prixHT * (TVAouv / 100)) - (prixHT * (promotionOuv / 100)));
                    } else {
                        prixTTCPromoTVAFLiv += quantiteLdc * (prixHT + (prixHT * (TVAouv / 100)));
                    }
                    
                }
            }
            
        }

        return prixTTCPromoTVAFLiv;
    }


    
}
