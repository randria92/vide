/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class LigneDeCommandeDAO {
    private MaConnexion mc;

    public LigneDeCommandeDAO() throws NamingException {
        mc = new MaConnexion();
    }
    
    
}
