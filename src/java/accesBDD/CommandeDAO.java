/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.Commande;
import Beans.LigneDeCommande;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class CommandeDAO implements Serializable{
    private MaConnexion mc;

    public CommandeDAO() throws NamingException {
        mc = new MaConnexion();
    }
    
    public List<Commande> listeCommandeNumClient(int numClient /*Cookie*/) throws ClassNotFoundException, SQLException {
        List<Commande> lcom;
       try( Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement()){
           
        List<LigneDeCommande> llcom = new ArrayList<>();
        String req1 = "SELECT idLigneDeCommande, ISBN, idCommande, quantiteOuvrageLigneDeCommande, TVAOuvrage, tauxPromotion FROM ligneDeCommande";
        ResultSet rs1 = stm.executeQuery(req1);
        LigneDeCommande ligneCommande = null;
        while (rs1.next()) {
            int numCommandeLlcom = rs1.getInt("idCommande");
            // if (numCommande == numCommandeLlcom) {
            System.out.println("PBL: "+numCommandeLlcom);
            int numLigneCommande = rs1.getInt("idLigneDeCommande");
            String ISBNllcom = rs1.getString("ISBN");

            int quantiteOuvragellcom = rs1.getInt("quantiteOuvrageLigneDeCommande");
            Float TVAOuvrage = rs1.getFloat("TVAOuvrage");
            Float tauxPromo = rs1.getFloat("tauxPromotion");

            ligneCommande = new LigneDeCommande(numLigneCommande, numCommandeLlcom, quantiteOuvragellcom, TVAOuvrage, tauxPromo, ISBNllcom);
            llcom.add(ligneCommande);
             
        }

        lcom = new ArrayList<>();
        String req = "SELECT idCommande,  idClient, idFormuleDeLivraison, idAdresseLivraisonCommande, dateCommande, statusCommande FROM commande WHERE idClient = "+numClient+"";
        ResultSet rs = stm.executeQuery(req);
        Commande commande = null;
        while (rs.next()) {
            int numCommande = rs.getInt("idCommande");
            numClient = rs.getInt("idClient");
            int formuleLivraison = rs.getInt("idFormuleDeLivraison");
            int adresseLivraison = rs.getInt("idAdresseLivraisonCommande");
            Date dateCommande = rs.getDate("dateCommande");
            int statusCommande = rs.getInt("statusCommande");

            List<LigneDeCommande> numLigneCommande = new ArrayList();
            for (LigneDeCommande lldcom : llcom) {
                if (numCommande == lldcom.getNumCommande()) {
                    numLigneCommande.add(lldcom);
                }
            }

            commande = new Commande(numClient, numLigneCommande, numCommande, dateCommande, statusCommande, formuleLivraison, adresseLivraison);
            lcom.add(commande);
        }
       
        rs.close();
        rs1.close();
       }
        System.out.println("Commande "+ lcom.toString());
        return lcom;

    }

    
    public List<Commande> listeCommandeNumCom(int numCommande ) throws ClassNotFoundException, SQLException {
        List<Commande> lcom;
       try( Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement()){
           
        List<LigneDeCommande> llcom = new ArrayList<>();
        String req1 = "SELECT idLigneDeCommande, ISBN, idCommande, quantiteOuvrageLigneDeCommande, TVAOuvrage, tauxPromotion FROM ligneDeCommande";
        ResultSet rs1 = stm.executeQuery(req1);
        LigneDeCommande ligneCommande = null;
        while (rs1.next()) {
            int numCommandeLlcom = rs1.getInt("idCommande");
            // if (numCommande == numCommandeLlcom) {
            System.out.println("PBL: "+numCommandeLlcom);
            int numLigneCommande = rs1.getInt("idLigneDeCommande");
            String ISBNllcom = rs1.getString("ISBN");

            int quantiteOuvragellcom = rs1.getInt("quantiteOuvrageLigneDeCommande");
            Float TVAOuvrage = rs1.getFloat("TVAOuvrage");
            Float tauxPromo = rs1.getFloat("tauxPromotion");

            ligneCommande = new LigneDeCommande(numLigneCommande, numCommandeLlcom, quantiteOuvragellcom, TVAOuvrage, tauxPromo, ISBNllcom);
            llcom.add(ligneCommande);
             
        }

        lcom = new ArrayList<>();
        String req = "SELECT idCommande,  idClient, idFormuleDeLivraison, idAdresseLivraisonCommande, dateCommande, statusCommande FROM commande WHERE idCommande = "+numCommande+"";
        ResultSet rs = stm.executeQuery(req);
        Commande commande = null;
        while (rs.next()) {
            numCommande = rs.getInt("idCommande");
            int numClient = rs.getInt("idClient");
            int formuleLivraison = rs.getInt("idFormuleDeLivraison");
            int adresseLivraison = rs.getInt("idAdresseLivraisonCommande");
            Date dateCommande = rs.getDate("dateCommande");
            int statusCommande = rs.getInt("statusCommande");

            List<LigneDeCommande> numLigneCommande = new ArrayList();
            for (LigneDeCommande lldcom : llcom) {
                if (numCommande == lldcom.getNumCommande()) {
                    numLigneCommande.add(lldcom);
                }
            }

            commande = new Commande(numClient, numLigneCommande, numCommande, dateCommande, statusCommande, formuleLivraison, adresseLivraison);
            lcom.add(commande);
        }
       
        rs.close();
        rs1.close();
       }
        System.out.println("Commande "+ lcom.toString());
        return lcom;

    }

    
}
