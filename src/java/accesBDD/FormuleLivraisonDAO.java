
package accesBDD;

import Beans.FormuleDeLivraison;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import traitement.GestionFormuleLivraison;

public class FormuleLivraisonDAO implements Serializable{
    private MaConnexion mc;

    public FormuleLivraisonDAO() throws NamingException {
        mc = new MaConnexion();
    }
    
    public List<FormuleDeLivraison> listeFormuleLivraison() throws ClassNotFoundException, SQLException, NamingException {
        
        Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement();
        
        List<FormuleDeLivraison> fdl = new ArrayList<>();
            String req = "SELECT idFormuleDeLivraison, nomTransporteur, prixFormuleDeLivraison, descriptionFormuleDeLivraison FROM formuleDeLivraison";
            ResultSet rs = stm.executeQuery(req);
            FormuleDeLivraison formuleLivraison = null;
            while (rs.next()) {
                int numFlv = rs.getInt("idFormuleDeLivraison");
                String nomFlv = rs.getString("nomTransporteur");
                Float prixFlv = rs.getFloat("prixFormuleDeLivraison");
                String descFlv = rs.getString("descriptionFormuleDeLivraison");
                
            formuleLivraison = new FormuleDeLivraison(numFlv, nomFlv, prixFlv, descFlv);
            fdl.add(formuleLivraison);
            }
            return fdl;
    }
    
    
    
    
}
