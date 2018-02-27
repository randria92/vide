/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.StatusLibrairie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;

/**
 *
 * @author cdi205
 */
public class StatusDOA {
     private MaConnexion mc;

    public StatusDOA() throws NamingException {
        mc = new MaConnexion();
    }
     public String affichageStatus(int status) throws SQLException, ClassNotFoundException {
        
        
        String lsL = null;
        String req = "Select idStatusLibrairie, descriptionStatusLibrairie FROM statusLibrairie";
        Connection cnt = mc.getConnection();
        Statement stm = cnt.createStatement();
        ResultSet rs = stm.executeQuery(req);
        StatusLibrairie sL = null;
        while (rs.next()) {
            int numStatus = rs.getInt("idStatusLibrairie");
            String descriptionStatus = rs.getString("descriptionStatusLibrairie");
            sL = new StatusLibrairie(numStatus);
            if (status == numStatus) {
                lsL = descriptionStatus;
            }

        }
        return lsL;
    }
     
}
