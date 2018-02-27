/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.OuvrageEva;
import Beans.Promotion;
import Beans.ThemeEtSousTheme;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class OuvrageEvaDAO implements Serializable{
     private MaConnexion mc;

    public OuvrageEvaDAO() throws NamingException {
        mc= new MaConnexion();
    }
     
    public List<OuvrageEva> objetTousLesOuvrage() throws ClassNotFoundException, SQLException {
        
        Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement();

        //Toute les promo rattachée aux ouvrages
        List<Promotion> promoTabl = new ArrayList();
        String req1 = "SELECT ISBN, ouvrage.idPromotion, tauxPromotion, dateDebutPromotion FROM promotion JOIN ouvrage ON promotion.idPromotion = ouvrage.idPromotion";
        ResultSet rs1 = stm.executeQuery(req1);
        Promotion promoOuvrage = null;
        while (rs1.next()) {//tableau de sous theme et theme associé au n°ISBN
            String ISBNPromo = rs1.getString("ISBN");
            Float tauxPromo = rs1.getFloat("tauxPromotion");
            Date datePromo = rs1.getDate("dateDebutPromotion");

            promoOuvrage = new Promotion(ISBNPromo, tauxPromo, datePromo);
            promoTabl.add(promoOuvrage);
        }

        //Tous les theme et sous theme rattachés aux ouvrages
        List<ThemeEtSousTheme> tablSouTheme = new ArrayList();
        String req3 = "SELECT ouv.ISBN , them.nomTheme, sthem.nomSousTheme FROM ouvrage ouv JOIN thematique the ON the.ISBN = ouv.ISBN JOIN sousTheme sthem ON the.nomSousTheme = sthem.nomSousTheme JOIN theme them ON sthem.nomTheme = them.nomTheme";
        ResultSet rs2 = stm.executeQuery(req3);
        ThemeEtSousTheme sousThemeLast = null;
        while (rs2.next()) {//tableau de sous theme et theme associé au n°ISBN
            String ISBNSousTheme = rs2.getString("ISBN");
            if (sousThemeLast == null || !ISBNSousTheme.equalsIgnoreCase(sousThemeLast.getISBNSousTheme())) {
                String nomSousTheme = rs2.getString("nomSousTheme");
                String nomTheme = rs2.getString("nomTheme");
                sousThemeLast = new ThemeEtSousTheme(ISBNSousTheme, nomTheme);
                if (nomSousTheme != null) {
                    List<String> sousThemeliste = sousThemeLast.getNomSousTheme();
                    sousThemeliste.add(nomSousTheme);
                }
                tablSouTheme.add(sousThemeLast);
            } else {
                String nomSousTheme = rs2.getString("nomSousTheme");
                List<String> sousThemeliste = sousThemeLast.getNomSousTheme();
                sousThemeliste.add(nomSousTheme);
            }
        }

        //Tous les ouvrages
        String req = "SELECT ISBN, titreOuvrage, sousTitreOuvrage, quantiteDispoOuvrage, prixHTOuvrage, disponibiliteOuvrage, TVAOuvrage FROM ouvrage ouv";
        List<OuvrageEva> tousLesOuvrage = new ArrayList();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            String ISBNOuvrage = rs.getString("ISBN");
            String titreOuvrage = rs.getString("titreOuvrage");
            String sousTitreOuvrage = rs.getString("sousTitreOuvrage");
            int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
            Float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
            String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
            Float TVAOuvrage = rs.getFloat("TVAOuvrage");

            List<ThemeEtSousTheme> themesOuvrage = new ArrayList<>();
            for (ThemeEtSousTheme sf : tablSouTheme) {//si l'isbn en cour est présent dans le tableau de theme tu ajoute la ligne du tableau theme a un nouveau tableau pour louvrage en cours
                if (ISBNOuvrage.equalsIgnoreCase(sf.getISBNSousTheme())) {
                    themesOuvrage.add(sf);
                }
            }

            Float promoOuv = null;
            for (Promotion promo : promoTabl) {
                if (ISBNOuvrage.equalsIgnoreCase(promo.getISBNPromotion())) {
                    promoOuv = promo.getTauxPromotion();
                }
            }
            OuvrageEva ouv = new OuvrageEva(ISBNOuvrage, titreOuvrage, sousTitreOuvrage, quantiteDispoOuvrage, prixHTOuvrage, disponibiliteOuvrage, TVAOuvrage, themesOuvrage, promoOuv);

            tousLesOuvrage.add(ouv);
        }
        //rs.close();
       // rs2.close();
        //rs1.close();
        return tousLesOuvrage;
        
        
    }
    
    public void reductionStockOuvrage(String ISBN, int quantiteOuvrageAchete) throws ClassNotFoundException, SQLException {
        
        Connection cnt = mc.getConnection();
        String req = "UPDATE ouvrage SET quantiteDispoOuvrage = ? WHERE ISBN = ?";
        PreparedStatement pstm = cnt.prepareCall(req);
       
        List<OuvrageEva> lOuv = this.objetTousLesOuvrage();

        for (OuvrageEva ouv : lOuv) {
            if (ouv.getISBN().equalsIgnoreCase(ISBN)) {
                
                int quantiteRestante = ouv.getQuantiteDispo() - quantiteOuvrageAchete;
                pstm.setString(1,String.valueOf(quantiteRestante));
                pstm.setString(2, ISBN);
                
                if (quantiteRestante == 0) {

                    String req1 = "UPDATE ouvrage SET disponibiliteOuvrage = 'Indisponible' WHERE ISBN = ?";
                    PreparedStatement pstm1 = cnt.prepareCall(req1);
                    pstm1.setString(1, ISBN);
                    pstm1.executeUpdate();
                    pstm1.close();
                }
            }
        }
        pstm.executeUpdate();
        //pstm.close();
    }

     
}
