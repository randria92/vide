/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;

/**
 *
 * @author Eva Stonem
 */
public class Promotion {
    private String ISBNPromotion;
    private Float tauxPromotion;
    private Date dateDebutPromotion;
    private Date dateFinPromotion;
    private String descriptionPromotion;
    private String imagePromotion;

    public Promotion() {
    }

    public Promotion(String ISBNPromotion, Float tauxPromotion, Date dateDebutPromotion) {
        this.ISBNPromotion = ISBNPromotion;
        this.tauxPromotion = tauxPromotion;
        this.dateDebutPromotion = dateDebutPromotion;
    }
    
    public Promotion(Float tauxPromotion) {
        this.tauxPromotion = tauxPromotion;
    }

    public Float getTauxPromotion() {
        return tauxPromotion;
    }

    public Date getDateDebutPromotion() {
        return dateDebutPromotion;
    }

    public Date getDateFinPromotion() {
        return dateFinPromotion;
    }

    public String getDescriptionPromotion() {
        return descriptionPromotion;
    }

    public String getImagePromotion() {
        return imagePromotion;
    }

    @Override
    public String toString() {
        return "Promotion{" + "tauxPromotion=" + tauxPromotion + ", dateDebutPromotion=" + dateDebutPromotion + ", dateFinPromotion=" + dateFinPromotion + ", descriptionPromotion=" + descriptionPromotion + ", imagePromotion=" + imagePromotion + '}';
    }

    public String getISBNPromotion() {
        return ISBNPromotion;
    }
    
    
}
