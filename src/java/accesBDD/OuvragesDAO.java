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

/**
 *
 * @author Gui2x en Java
 */
public class OuvragesDAO {

    private MaConnexion mc;

    public OuvragesDAO() throws NamingException {
        mc = new MaConnexion();
    }

    public List<Ouvrage> selectAllOuvrages() throws SQLException {
        String req = "select o.ISBN, o.titreOuvrage, o.quantiteDispoOuvrage, o.prixHTOuvrage, o.disponibiliteOuvrage, o.dateParutionOuvrage, o.TVAOuvrage, o.idFournisseur "
                + "from ouvrage o order by o.titreOuvrage";
        Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement();
        List<Ouvrage> lo = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String titreOuvrage = rs.getString("titreOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
                int idFournisseur = rs.getInt("idFournisseur");
                float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");
                Ouvrage o = new Ouvrage(isbn, titreOuvrage, disponibiliteOuvrage, quantiteDispoOuvrage, idFournisseur, prixHTOuvrage, TVAOuvrage, dateParutionOuvrage);
                lo.add(o);
            }
            rs.close();
        } finally {
            if (cnt != null) {
                cnt.close();
            }
        }
        return lo;
    }

    public Ouvrage selectOuvrageByISBN(String isbn) throws SQLException {
        String req = "select * from ouvrage where ISBN = ?";
        Ouvrage o = null;
        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req);) {
            stm.setString(1, isbn);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String titreOuvrage = rs.getString("titreOuvrage");
                String disponibiliteOuvrage = rs.getString("disponibiliteOuvrage");
                int quantiteDispoOuvrage = rs.getInt("quantiteDispoOuvrage");
                int idFournisseur = rs.getInt("idFournisseur");
                float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");
                o = new Ouvrage(isbn, titreOuvrage, disponibiliteOuvrage, quantiteDispoOuvrage, idFournisseur, prixHTOuvrage, TVAOuvrage, dateParutionOuvrage);
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
                int idFournisseur = rs.getInt("idFournisseur");
                float prixHTOuvrage = rs.getFloat("prixHTOuvrage");
                float TVAOuvrage = rs.getFloat("TVAOuvrage");
                Date dateParutionOuvrage = rs.getDate("dateParutionOuvrage");
                o = new Ouvrage(isbn, titreOuvrage, disponibiliteOuvrage, quantiteDispoOuvrage, idFournisseur, prixHTOuvrage, TVAOuvrage, dateParutionOuvrage);
                lo.add(o);
            }
        }
        return lo;
    }
}
