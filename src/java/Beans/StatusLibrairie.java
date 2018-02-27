/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Eva Stonem
 */
public class StatusLibrairie implements Serializable{
    private int numStatus;
    private String typeStatus;
    private String descriptionStatus;

    public StatusLibrairie() {
    }

    public StatusLibrairie(int numStatus, String descriptionStatus) {
        this.numStatus = numStatus;
        this.descriptionStatus = descriptionStatus;
    }

    public StatusLibrairie(int numStatus) {
        this.numStatus = numStatus;
    }

    public int getNumStatus() {
        return numStatus;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public String getDescriptionStatus() {
        return descriptionStatus;
    }
    public Vector<StatusLibrairie> getVector() {
        Vector v= new Vector();
        v.add(this.numStatus);
        v.add(this.typeStatus);
        v.add(this.descriptionStatus);
        return v;
    }

    public StatusLibrairie(String descriptionStatus) {
        this.descriptionStatus = descriptionStatus;
    }
    
}
