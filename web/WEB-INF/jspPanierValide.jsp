<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/LibrairyAFPA_GFCE/CSS/monCSS.css" rel="stylesheet" type="text/css"/>
        <title>Panier Validation</title>
    </head>
    <body>

        <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
               border-spacing: 2px; border-color: gray">
            <caption><h2>VALIDATION PANIER</h2></caption> <!--Titre du Tableau-->
            <br>
            <thead> <!-- En-tête du tableau -->
                <tr>
                    <c:forEach items="${clefListePanier}" var="c" >
                        <th>${c}</th>
                        </c:forEach>
                </tr>
            </thead>

            <tbody> <!-- Corps du tableau -->
                <tr >    
                    <c:forEach var="c" items="${monPanier.list()}">
                        <td style="text-align: center; ">${c.titre}</td>
                        <td style="text-align: center;">${c.quantiteOuvrage}</td>
                        <td style="text-align: center;">${c.promotionOuvrage}</td>
                        <td style="text-align: center;">${monPanier.prixTotalUneligne(c)}</td>
                        <td style="text-align: center;">${monPanier.economieUneLigne(c)}</td>

                        <td><a href='controller?section=add&isbn=${c.ISBN}&titre=${c.titre}&prixHT=${c.prixHT}&TVA=${c.TVAOuvrage}&promotionOuvrage=${c.promotionOuvrage}&disponibilite=${c.disponibilite}&quantiteDispo=${c.quantiteDispo}'>+</a></td>
                        <td><a href='controller?section=dec&isbn=${c.ISBN}'>-</a></td>
                        <td><a href='controller?section=del&isbn=${c.ISBN}'>Supprimer</a></td>
                    </tr> 
                </c:forEach>
            </tbody>
        </table>
        <h4>Recap Commande:</h4>
        <hr>
        <br>
        <c:forEach items="${afficheAdresse}" var="c" > 
            <c:if test="${c.numAdresse eq adresseLivraison}">
                Adresse de Livraison : ${c}
            </c:if>
        </c:forEach>
        <br>
        <c:forEach items="${afficheAdresse}" var="c" > 
            <c:if test="${c.numAdresse eq adresseFacturation}">
                Adresse de Facturation:${c}
            </c:if>
        </c:forEach>
        <br>
        <c:forEach var="c" items="${listFormuleLivraison}">
            <c:if test="${c.numFormuleLivraison eq numFormuleLivraisonChoisie}">
                <br>
                Formule de Livraison : ${c.getPrixFormuleDeLivraison()} €
                <br>
                <p style="padding-left: 600px">Total Commande : ${monPanier.prixTotalCommande(c.getPrixFormuleDeLivraison())} €</p>
            </c:if>
        </c:forEach>
                <br>
                <br>
                <a href="controller?section=commandeValidee" class="boutonPaiement" style="margin-left: 600px">Paiement Commande</a>
               
    </body>
</html>
