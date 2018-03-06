/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author cdi205
 */
public class MaConnexion implements Serializable{
    private DataSource ds;

    public MaConnexion() throws NamingException {
        InitialContext context = new InitialContext();
        ds = (DataSource) context.lookup("jdbc/Librairie");
    }
    
    public Connection getConnection() throws SQLException{
        Connection cnt = ds.getConnection();
        return cnt;
    }
}
