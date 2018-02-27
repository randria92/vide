/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import accesBDD.StatusDOA;
import java.io.Serializable;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class GestionStatus implements Serializable{
    private StatusDOA sDOA;

    public GestionStatus() throws NamingException {
        sDOA = new StatusDOA();
    }
    
    
}
