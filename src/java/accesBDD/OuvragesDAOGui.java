/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import Beans.Ouvrage;
import Beans.Ouvrage;
import Beans.Promotion;
import Beans.ThemeEtSousTheme;

/**
 *
 * @author Gui2x en Java
 */
public class OuvragesDAOGui {

    private MaConnexion mc;

    public OuvragesDAOGui() throws NamingException {
        mc = new MaConnexion();
    }

    public List<Ouvrage> selectAllOuvrages() throws SQLException {
        List<Promotion> promoTabl = new ArrayList();
        String req1 = "SELECT ISBN, ouvrage.idPromotion, tauxPromotion, dateDebutPromotion FROM promotion JOIN ouvrage ON promotion.idPromotion = ouvrage.idPromotion";
        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req1);) {
            ResultSet rs1 = stm.executeQuery();
            Promotion promoOuvrage = null;
            while (rs1.next()) {//tableau de sous theme et theme associé au n°ISBN
                String ISBNPromo = rs1.getString("ISBN");
                Float tauxPromo = rs1.getFloat("tauxPromotion");
                java.util.Date datePromo = rs1.getDate("dateDebutPromotion");

                promoOuvrage = new Promotion(ISBNPromo, tauxPromo, datePromo);
                promoTabl.add(promoOuvrage);
            }
        }
        
        String req = "select o.ISBN, o.titreOuvrage, o.quantiteDispoOuvrage, o.prixHTOuvrage, o.disponibiliteOuvrage, o.dateParutionOuvrage, o.TVAOuvrage, o.idPromotion "
                + "from ouvrage o order by o.titreOuvrage";
         Ouvrage o = null;
         List<Ouvrage> lo;
       try( Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement()){
        lo = new ArrayList<>();
        
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                
                String isbn = rs.getString("ISBN");
                String titreOuvrage = rs.getString("titreOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
                int idPromotion = rs.getInt("idPromotion");
                float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");
                
                Float promoOuv = null;
                for (Promotion promo : promoTabl) {
                    if (isbn.equalsIgnoreCase(promo.getISBNPromotion())) {
                        promoOuv = promo.getTauxPromotion();
                    }
                }
                o = new Ouvrage(isbn, titreOuvrage, quantiteDispoOuvrage,prixHTOuvrage,disponibiliteOuvrage , dateParutionOuvrage, TVAOuvrage, promoOuv);
                lo.add(o);
            
        }
       }
        return lo;
    }

    public Ouvrage selectOuvrageByISBN(String isbn) throws SQLException {

        List<Promotion> promoTabl = new ArrayList();
        String req1 = "SELECT ISBN, ouvrage.idPromotion, tauxPromotion, dateDebutPromotion FROM promotion JOIN ouvrage ON promotion.idPromotion = ouvrage.idPromotion";
        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req1);) {
            ResultSet rs1 = stm.executeQuery();
            Promotion promoOuvrage = null;
            while (rs1.next()) {//tableau de sous theme et theme associé au n°ISBN
                String ISBNPromo = rs1.getString("ISBN");
                Float tauxPromo = rs1.getFloat("tauxPromotion");
                java.util.Date datePromo = rs1.getDate("dateDebutPromotion");

                promoOuvrage = new Promotion(ISBNPromo, tauxPromo, datePromo);
                promoTabl.add(promoOuvrage);
            }
        }
        String req = "select * from ouvrage where ISBN = ?";
        Ouvrage o = null;
        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req);) {

            stm.setString(1, isbn);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String ISBNOuvrage = rs.getString("ISBN");
                String titreOuvrage = rs.getString("titreOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");

                Float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                Float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");

                Float promoOuv = null;
                for (Promotion promo : promoTabl) {
                    if (ISBNOuvrage.equalsIgnoreCase(promo.getISBNPromotion())) {
                        promoOuv = promo.getTauxPromotion();
                    }
                }
                o = new Ouvrage(isbn, titreOuvrage, quantiteDispoOuvrage,prixHTOuvrage,disponibiliteOuvrage , dateParutionOuvrage, TVAOuvrage, promoOuv);
               
            }
        }
        return o;
    }

    public List<Ouvrage> rechOuvrage(String recherche) throws SQLException {
        String req = "select * from ouvrage where titre like %?%";
        Ouvrage o = null;
        List<Ouvrage> lo = new ArrayList<>();
        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req);) {
            stm.setString(1, recherche);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String isbn = rs.getString("ISBN");
                String titreOuvrage = rs.getString("titreOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
                int idPromotion = rs.getInt("idPromotion");
                float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");
                o = new Ouvrage(isbn, titreOuvrage, disponibiliteOuvrage, quantiteDispoOuvrage, idPromotion, prixHTOuvrage, TVAOuvrage, dateParutionOuvrage);
                lo.add(o);
            }
        }
        return lo;
    }

    public List<Ouvrage> objetTousLesOuvrage() throws ClassNotFoundException, SQLException {
        List<Ouvrage> tousLesOuvrage;
        try (Connection cnt = mc.getConnection();
                Statement stm = cnt.createStatement()) {

            //Toute les promo rattachée aux ouvrages
            List<Promotion> promoTabl = new ArrayList();
            String req1 = "SELECT ISBN, ouvrage.idPromotion, tauxPromotion, dateDebutPromotion FROM promotion JOIN ouvrage ON promotion.idPromotion = ouvrage.idPromotion";
            ResultSet rs1 = stm.executeQuery(req1);
            Promotion promoOuvrage = null;
            while (rs1.next()) {//tableau de sous theme et theme associé au n°ISBN
                String ISBNPromo = rs1.getString("ISBN");
                Float tauxPromo = rs1.getFloat("tauxPromotion");
                java.util.Date datePromo = rs1.getDate("dateDebutPromotion");

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
            tousLesOuvrage = new ArrayList();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                String ISBNOuvrage = rs.getString("ISBN");
                String titreOuvrage = rs.getString("titreOuvrage");
                String sousTitreOuvrage = rs.getString("sousTitreOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
                Float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                Float TVAOuvrage = rs.getFloat("TVAOuvrage");

//            List<ThemeEtSousTheme> themesOuvrage = new ArrayList<>();
//            for (ThemeEtSousTheme sf : tablSouTheme) {//si l'isbn en cour est présent dans le tableau de theme tu ajoute la ligne du tableau theme a un nouveau tableau pour louvrage en cours
//                if (ISBNOuvrage.equalsIgnoreCase(sf.getISBNSousTheme())) {
//                    themesOuvrage.add(sf);
//                }
//            }
                Float promoOuv = null;
                for (Promotion promo : promoTabl) {
                    if (ISBNOuvrage.equalsIgnoreCase(promo.getISBNPromotion())) {
                        promoOuv = promo.getTauxPromotion();
                    }
                }
                Ouvrage ouv = new Ouvrage(ISBNOuvrage, titreOuvrage, sousTitreOuvrage, quantiteDispoOuvrage, prixHTOuvrage, disponibiliteOuvrage, TVAOuvrage, promoOuv);

                tousLesOuvrage.add(ouv);
            }
        //rs.close();
            // rs2.close();
            //rs1.close();
        }
        return tousLesOuvrage;

    }

}
