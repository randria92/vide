package traitement;

import Beans.FormuleDeLivraison;
import accesBDD.FormuleLivraisonDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class GestionFormuleLivraison implements Serializable {

    private FormuleLivraisonDAO fDAO;

    public GestionFormuleLivraison() throws NamingException {
        fDAO = new FormuleLivraisonDAO();
    }

    public Float prixFLV(int numFormuleDeLivraison) throws ClassNotFoundException, SQLException, NamingException {

        List<FormuleDeLivraison> lFdl = fDAO.listeFormuleLivraison();
        Float prixFdL = 0f;

        for (FormuleDeLivraison fdl : lFdl) {
            if (numFormuleDeLivraison == fdl.getNumFormuleLivraison()) {
                prixFdL = fdl.getPrixFormuleDeLivraison();
            }
        }
        return prixFdL;
    }
    
    public String nomTransporteurFDL(int numFormuleDeLivraison) throws ClassNotFoundException, SQLException, NamingException{
        
        List <FormuleDeLivraison> lFdl = fDAO.listeFormuleLivraison();
        String nomTransporteur = null;
        
        for (FormuleDeLivraison fdl : lFdl){
            if (numFormuleDeLivraison == fdl.getNumFormuleLivraison()){
                nomTransporteur = fdl.getNomTransporteur();
            }
        }
        return nomTransporteur;
    }
    
    
}
