/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import accesBDD.AdresseDAO;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Eva Stonem
 */
public class Adresse {
    private int numAdresse;
    private String nomReceptionnaire;
    private String prenomReceptionnaire;
    private String numVoie;
    private String typeVoie;
    private String nomVoie;
    private String complement;//null
    private String codePostale;
    private String ville;
    private String pays;

    public Adresse() {
    }

    public Adresse(String nomReceptionnaire, String prenomReceptionnaire, String numVoie, String typeVoie, String nomVoie, String codePostale, String ville, String pays) {
        this.nomReceptionnaire = nomReceptionnaire;
        this.prenomReceptionnaire = prenomReceptionnaire;
        this.numVoie = numVoie;
        this.typeVoie = typeVoie;
        this.nomVoie = nomVoie;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
    }

    public Adresse(int numAdresse,String nomReceptionnaire, String prenomReceptionnaire, String numVoie, String typeVoie, String nomVoie, String complement, String codePostale, String ville, String pays) {
        this.numAdresse = numAdresse;
        this.nomReceptionnaire = nomReceptionnaire;
        this.prenomReceptionnaire = prenomReceptionnaire;
        this.numVoie = numVoie;
        this.typeVoie = typeVoie;
        this.nomVoie = nomVoie;
        this.complement = complement;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
    }

 
    public String getNomReceptionnaire() {
        return nomReceptionnaire;
    }

    public String getPrenomReceptionnaire() {
        return prenomReceptionnaire;
    }

    public String getNumVoie() {
        return numVoie;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public String getComplement() {
        return complement;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return  numVoie + " " + typeVoie + " " + nomVoie + " "+ codePostale + " " + ville + " " + pays ;
    }

    

    public int getNumAdresse() {
        return numAdresse;
    }
    public List<String> affichAdresse(int numClient) throws ClassNotFoundException, SQLException, NamingException{
        AdresseDAO aDAO = new AdresseDAO();
        List<String> lStringAdresse = null;
        List<Adresse> la = aDAO.listeAdresseClient(numClient);
        String adrS = null;
        for(Adresse adr : la){
             adrS = adr.getNumVoie()+" "+ adr.getTypeVoie()+" "+ adr.getNomVoie()+" "+ adr.getCodePostale()+" "+ adr.getVille()+" "+ adr.getPays();
        lStringAdresse.add(adrS);
        }
        return lStringAdresse;
    }
}
