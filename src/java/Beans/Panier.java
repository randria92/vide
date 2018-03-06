package Beans;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Panier implements Serializable {

    HashMap<String, LigneDeCommande> map;

    public Panier() {
        this.map = new HashMap();
    }

    public List<String> getCleDefaut() {
        List<String> clefs = new ArrayList<>();
        clefs.add("Titre");
        clefs.add("Nombre d'articles");
        clefs.add("Promotion (%)");
        clefs.add("Prix TTC (€)");
        clefs.add("Economies Réalisées (€)");
        clefs.add("");
        clefs.add("");
        clefs.add("");
        return clefs;
    }

    public void add(String titre, String ISBN, Float prixHT, Float TVA, Float tauxPromotion, String disponibiliteOuvrage, int quantiteDispo) {//recupère dans attribut de session l'ISBN, prixHT, TVA, promotion
        if (this.map.containsKey(ISBN)) {
            LigneDeCommande i = this.map.get(ISBN);
            if(!i.getDisponibilite().equalsIgnoreCase("Indisponible") && i.getQuantiteDispo()>i.getQuantiteOuvrage()){
            i.changeQty(+1);
            }else{
                
            }
        } else {
            LigneDeCommande i = new LigneDeCommande(titre, 1, ISBN, prixHT, TVA, tauxPromotion, disponibiliteOuvrage, quantiteDispo);
            if(!i.getDisponibilite().equalsIgnoreCase("Indisponible")){
            this.map.put(ISBN, i);
            }
        }
    }

    public void dec(String ISBN) {
        if (this.map.containsKey(ISBN)) {
            LigneDeCommande i = this.map.get(ISBN);
            i.changeQty(-1);
            if (i.getQuantiteOuvrage() < 1) {
                del(ISBN);
            }
        }
    }

    public void del(String ISBN) {
        this.map.remove(ISBN);
    }

    public void clear() {
        this.map.clear();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Collection<LigneDeCommande> list() {
        return this.map.values();
    }

    public int nomBreDarticleTotal() {
        int nbrArticle = 0;
        for (LigneDeCommande lcom : this.list()) {
            nbrArticle += lcom.getQuantiteOuvrage();
        }
        return nbrArticle;
    }

    public String prixTotalUneligne(LigneDeCommande lcom) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        String prixUnelignePanierS = null;
        Float prixUnelignePanier = (lcom.getPrixHT() + (lcom.getPrixHT() * (lcom.getTVAOuvrage()/ 100))) - (lcom.getPrixHT() * (lcom.getPromotionOuvrage() / 100));
        prixUnelignePanierS = df.format(prixUnelignePanier);
        return prixUnelignePanierS;
    }

    public String economieUneLigne(LigneDeCommande lcom) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        
        String economieRealiseS = "/";
        Float economieRealise = 0f;
        
        if (lcom.getPromotionOuvrage() != null && lcom.getPromotionOuvrage() != 0f) {
            economieRealise = lcom.getQuantiteOuvrage() * (lcom.getPrixHT() + (lcom.getPrixHT() * (lcom.getTVAOuvrage()/ 100)) * (lcom.getPromotionOuvrage() / 100));
            economieRealiseS = df.format(economieRealise);
        }
        return economieRealiseS;
    }

    public String prixTotal() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String prixTotalPanierS = null;
        Float prixTotalPanier = 0f;
        for (LigneDeCommande lcom : this.list()) {
            Float prixTTCetPromo = lcom.getPrixHT() + (lcom.getPrixHT() * (lcom.getTVAOuvrage()/ 100)) - (lcom.getPrixHT() * (lcom.getPromotionOuvrage() / 100));
            prixTotalPanier += lcom.getQuantiteOuvrage() * prixTTCetPromo;
        }
        prixTotalPanierS = df.format(prixTotalPanier);
        return prixTotalPanierS;
    }
    
    public String prixTotalCommande(Float fdl){
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String prixTotalPanierS = null;
        Float prixTotalPanier = 0f;
        for (LigneDeCommande lcom : this.list()) {
            Float prixTTCetPromo = lcom.getPrixHT() + (lcom.getPrixHT() * (lcom.getTVAOuvrage()/ 100)) - (lcom.getPrixHT() * (lcom.getPromotionOuvrage() / 100));
            prixTotalPanier += lcom.getQuantiteOuvrage() * prixTTCetPromo;
        }
        prixTotalPanier += fdl;
        prixTotalPanierS = df.format(prixTotalPanier);
        return prixTotalPanierS;
    }
    
}
