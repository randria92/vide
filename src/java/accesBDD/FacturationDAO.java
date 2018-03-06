/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.Facturation;
import Beans.LigneDeCommande;
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
 * @author Eva Stonem
 */
public class FacturationDAO implements Serializable {

    private MaConnexion mc;

    public FacturationDAO() throws NamingException {
        mc = new MaConnexion();
    }

    public List<Facturation> listeFacturation(int numCommande) throws SQLException, ClassNotFoundException {
        List<Facturation> lfac = null;

        try (Connection cnt = mc.getConnection();
                Statement stm = cnt.createStatement()) {
            lfac = new ArrayList();

            String req = "SELECT idFacture, idCommande, idAdresseFacturation, dateFacturation, statusFacturation FROM facturation WHERE idCommande =" + numCommande;
            ResultSet rs = stm.executeQuery(req);
            Facturation facture = null;
            while (rs.next()) {
                int numCommandeFac = rs.getInt("idCommande");
                int numFacture = rs.getInt("idFacture");
                int numAdresseFact = rs.getInt("idAdresseFacturation");
                Date dateFacture = rs.getDate("dateFacturation");
                int statusFacturation = rs.getInt("statusFacturation");

                facture = new Facturation(numFacture, numCommandeFac, numAdresseFact, dateFacture, statusFacturation);
                lfac.add(facture);
            }
        }
        return lfac;
    }

    public void ajoutFactAUneNouvelleCommande(int numCommande, int numAdresse) throws ClassNotFoundException, SQLException {

        try (Connection cnt = mc.getConnection()) {

            String format = "yyyy-MM-dd";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
            java.util.Date dateAjourdhui = new java.util.Date();
            String dateFacturation = formater.format(dateAjourdhui.getTime());

            String req = "INSERT INTO facturation(dateFacturation, idAdresseFacturation, idCommande, statusFacturation)VALUES (?,?,?,?)";

            PreparedStatement pstm = cnt.prepareCall(req);
            pstm.setString(1, dateFacturation);
            pstm.setInt(2, numAdresse);
            pstm.setInt(3, numCommande);
            pstm.setInt(4, 52);

            pstm.executeUpdate();
        }
    }
}
