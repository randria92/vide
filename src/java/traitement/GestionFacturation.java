/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import Beans.Facturation;
import Beans.LigneDeCommande;
import accesBDD.FacturationDAO;
import accesBDD.LigneDeCommandeDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Eva Stonem
 */
public class GestionFacturation implements Serializable{
    
    private FacturationDAO facDAO;

    public GestionFacturation() throws NamingException {
        facDAO = new FacturationDAO();
    }
    
    public List<String> getCleDefaut(){
        List<String> clefs = new ArrayList<>();
        clefs.add("N° Facture");
        clefs.add("Adresse de facturation");
        clefs.add("Date de Facture");
        clefs.add("Status Facture");
        return clefs;
    }
    
    public List <Facturation> listeFacturation (int numCommande) throws ClassNotFoundException, SQLException, NamingException, ParseException{
    
        List <Facturation> lFac = facDAO.listeFacturation(numCommande);
        for(Facturation f : lFac){
            System.out.println("f.getNumFacture();"+f.getNumFacture());
        }
        return lFac;
    }
    
}
