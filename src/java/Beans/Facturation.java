/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import accesBDD.StatusDOA;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;

/**
 *
 * @author Eva Stonem
 */
public class Facturation implements Serializable{
    private int numFacture;
    private int numCommande;
    private int numAdresseFact;
    private Date dateFacturation;
    private int numStatusFact;

    public Facturation() {
    }

    public Facturation(int numFacture, int numCommande, int numAdresseFact, Date dateFacturation, int numStatusFact) {
        this.numFacture = numFacture;
        this.numCommande = numCommande;
        this.numAdresseFact = numAdresseFact;
        this.dateFacturation = dateFacturation;
        this.numStatusFact = numStatusFact;
    }

    public String getStatusFacture(int statusFacture) throws NamingException, SQLException, ClassNotFoundException {
        StatusLibrairie sl = new StatusLibrairie(statusFacture);
        StatusDOA sDOA = new StatusDOA();
        String status = sDOA.affichageStatus(statusFacture);
        return status;
    }
    
    public int getNumFacture() {
        return numFacture;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public int getNumAdresseFact() {
        return numAdresseFact;
    }

    public Date getDateFacturation() {
        return dateFacturation;
    }

    public String getDateFacturation(String recherche) throws ParseException {
        String dateCommandeS = null;
        SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd");
        
        SimpleDateFormat sdf01 = new SimpleDateFormat("dd MMMM yyyy");
        
        try {
        Date dateCommandeSQL = new SimpleDateFormat("yyyy-MM-dd").parse(recherche);
        dateCommandeS = sdf01.format(dateCommandeSQL);
        return dateCommandeS;
        }catch(ParseException exx){
        System.out.println("NoN sql");
        }
        try{
        Date dateCommandeSQL = new SimpleDateFormat("dd/MM/yyyy").parse(recherche);
        dateCommandeS = sdf01.format(dateCommandeSQL);
        return dateCommandeS;
        }catch(ParseException exj){
            System.out.println("nonJava");
        }
        
        return dateCommandeS;
    }
    
    public int getNumStatusFact() {
        return numStatusFact;
    }

    @Override
    public String toString() {
        return "Facturation{" + "numFacture=" + numFacture + ", numCommande=" + numCommande + ", numAdresseFact=" + numAdresseFact + ", dateFacturation=" + dateFacturation + ", numStatusFact=" + numStatusFact + '}';
    }
    
    
}
