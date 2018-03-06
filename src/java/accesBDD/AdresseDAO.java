/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.Adresse;
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
public class AdresseDAO {
    private MaConnexion mc;

    public AdresseDAO() throws NamingException {
        mc = new MaConnexion();
    }
    public List<Adresse> listeAdresseClient(int numClient) throws ClassNotFoundException, SQLException{
        try(
        Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement()){
        
        List<Adresse> la = new ArrayList<>();
        String req = "SELECT cli.idClient, nomReceptionnaireAdresse, prenomReceptionnaireAdresse ,adr.idAdresse , adr.numVoieAdresse ,adr.typeVoieAdresse,adr.nomVoieAdresse,adr.codePostalAdresse,adr.complementAdresse,adr.villeAdresse,adr.paysAdresse "
                + "FROM client cli "
                + "JOIN adresse adr "
                + "ON cli.idClient = adr.idClient "
                + " WHERE cli.idClient  = " + numClient + " ";
        ResultSet rs = stm.executeQuery(req);
        Adresse adresseClient = null;
        while (rs.next()) {
            String prenom = rs.getString("nomReceptionnaireAdresse"); 
            String nom = rs.getString("prenomReceptionnaireAdresse");
            int numAdresse = rs.getInt("idAdresse");
            String numVoie = rs.getString("numVoieAdresse");
            String typeVoie = rs.getString("typeVoieAdresse");
            String nomVoie = rs.getString("nomVoieAdresse");
            String cP = rs.getString("codePostalAdresse");
            String complement = rs.getString("complementAdresse");
            String ville = rs.getString("villeAdresse");
            String pays = rs.getString("paysAdresse");
            adresseClient = new Adresse(numAdresse,nom, prenom, numVoie, typeVoie, nomVoie,complement,cP, ville, pays);
            la.add(adresseClient);
        }
        rs.close();
        stm.close();
        return la;
    }
    }
    
     public void ajoutAdresse(int numClient, String nomReceptionnaire, String prenomReceptionnaire, String numVoie,String typeVoie, String nomVoie,String codePostal, String ville,String pays, String complement) throws SQLException, ClassNotFoundException{
        try(
        Connection cnt = mc.getConnection()){
        
        String req = "INSERT INTO adresse "
                + "(idClient,nomReceptionnaireAdresse,prenomReceptionnaireAdresse, numVoieAdresse, typeVoieAdresse, nomVoieAdresse,codePostalAdresse,villeAdresse, paysAdresse, complementAdresse ) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pstmt = cnt.prepareCall(req);
            pstmt.setInt(1, Integer.valueOf(numClient));
            pstmt.setString(2, nomReceptionnaire);
            pstmt.setString(3, prenomReceptionnaire);
            pstmt.setString(4, numVoie);
            pstmt.setString(5, typeVoie);
            pstmt.setString(6, nomVoie);
            pstmt.setString(7, codePostal);
            pstmt.setString(8, ville);
            pstmt.setString(9, pays);
            pstmt.setString(10, complement);
            
            pstmt.executeUpdate();
        pstmt.close();
        }
    }

     public String affichAdresse(int numClient) throws ClassNotFoundException, SQLException, NamingException{
        
        List<Adresse> la = this.listeAdresseClient(numClient);
        String adrS = null;
        for(Adresse adr : la){
             adrS = adr.getNumVoie()+" "+ adr.getTypeVoie()+" "+ adr.getNomVoie()+" "+ adr.getCodePostale()+" "+ adr.getVille()+" "+ adr.getPays();
        }
        return adrS;
    }
}
