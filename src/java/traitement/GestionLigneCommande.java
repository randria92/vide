/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import Beans.Commande;
import Beans.LigneDeCommande;
import Beans.Ouvrage;
import accesBDD.CommandeDAO;
import accesBDD.LigneDeCommandeDAO;
import accesBDD.OuvrageEvaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class GestionLigneCommande implements Serializable{
    private LigneDeCommandeDAO lComDAO;
   

    public GestionLigneCommande() throws NamingException {
        lComDAO = new LigneDeCommandeDAO();
        
    }
    
    public List<String> getCleDefaut(){
        List<String> clefs = new ArrayList<>();
        clefs.add("Titre");
        clefs.add("Nombre d'articles");
        clefs.add("Promotion (%)");
        clefs.add("Prix TTC (€)");
        clefs.add("Economies Réamlisées (€)");
        clefs.add("Avis");
        
        return clefs;
    }
    
     public List <LigneDeCommande> listLigneCommande (int numCommande) throws ClassNotFoundException, SQLException, NamingException, ParseException{
    
        List <LigneDeCommande> llcom = lComDAO.listeLigneCommande(numCommande);
        
        return llcom;
    }
    
    
    
}
