package accesBDD;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import Beans.Avis;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class avisDAO implements Serializable {

    private MaConnexion mc;

    public avisDAO() throws NamingException {
        mc = new MaConnexion();
    }

    public List<Avis> listAvis() throws SQLException {
        String req = "select idAvis"
                + ",noteAvis"
                + ",commentaireAvis"
                + ",dateAvis"
                + "FROM avis";
        List<Avis> la = new ArrayList<>();
        try (Connection cnt = mc.getConnection();
                Statement stm = cnt.createStatement();
                ResultSet rs = stm.executeQuery(req)) {
            while (rs.next()) {
                Avis avs = new Avis();
                avs.setIdAvis(rs.getInt("idAvis"));
                avs.setISBN(rs.getString("ISBN"));
                avs.setNoteAvis(rs.getInt("noteAvis"));
                avs.setCommentaireAvis(rs.getString("commentaireAvis"));
                avs.setDateAvis(rs.getDate("dateAvis"));
                la.add(avs);
            }
        }
        return la;
    }

    public boolean insertAvis(Avis avs) throws SQLException {
        String req = "Insert INTO avis (idClient, idLigneDeCommande, ISBN, noteAvis , statusDeValidationAvis, commentaireAvis, dateModerationAvis) VALUES (?,?,?,?,?,?,?)";

        try (Connection cnt = mc.getConnection();
                PreparedStatement stm = cnt.prepareStatement(req)) {

            stm.setInt(1, avs.getIdClient());
            stm.setInt(2, avs.getIdLigneDeCommande());
            stm.setString(3, avs.getISBN());
            stm.setInt(4, avs.getNoteAvis());
            System.out.println("avs.getCommentaireAvis().isEmpty() "+avs.getCommentaireAvis().isEmpty());
//            if (avs.getCommentaireAvis().isEmpty()) {
//                stm.setString(6, null);
//            } else {
                stm.setString(6, avs.getCommentaireAvis());
//            }
            stm.setInt(5, avs.getStatusDeValidationAvis());
            stm.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            stm.executeUpdate();
            stm.close();
            return true;
        }

    }

}
