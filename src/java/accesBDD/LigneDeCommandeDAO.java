/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.LigneDeCommande;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class LigneDeCommandeDAO implements Serializable{
    private MaConnexion mc;

    public LigneDeCommandeDAO() throws NamingException {
        mc = new MaConnexion();
    }
    
    public List<LigneDeCommande> listeLigneCommande(int numCommande) throws SQLException, ClassNotFoundException {
        List<LigneDeCommande> llcom = null;
        
        try(Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement()){
        llcom = new ArrayList();

        String req = "SELECT idLigneDeCommande, ISBN, idCommande, quantiteOuvrageLigneDeCommande, TVAOuvrage, tauxPromotion FROM ligneDeCommande WHERE idCommande ="+numCommande;
        ResultSet rs = stm.executeQuery(req);
        LigneDeCommande ligneCom = null;
        while (rs.next()) {
            int numCommandeLlcom = rs.getInt("idCommande");
            int numLigneCommande = rs.getInt("idLigneDeCommande");
            String ISBNllcom = rs.getString("ISBN");
            int quantiteOuvragellcom = rs.getInt("quantiteOuvrageLigneDeCommande");
            Float TVAOuvrage = rs.getFloat("TVAOuvrage");
            Float tauxPromo = rs.getFloat("tauxPromotion");

            ligneCom = new LigneDeCommande(numLigneCommande, numCommandeLlcom, quantiteOuvragellcom, TVAOuvrage, tauxPromo, ISBNllcom);
            llcom.add(ligneCom);
        }
        }
        return llcom;
    }

    public void ajoutLigneDeCommande(int nombreLigneCommande, String ISBN, int idCommande, int quantitéOuvrage, Float TVAOuvrage, Float tauxPromotion) throws ClassNotFoundException, SQLException {
        
        try(Connection cnt = mc.getConnection()){
        
        String req = "INSERT INTO ligneDeCommande "
                + "(ISBN,idCommande,quantiteOuvrageLigneDeCommande, TVAOuvrage, tauxPromotion) "
                + "VALUES "
                + "(?, ?, ?, ?, ?) ";

        PreparedStatement pstmt = cnt.prepareCall(req);

        
            pstmt.setString(1, ISBN);//ISBN
            pstmt.setInt(2, idCommande);//idCommande
            pstmt.setInt(3, quantitéOuvrage);//quantiteOuvrageLigneDeCommande
            pstmt.setFloat(4, TVAOuvrage);//TVAOuvrage
            pstmt.setFloat(5, tauxPromotion);//tauxPromotion
            pstmt.executeUpdate();
        }
        
        
    }
    
}
