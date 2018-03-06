package traitement;

import Beans.client;
import accesBDD.clientDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cdi211
 */
public class GestionClient implements Serializable {

    private clientDAO cltDAO;

    public GestionClient() {
    }

    public GestionClient(clientDAO cltDAO) {
        this.cltDAO = cltDAO;
    }

    public List<client> afficherInformationClient(String mail) throws SQLException{
        List<client> infoClt = cltDAO.rechercheClientparMail(mail);
        return infoClt;
    }
    
    
}
