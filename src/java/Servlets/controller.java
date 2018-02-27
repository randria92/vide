/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Commande;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import traitement.GestionCommande;

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
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String pageJSP = "/WEB-INF/jspAcceuil.jsp";
        String section = request.getParameter("section");
        
        HttpSession session = request.getSession();

        if (getServletContext().getAttribute("gestionCommande") == null) {
            try {
                getServletContext().setAttribute("gestionCommande", new GestionCommande());
            } catch (NamingException ex) {
                ex.printStackTrace();
                //to do
            }
        }
        GestionCommande gestionCommande = (GestionCommande) getServletContext().getAttribute("gestionCommande");

        if("menu-main".equals(section)){
            pageJSP = "/WEB-INF/menus/menu-main.jsp";
        }
        
        if ("listCommande".equals(section)) {
            try {
                //List<String> clefs = gestionCommande.getCleDefaut();
                List<String> lcom = gestionCommande.listCommande(5);
                request.setAttribute("listCommande", lcom);
                pageJSP = "/WEB-INF/jspCommandeS.jsp";
            } catch (SQLException ex) {
                ex.printStackTrace();
                //to do
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
