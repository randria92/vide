<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link href="/LibrairyAFPA_GFCE/CSS/monCSS.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <c:url value="controller?section=menu-main" var="url01" />
    <form method="POST" action="">
    <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
           border-spacing: 2px; border-color: gray">
        <caption><h2>PANIER</h2></caption> <!--Titre du Tableau-->
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
        </form>
<div id ="boutonPanierConteneur">
    <form method="POST"  action="controller?section=connexionClient&pageSuivante=adresseClient">
        <input class="boutonPanier" type="submit" value="Valider Panier" name="validerPanier" /> 
    </form>

    <form method="POST" action="controller?section=afficher-ouvrage">
        <input class="boutonPanier" type="submit" value="Continuer mes achats" name="continuerAchat" /> 
    </form>
</div>
</body>

