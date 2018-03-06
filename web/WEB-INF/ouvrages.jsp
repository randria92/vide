<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ouvrages</title>
    <link href="/LibrairyAFPA_GFCE/CSS/cssGui.css" rel="stylesheet" type="text/css"/>
</head>
<body bgcolor="FFFFFF">
    <nav>

    </nav>
    <hr />

    <!-- Options de recherche -->
<center>
    <form class="recherche" action="controller">
        Recherche : <input type="text" name="laRecherche" value="titre"/>
        <input type="submit" value="Ok" name="okRech" />
    </form>
</center>
<br>

<!--Liste des ouvrages-->
<c:forEach items="${liste}" var="p">
    <a href="controller?section=afficher-UnOuvrage&isbn=${p.ISBN}" ></a>
    <div class="carre"><center>
            <img src="/LibrairyAFPA_GFCE/images/livre.png"></center>
        <center>${p.ISBN}</center><br>
        <center><b>${p.titre}</b></center><br>
        ${p.prixHT}€<br>

        <center>
            <a href="controller?section=ajout-panier&isbn=${p.ISBN}&titre=${p.titre}&prixHT=${p.prixHT}&TVA=${p.TVAOuvrage}&promotionOuvrage=${p.promotionOuvrage}&disponibilite=${p.disponibilite}&quantiteDispo=${p.quantiteDispo}" >
                <c:if test="${p.disponibilite eq 'Disponible'}">
                    <input type="submit" value="Ajouter Panier" name="ajoutPanier" /> 
                </c:if>
                    </a>
                <c:if test="${p.disponibilite eq 'Indisponible'}">
                    <p>Indisponible</p>
                </c:if>
            
        </center>
    </div>
</c:forEach>

