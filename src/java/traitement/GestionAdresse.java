/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import Beans.Adresse;
import accesBDD.AdresseDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class GestionAdresse implements Serializable{
    private AdresseDAO aDAO;

    public GestionAdresse() throws NamingException {
        aDAO = new AdresseDAO();
    }
    
    public List<String> getCleDefaut(){
        List<String> clefs = new ArrayList<>();
        clefs.add("Client");
        clefs.add("Adresse");
        return clefs;
    }
    public List<Adresse> listeAdresseClient(int numClient) throws ClassNotFoundException, SQLException{
        List<Adresse> lA = aDAO.listeAdresseClient(numClient);
        return lA;
    }
    public String affichAdresse(int numClient) throws ClassNotFoundException, SQLException{
        
        List<Adresse> la = aDAO.listeAdresseClient(numClient);
        String adrS = null;
        for(Adresse adr : la){
             adrS = adr.getNumVoie()+" "+ adr.getTypeVoie()+" "+ adr.getNomVoie()+" "+ adr.getCodePostale()+" "+ adr.getVille()+" "+ adr.getPays();
        }
        return adrS;
    }
    
    public List<String> affichAdresseList(int numClient) throws ClassNotFoundException, SQLException, NamingException{
        
        List<String> lStringAdresse = new ArrayList();
        List<Adresse> la = aDAO.listeAdresseClient(numClient);
         
        for(Adresse adr : la){
            String adrS = adr.getNumVoie()+" "+ adr.getTypeVoie()+" "+ adr.getNomVoie()+" "+ adr.getCodePostale()+" "+ adr.getVille()+" "+ adr.getPays();
            lStringAdresse.add(adrS);
            System.out.println("adr:" +adrS);
        }
        return lStringAdresse;
    }
    
    public void ajoutAdresse(int numClient, String nomReceptionnaire, String prenomReceptionnaire, String numVoie,String typeVoie, String nomVoie,String codePostal, String ville,String pays, String complement) throws SQLException, ClassNotFoundException{
    aDAO.ajoutAdresse(numClient, nomReceptionnaire, prenomReceptionnaire, numVoie, typeVoie, nomVoie, codePostal, ville, pays, complement);
    }
    
}
