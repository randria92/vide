package accesBDD;

import Beans.Avis;
import Beans.client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author cdi211
 */
public class clientDAO {

    private MaConnexion mc;

    public clientDAO() throws NamingException {
        mc = new MaConnexion();
    }

    //---- liste adresses par  client -----
//    public List<adresse> rechercheAdresseParClient(String mail){
//
//    }
    //-- si mail existe pas la peine de s'inscrire --- renvoie mail existant.
    public List<client> rechercheClientparMail(String mail) throws SQLException {

        List<client> clt;
        try (Connection cnt = mc.getConnection()) {
            PreparedStatement stm = (PreparedStatement) cnt.createStatement();
            clt = new ArrayList();
            String req = "Select nomClient,prenomClient from client where emailClient=?";
            ResultSet rs = stm.executeQuery(req);
            stm.setString(1, mail);
            client clientTrouve = null;
            //--- on retourne ou non un resultat ici.
            if (rs.next()) {
                String nom = rs.getString("nomClient");
                String prenom = rs.getString("prenomClient");
                String email = rs.getString("emailClient");
                clientTrouve = new client(nom, prenom, email);
                clt.add(clientTrouve);
            }   //-- si on ne ferme pas ça risque d'étrangler le flux serveur
            stm.close();
        }

        return clt;
    }

    public boolean insertClient(client clt) throws SQLException {
        //String req = "Insert INTO client (idClient, nomClient, prenomClient, noteAvis , statusDeValidationAvis, commentaireAvis, dateModerationAvis) VALUES (?,?,?,?,?,?,?)";
        String req = "insert into client (civiliteClient,nomClient,"
                + "prenomClient,dateNaissanceClient,telephoneClient,"
                + "emailClient,motDePasseClient,dateAdhesionClient\n"
                + ")VALUES('madame',?,?,'1990-12-12','0123456789',?,?,'1990-12-12')";
        Connection cnt = mc.getConnection();
        PreparedStatement stm = cnt.prepareStatement(req);

        stm.setString(1, clt.getNomClient());
        stm.setString(2, clt.getPrenomClient());
        stm.setString(3, clt.getMotDePasseClient());
        stm.setString(4, clt.getEmailClient());

        stm.executeUpdate();
        stm.close();
        return true;

    }

}
