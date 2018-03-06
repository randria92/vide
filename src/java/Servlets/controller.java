/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Adresse;
import Beans.Avis;
import Beans.Commande;
import Beans.Facturation;
import Beans.FormuleDeLivraison;
import Beans.LigneDeCommande;
import Beans.Ouvrage;
import Beans.Panier;
import accesBDD.OuvragesDAOGui;
import accesBDD.avisDAO;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import traitement.GestionAdresse;
import traitement.GestionCommande;
import traitement.GestionFacturation;
import traitement.GestionFormuleLivraison;
import traitement.GestionLigneCommande;

/**
 *
 * @author cdi205
 */
@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class controller extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String pageJSP = "/WEB-INF/jspAcceuil.jsp";
        String section = request.getParameter("section");

        HttpSession session = request.getSession();

        boolean login = false;

        if ("pageSuivante".equals(section)) {
            String lien = (String) session.getAttribute("pageSuivante");
            section = lien;
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        boolean ajoutAvis = false;
        if ("ajoutAvis".equals(section)) {
            ajoutAvis = true;
            request.setAttribute("ajoutAvis", ajoutAvis);
            request.setAttribute("numLigneComAvis", request.getParameter("numLigneComAvis"));
            request.setAttribute("ISBNAvis", request.getParameter("ISBNAvis"));
            if (request.getParameter("Envoyer") != null) {
                try {//si bouton ok saisi
                    ajoutAvis = true;
                    request.setAttribute("ajoutAvis", ajoutAvis);
                    String lc = request.getParameter("numLigneComAvis");
                    String ISBN = request.getParameter("ISBNAvis");
                    String idc = request.getParameter("1");
                    String not = request.getParameter("note");
                    String com = request.getParameter("commentaire");
                    System.out.println("request.getParameter(\"note\"): "+request.getParameter("note"));
                    int intLigneCommande = Integer.parseInt(lc);
                    int intClient = 1;

                    Avis avi = new Avis();
                    avi.setIdLigneDeCommande(intLigneCommande);
                    avi.setISBN(ISBN);
                    avi.setIdClient(intClient);
                    avi.setCommentaireAvis(com);
                    avi.setStatusDeValidationAvis(20);
                    switch (not) {
                        case "TresSatisfait":
                            avi.setNoteAvis(20);
                            break;
                        case "Satisfait":
                            avi.setNoteAvis(15);
                            break;
                        case "PasMal":
                            avi.setNoteAvis(12);
                            break;
                        case "moyen":
                            avi.setNoteAvis(10);
                            break;
                        case "nul":
                            avi.setNoteAvis(0);
                            break;
                        default:
                            avi.setNoteAvis(20);
                    }
                    
                    avisDAO avid = new avisDAO();
                    boolean ajoutNouvelleAvis = avid.insertAvis(avi);
                    request.setAttribute("validationAjoutAvis", ajoutNouvelleAvis);
                    request.setAttribute("objetAvis", avi);
                    System.out.println("com: "+avi.getCommentaireAvis());
                } catch (SQLException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        if (getServletContext().getAttribute("gestionCommande") == null) {
            try {
                getServletContext().setAttribute("gestionCommande", new GestionCommande());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        GestionCommande gestionCommande = (GestionCommande) getServletContext().getAttribute("gestionCommande");

        if ("menu-main".equals(section)) {
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        if ("connexionClient".equals(section) || login) {
            //uniquement connexion validé
            login = true;
            request.setAttribute("connexionClient", login);
            request.setAttribute("pageSuivante", request.getParameter("pageSuivante"));
            session.setAttribute("pageSuivante", request.getParameter("pageSuivante"));

            //Cookie loginCookie = new Cookie("loginCookieName", "true");
            //response.addCookie(loginCookie);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
      
       

        boolean listCommandeB = false;
        if ("listCommande".equals(section)) {
            listCommandeB = true;
            request.setAttribute("listCommandeB", listCommandeB);
            try {
                List<String> clefs = gestionCommande.getCleDefaut();
                List<Commande> lcom = gestionCommande.listCommande(1);//client
                
                request.setAttribute("clefsListCommande", clefs);
                request.setAttribute("listCommande", lcom);
                pageJSP = "/WEB-INF/menus/menu-main.jsp";

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (getServletContext().getAttribute("gestionDetailFacture") == null) {
            try {
                getServletContext().setAttribute("gestionDetailFacture", new GestionFacturation());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        if (getServletContext().getAttribute("gestionAdresseClient") == null) {
            try {
                getServletContext().setAttribute("gestionAdresseClient", new GestionAdresse());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        GestionAdresse gestionAdresse = (GestionAdresse) getServletContext().getAttribute("gestionAdresseClient");

        GestionFacturation gestionDetailFacture = (GestionFacturation) getServletContext().getAttribute("gestionDetailFacture");

        if (getServletContext().getAttribute("gestionDetailCommande") == null) {
            try {
                getServletContext().setAttribute("gestionDetailCommande", new GestionLigneCommande());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        GestionLigneCommande gestionDetailCommande = (GestionLigneCommande) getServletContext().getAttribute("gestionDetailCommande");
        
        //-----------------------------------------------detailCommande-----------------------
        boolean effCom = false;
        if ("detailCommande".equals(section)) {
            effCom = true;
            request.setAttribute("affCommande", effCom);
            try {

                int numCommande = Integer.valueOf(request.getParameter("numCommande"));
                int statusCommande = Integer.valueOf(request.getParameter("statusCommande"));
                if (statusCommande == 32) {
                    request.setAttribute("valide", true);
                }

                List<String> clefs = gestionDetailCommande.getCleDefaut();
                List<LigneDeCommande> llCom = gestionDetailCommande.listLigneCommande(numCommande);//Depend de la ligne selectionné

                request.setAttribute("clefsListLigneCommandeCommande", clefs);
                request.setAttribute("listLigneCommande", llCom);

                List<String> clefsf = gestionDetailFacture.getCleDefaut();
                List<Facturation> lFac = gestionDetailFacture.listeFacturation(numCommande);//Depend de la ligne selectionné
                List<Adresse> listeAdresse = gestionAdresse.listeAdresseClient(1);//numClient

                request.setAttribute("afficheAdresse", listeAdresse);
                request.setAttribute("clefsListFacture", clefsf);
                request.setAttribute("listFacture", lFac);
                
                pageJSP = "/WEB-INF/menus/menu-main.jsp";

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//         boolean affFact = false;
//        if ("detailFacturation".equals(section)) {
//            affFact = true;
//            
//            request.setAttribute("affFact", affFact);
//            try {
//
//                int numCommande = Integer.valueOf(request.getParameter("numCommande"));
//                
//                pageJSP = "/WEB-INF/menus/menu-main.jsp";
//
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParseException ex) {
//                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        if ("reinitialiserAdresseFact".equals(section)) {
            session.removeAttribute("adresseFacValidee");
            section = "adresseClient";
        }
        if ("reinitialiserAdresseLiv".equals(section)) {
            session.removeAttribute("adresseLivValidee");
            section = "adresseClient";
        }
        //-----------------------------------------------ajoutAdresse-----------------------
        boolean infosAdresse = false;
        boolean infosAdresseFacturation = false;
        boolean adresseLivValidee = false;
        boolean adresseFacValidee = false;
        boolean adresseFacAjoute = false;
        boolean adresseLivAjoute = false;
        if ("ajoutAdresse".equals(section)) {
            try {
                //nouvelle Adresse
                if (request.getParameter("adresse") != null && session.getAttribute("adresseLivValidee") == null) {//Un radio bouton adresse selectionné
                    if (request.getParameter("adresse").equals("nouvelleAdresse")) {//Ajout d'une nouvelle
                        int numClient = 1;
                        String nomReceptionnaire = request.getParameter("nomReceptionnaire");
                        String prenomReceptionnaire = request.getParameter("prenomReceptionnaire");
                        String numVoie = request.getParameter("numVoie");
                        String typeVoie = request.getParameter("typeVoie");
                        String nomVoie = request.getParameter("nomVoie");
                        String codePostal = request.getParameter("codePostal");
                        String ville = request.getParameter("ville");
                        String pays = request.getParameter("pays");
                        String complement = request.getParameter("complement");

                        if (!nomReceptionnaire.isEmpty() && !prenomReceptionnaire.isEmpty() && !numVoie.isEmpty() && !typeVoie.isEmpty() && !nomVoie.isEmpty() && !codePostal.isEmpty() && !ville.isEmpty() && !pays.isEmpty()) {
                            gestionAdresse.ajoutAdresse(numClient, nomReceptionnaire, prenomReceptionnaire, numVoie, typeVoie, nomVoie, codePostal, ville, pays, complement);
                            section = "adresseClient";
                            adresseLivAjoute = true;
                            request.setAttribute("adresseLivAjoute", adresseLivAjoute);
                            if (request.getAttribute("infosAdresse") != null) {
                                request.removeAttribute("infosAdresse");
                            }

                        } else {//Adresse non valide
                            infosAdresse = true;
                            request.setAttribute("infosAdresse", infosAdresse);
                            section = "adresseClient";
                        }

                    } else if (session.getAttribute("adresseLivValidee") == null) {//Adresse deja existante Validé 
                        session.setAttribute("adresseLivraison", request.getParameter("adresse"));
                        adresseLivValidee = true;
                        session.setAttribute("adresseLivValidee", adresseLivValidee);
                    }
                } else {//Aucune adresse selectionnée ou ajoutée
                    section = "adresseClient";
                    infosAdresse = true;
                    request.setAttribute("infosAdresse", infosAdresse);
                }
                //////////////////////////////////ADRESSE FACTURATION//////////////////////////////////////////////////
                if (request.getParameter("adresseFacturation") != null && session.getAttribute("adresseFacValidee") == null) {
                    if (request.getParameter("adresseFacturation").equals("nouvelleAdresse") && session.getAttribute("adresseFacValidee") == null) {
                        int numClient = 1;
                        String nomReceptionnaire = request.getParameter("nomReceptionnaire");
                        String prenomReceptionnaire = request.getParameter("prenomReceptionnaire");
                        String numVoie = request.getParameter("numVoie");
                        String typeVoie = request.getParameter("typeVoie");
                        String nomVoie = request.getParameter("nomVoie");
                        String codePostal = request.getParameter("codePostal");
                        String ville = request.getParameter("ville");
                        String pays = request.getParameter("pays");
                        String complement = request.getParameter("complement");

                        if (!nomReceptionnaire.isEmpty() && !prenomReceptionnaire.isEmpty() && !numVoie.isEmpty() && !typeVoie.isEmpty() && !nomVoie.isEmpty() && !codePostal.isEmpty() && !ville.isEmpty() && !pays.isEmpty()) {
                            gestionAdresse.ajoutAdresse(numClient, nomReceptionnaire, prenomReceptionnaire, numVoie, typeVoie, nomVoie, codePostal, ville, pays, complement);
                            section = "adresseClient";
                            adresseFacAjoute = true;
                            session.setAttribute("adresseFacAjoute", adresseFacAjoute);
                            if (request.getAttribute("infosAdresseFacturation") != null) {
                                request.removeAttribute("infosAdresseFacturation");
                            }
                            //recuperer numero adresse livraison
                        } else if (session.getAttribute("adresseFacValidee") == null) {
                            infosAdresse = true;
                            request.setAttribute("infosAdresseFacturation", infosAdresseFacturation);
                            section = "adresseClient";
                        }
                    } else if (session.getAttribute("adresseFacValidee") == null) {//Adresse deja existante Validé 
                        session.setAttribute("adresseFacturation", request.getParameter("adresseFacturation"));
                        adresseFacValidee = true;
                        session.setAttribute("adresseFacValidee", adresseFacValidee);
                    }
                } else {//aucun boutton selectionné
                    section = "adresseClient";
                    infosAdresseFacturation = true;
                    request.setAttribute("infosAdresseFacturation", infosAdresseFacturation);
                }

                if (session.getAttribute("adresseFacValidee") != null && session.getAttribute("adresseLivValidee") != null) {
                    section = "choixFormuleLivraison";
                }
            } catch (SQLException ex) {
                out.println("Erreur Serveur");
            } catch (ClassNotFoundException ex) {
                out.println("Erreur");
            }
        }
        //-----------------------------------------------adresseClient-----------------------
        boolean adresseClientB = false;
        if ("adresseClient".equals(section)) {
            adresseClientB = true;
            request.setAttribute("adresseClientB", adresseClientB);
            try {
                List<String> clefs = gestionAdresse.getCleDefaut();
                List<Adresse> listeAdresse = gestionAdresse.listeAdresseClient(1);//numClient
                Adresse a = new Adresse();
                request.setAttribute("afficheAdresse", listeAdresse);
                request.setAttribute("clefListeAdresseClient", clefs);
                //request.setAttribute("listeAdresseClient", clefs);//listeAdresse
                pageJSP = "/WEB-INF/menus/menu-main.jsp";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //-----------------------------connexion----------------
        boolean connexionAff = false;
        if ("connexion".equals(section)) {
            connexionAff = true;
           request.setAttribute("connexionAff", connexionAff);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        //------------s'inscrire-------------------------
         boolean connexionInsc = false;
        if ("inscrire".equals(section)) {
            connexionInsc = true;
           request.setAttribute("connexionInsc", connexionInsc);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        

        if (getServletContext().getAttribute("gestionFormuleLivraison") == null) {
            try {
                getServletContext().setAttribute("gestionFormuleLivraison", new GestionFormuleLivraison());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        GestionFormuleLivraison gestionFormuleLivraison = (GestionFormuleLivraison) getServletContext().getAttribute("gestionFormuleLivraison");
        boolean affichageChoixFormuleLivraison = false;
        if ("choixFormuleLivraison".equals(section)) {
            if (request.getParameter("F1") == null) {
                affichageChoixFormuleLivraison = true;
                request.setAttribute("affichageChoixFormuleLivraison", affichageChoixFormuleLivraison);
                try {
                    List<FormuleDeLivraison> lFDL = gestionFormuleLivraison.listeFormuleLivraison();
                    request.setAttribute("listFormuleLivraison", lFDL);
                    pageJSP = "/WEB-INF/menus/menu-main.jsp";
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("F1" + request.getParameter("F1"));
                session.setAttribute("numFormuleLivraisonChoisie", request.getParameter("F1"));
                section = "panierValider";
            }

        }

        //RECHERCHE a voir (COMBOBOX theme a faire )
        if (request.getParameter("okRech") != null) {
            boolean affOuvrage = true;
            String recherche = request.getParameter("laRecherche");
            System.out.println(recherche);
            List<Ouvrage> lo = new ArrayList<>();
            OuvragesDAOGui ODAO = null;
            try {
                ODAO = new OuvragesDAOGui();
            } catch (NamingException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lo = ODAO.rechOuvrage(recherche);
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("liste", lo);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";

        }
        boolean affOuvrage = false;
        boolean ajoutPanier = false;
        if ("ajout-panier".equals(section)) {
            ajoutPanier = true;
            affOuvrage = true;
            request.setAttribute("ajoutPanier", ajoutPanier);
            request.setAttribute("ajoutPanier", affOuvrage);
            if (session.getAttribute("monPanier") == null) {//Si panier inexistant
                Panier p = new Panier();
                String titre = request.getParameter("titre");
                String ISBN = request.getParameter("isbn");
                Float prixHT = Float.valueOf(request.getParameter("prixHT"));
                Float TVA = Float.valueOf(request.getParameter("TVA"));
                Float tauxPromotion = Float.valueOf(request.getParameter("promotionOuvrage"));
                String disponibilite = request.getParameter("disponibilite");
                int quantiteDispo = Integer.valueOf(request.getParameter("quantiteDispo"));

                p.add(titre, ISBN, prixHT, TVA, tauxPromotion, disponibilite, quantiteDispo);

                session.setAttribute("monPanier", p);
            } else {

                Panier p = (Panier) session.getAttribute("monPanier");
                String titre = request.getParameter("titre");

                String ISBN = request.getParameter("isbn");
                Float prixHT = Float.valueOf(request.getParameter("prixHT"));
                Float TVA = Float.valueOf(request.getParameter("TVA"));
                Float tauxPromotion = Float.valueOf(request.getParameter("promotionOuvrage"));
                String disponibilite = request.getParameter("disponibilite");
                int quantiteDispo = Integer.valueOf(request.getParameter("quantiteDispo"));

                session.setAttribute("monPanier", p);
                p.add(titre, ISBN, prixHT, TVA, tauxPromotion, disponibilite, quantiteDispo);

            }

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        boolean affPanier = false;
        boolean addPanier = false;
        if ("add".equals(section)) {
            affPanier = true;
            addPanier = true;
            request.setAttribute("add", addPanier);

            Panier p = (Panier) session.getAttribute("monPanier");
            String titre = request.getParameter("titre");

            String ISBN = request.getParameter("isbn");
            Float prixHT = Float.valueOf(request.getParameter("prixHT"));
            Float TVA = Float.valueOf(request.getParameter("TVA"));
            Float tauxPromotion = Float.valueOf(request.getParameter("promotionOuvrage"));
            String disponibilite = request.getParameter("disponibilite");
            int quantiteDispo = Integer.valueOf(request.getParameter("quantiteDispo"));

            session.setAttribute("monPanier", p);
            p.add(titre, ISBN, prixHT, TVA, tauxPromotion, disponibilite, quantiteDispo);

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        boolean decPanier = false;
        if ("dec".equals(section)) {
            affPanier = true;
            decPanier = true;
            request.setAttribute("dec", decPanier);

            Panier p = (Panier) session.getAttribute("monPanier");
            String ISBN = request.getParameter("isbn");

            session.setAttribute("monPanier", p);
            p.dec(ISBN);

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        boolean delPanier = false;
        if ("del".equals(section)) {
            affPanier = true;
            delPanier = true;
            request.setAttribute("del", delPanier);

            Panier p = (Panier) session.getAttribute("monPanier");
            String ISBN = request.getParameter("isbn");

            session.setAttribute("monPanier", p);
            p.del(ISBN);

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        if ("afficher-ouvrage".equals(section) || affOuvrage) {
            affOuvrage = true;
            request.setAttribute("affOuvrage", affOuvrage);
            List<Ouvrage> lo = new ArrayList<>();
            OuvragesDAOGui ODAO = null;
            try {
                ODAO = new OuvragesDAOGui();
            } catch (NamingException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lo = ODAO.selectAllOuvrages();
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("liste", lo);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        boolean affUnOuvrage = false;
        if ("afficher-UnOuvrage".equals(section)) {
            affUnOuvrage = true;
            request.setAttribute("affUnOuvrage", affUnOuvrage);
            OuvragesDAOGui ODAO = null;
            Ouvrage o = null;
            try {
                ODAO = new OuvragesDAOGui();
            } catch (NamingException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                o = (Ouvrage) ODAO.selectOuvrageByISBN(request.getParameter("isbn"));

            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ouvrage", o);

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        if ("viderPanier".equals(section)) {
            Panier p = (Panier) session.getAttribute("monPanier");
            System.out.println(session.getAttribute("monPanier"));
            p.clear();
            request.setAttribute("monPanier", p);
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }

        if (getServletContext().getAttribute("gestionPanier") == null) {
            getServletContext().setAttribute("gestionPanier", new Panier());
        }
        Panier gestionPanier = (Panier) getServletContext().getAttribute("gestionPanier");

        if ("affichagePanier".equals(section) || affPanier) {
            affPanier = true;
            request.setAttribute("affPanier", affPanier);

            List<String> clefs = gestionPanier.getCleDefaut();
            Collection<LigneDeCommande> listePanier = gestionPanier.list();
            request.setAttribute("clefListePanier", clefs);
            request.setAttribute("affichePanier", listePanier);

            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        boolean panierValider = false;
        if ("panierValider".equals(section)) {
            try {

                panierValider = true;
                request.setAttribute("panierValider", panierValider);

                //Panier
                List<String> clefs = gestionPanier.getCleDefaut();
                Collection<LigneDeCommande> listePanier = gestionPanier.list();

                request.setAttribute("clefListePanier", clefs);
                request.setAttribute("affichePanier", listePanier);

                //Adresse
                List<Adresse> listeAdresse = gestionAdresse.listeAdresseClient(1);//numClient
                request.setAttribute("afficheAdresse", listeAdresse);
                pageJSP = "/WEB-INF/menus/menu-main.jsp";

                //Formule livraison
                List<FormuleDeLivraison> lFDL = gestionFormuleLivraison.listeFormuleLivraison();
                request.setAttribute("listFormuleLivraison", lFDL);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pageJSP = response.encodeURL(pageJSP);
        getServletContext().getRequestDispatcher(pageJSP).include(request, response);
        //Pas d'affichage
        //Creer les URL 
        //Creer les Sessions
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (NamingException ex) {
            Logger.getLogger(controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (NamingException ex) {
            Logger.getLogger(controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
