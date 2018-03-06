
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Commande</title>
    </head>
    <body>
        <c:url value="controller?section=menu-main" var="url01" />
        <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
               border-spacing: 2px; border-color: gray" >
            <caption>Client </caption> <!--Titre du Tableau-->
            <thead> <!-- En-tête du tableau -->
                <tr>
                    <c:forEach items="${clefsListLigneCommandeCommande}" var="c" >
                        <th>${c}</th>
                        </c:forEach>
                </tr>
            </thead>

            <tbody> <!-- Corps du tableau -->
                <c:forEach items="${listLigneCommande}" var="c" > 
                    <tr >    

                        <td style="text-align: center;">${c.ISBN}</td>
                        <td style="text-align: center;">${c.quantiteOuvrage}</td>
                        <td style="text-align: center;">${c.tauxPromotion}</td>
                        <td style="text-align: center;">${c.prixTTCUneLigne(c.numCommande,c.numLigneDeCommande)}</td>
                        <td style="text-align: center;">${c.economiRealise(c.numCommande,c.numLigneDeCommande)}</td>

                        <c:if test="${valide}"> 
                            <c:if test="">
                                
                            </c:if>
                                
                            <td> <a href = "controller?section=ajoutAvis&ISBNAvis=${c.ISBN}&numLigneComAvis=${c.numLigneDeCommande}" >Laisser un Avis</a></td>
                        </c:if>
                        
                    </tr> 
                </c:forEach>
                    
            </tbody>
        </table>
        
         <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
               border-spacing: 2px; border-color: gray">
            <caption>Facture </caption> <!--Titre du Tableau-->
            <thead> <!-- En-tête du tableau -->
                <tr>
                    <c:forEach items="${clefsListFacture}" var="c" >
                        <th>${c}</th>
                        </c:forEach>
                </tr>
            </thead>

            <tbody> <!-- Corps du tableau -->
                <c:forEach items="${listFacture}" var="x" > 
                    <tr >   
                        <td style="text-align: center ; padding-left:42px ;padding-right: 42px   ">${x.numFacture}</td>
                        <c:forEach items="${afficheAdresse}" var="c" > 
                        <c:if test="${x.numAdresseFact eq c.numAdresse}"> 
                            <td style="text-align: center ; padding-left:42px ; padding-right: 42px   ">${c}</td>
                        </c:if>
                    </c:forEach>

                <td style="text-align: center ; padding-left:42px; padding-right: 42px  ">${x.getDateFacturation(x.getDateFacturation())}</td>
                <td style="text-align: center ; padding-left:42px; padding-right: 42px   ">${x.getStatusFacture(x.getNumStatusFact())}</td>

            </tr> 
        </c:forEach>
    </tbody>
</table>
        <a href="controller?section=listCommande"><img class="imgPrevious" src="/LibrairyAFPA_GFCE/image/previous.png"></a>
    </body>
</html>
