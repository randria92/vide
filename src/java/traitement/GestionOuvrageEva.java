/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import Beans.OuvrageEva;
import accesBDD.MaConnexion;
import accesBDD.OuvrageEvaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class GestionOuvrageEva implements Serializable{
    private MaConnexion mc;

    public GestionOuvrageEva() throws NamingException {
        mc = new MaConnexion();
    }
    
    public Float prixOuvrage(String ISBN) throws ClassNotFoundException, SQLException, NamingException {
        //PersistanceOuvrages PO = new PersistanceOuvrages();
        OuvrageEvaDAO ODAO = new OuvrageEvaDAO();
        List<OuvrageEva> Louv = ODAO.objetTousLesOuvrage();
        Float prixOuvrage = 0f;
        for (OuvrageEva ouv : Louv) {
            if (ouv.getISBN().equalsIgnoreCase(ISBN)) {
                Float prixOuv = ouv.getPrixHT();
                Float TVAOuv = ouv.getTVAOuvrage() / 100;
                Float promoOuv = ouv.getPromotionOuvrage() / 100;
                prixOuvrage = prixOuv + (prixOuv * TVAOuv) - (prixOuv * promoOuv);
            }
        }
        return prixOuvrage;

    }
}
