/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesBDD;

import Beans.client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public List<client> rechercheClientparMail(String mail) throws SQLException{
       
        Connection cnt = mc.getConnection();
        PreparedStatement stm =  (PreparedStatement) cnt.createStatement();
         
        List<client> clt = new ArrayList();
        String req = "Select * from client where emailClient=?";
        ResultSet rs = stm.executeQuery(req);
        client clientTrouve = null;
        if(rs != null){
            String nom = rs.getString("nomClient");
            String prenom = rs.getString("prenomClient");
            String email = rs.getString("emailClient");
            clientTrouve = new client(nom,prenom,email);
            clt.add(clientTrouve);
        }
        
        //-- si on ne ferme pas ça risque d'étrangler le flux serveur
        stm.close();
       cnt.close();

        return clt;
    }
    
    
    
    
}
