
package Beans;

import accesBDD.MaConnexion;
import java.io.Serializable;
import javax.naming.NamingException;

public class FormuleDeLivraison implements Serializable{
    private MaConnexion mc;
    private int numFormuleLivraison;
    private String nomTransporteur;
    private Float prixFormuleDeLivraison;
    private String descriptionFormuleDeLivraison;
    
    
    public FormuleDeLivraison() throws NamingException {
        mc = new MaConnexion();
    }

    public FormuleDeLivraison(int numFormuleLivraison, String nomTransporteur, Float prixFormuleDeLivraison, String descriptionFormuleDeLivraison) {
        this.numFormuleLivraison = numFormuleLivraison;
        this.nomTransporteur = nomTransporteur;
        this.prixFormuleDeLivraison = prixFormuleDeLivraison;
        this.descriptionFormuleDeLivraison = descriptionFormuleDeLivraison;
    }

    public int getNumFormuleLivraison() {
        return numFormuleLivraison;
    }

    public Float getPrixFormuleDeLivraison() {
        return prixFormuleDeLivraison;
    }

    public String getDescriptionFormuleDeLivraison() {
        return descriptionFormuleDeLivraison;
    }

    public String getNomTransporteur() {
        return nomTransporteur;
    }

    @Override
    public String toString() {
        return "FormuleDeLivraison{" + "numFormuleLivraison=" + numFormuleLivraison + ", prixFormuleDeLivraison=" + prixFormuleDeLivraison + ", descriptionFormuleDeLivraison=" + descriptionFormuleDeLivraison + '}';
    }
    
}
