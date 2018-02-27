/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import Beans.Commande;
import Beans.LigneDeCommande;
import Beans.OuvrageEva;
import accesBDD.CommandeDAO;
import accesBDD.OuvrageEvaDAO;
import accesBDD.StatusDOA;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class GestionCommande implements Serializable{
    private CommandeDAO comDAO;

    public GestionCommande() throws NamingException {
        comDAO = new CommandeDAO();
    }
    
    public List<String> getCleDefaut(){
        List<String> clefs = new ArrayList<>();
        clefs.add("N°Commande");
        clefs.add("Status Commande");
        clefs.add("Quantités Expediées");
        clefs.add("Prix TTC");
        clefs.add("Date Commande");
        return clefs;
    }
    
    public List <Commande> listCommande (int numClient) throws ClassNotFoundException, SQLException, NamingException, ParseException{
    //List<String> clefs = getCleDefaut();
        //List<Commande> ls = new ArrayList<>();
        List <Commande> lcom = comDAO.listeCommandeNumClient(numClient);
        for(Commande c : lcom){
            
            //String sLCom ="Num Commande"+ c.getNumCommande()+" Status "+ sdoa.affichageStatus(c.getStatusCommande()) +" Quantite expedie "+ this.quantiteTotalExpedieCommande(c.getNumCommande(), numClient) +" total Commande: "+this.totalCommande(c.getNumCommande()) +" Date Commande: "+ c.getDateCommande(String.valueOf(c.getDateCommande()));//lcom.toString();
            //ls.add(c.getNumCommande());
        }
        return lcom;
    }
    
//    public List <Commande> listCommande (int numClient) throws ClassNotFoundException, SQLException{
//        
//        List <Commande> lcom = comDAO.listeCommande(numClient);
//        for(Commande p : lcom){
//                    lcom.add(p);
//            }
//        return lcom;
//    }
    
    public Float totalCommande(int numCommande) throws NamingException, ClassNotFoundException, SQLException {
        //GestionCommande gC = new GestionCommande();
        List<Commande> listNumCommande = null;
            listNumCommande = comDAO.listeCommandeNumCom(numCommande);
        
        Float prixTTCPromoTVAFLiv = 0f;

        for (Commande c : listNumCommande) {

            int numFormuleLiv = c.getNumFormuleDeLivraison();
            GestionFormuleLivraison GFDL = new GestionFormuleLivraison();
            //PersistanceFormuleLivraison PFDL = new PersistanceFormuleLivraison();
            Float PrixFDL = GFDL.prixFLV(numFormuleLiv);
            
            if (c.getNumCommande() == numCommande) {
                List<LigneDeCommande> lldc = c.getListeLigneDecommande();
                prixTTCPromoTVAFLiv += PrixFDL;
                for (LigneDeCommande ldc : lldc) {

                    int quantiteLdc = ldc.getQuantiteOuvrage();
                    Float TVAouv = ldc.getTVAOuvrageLDC();
                    Float promotionOuv = ldc.getTauxPromotion();

                    Float prixHT = 0.00f;
                    OuvrageEvaDAO ODAO = new OuvrageEvaDAO();
                    List<OuvrageEva> lOE = ODAO.objetTousLesOuvrage();
                    
                    for (OuvrageEva ouv : lOE) {
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
