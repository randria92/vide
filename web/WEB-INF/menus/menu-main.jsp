<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link href="/LibrairyAFPA_GFCE/CSS/monCSS.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
    </head>
    <body>
        <header>

        </header>

        <nav id="sectionFixeHaut">
            <ul >
                <li>
                    <p style="color: white"  >LIBRAIRIE EN LIGNE</p>
                </li>
                <li >
                    <c:url value="controller" var="url01" />
                    <a href="${url01}" >HOME</a> 
                </li>
                <li>
                    <form action="">
                        <a style="color: white"> Recherche : <input type="text" value="titre"/>
                            <input type="submit" value="Ok" name="okRech" /></a>
                    </form>
                </li>
                <li class="bordereau">
                    <c:url value="controller?section=listCommande" var="url02" />
                    <a href="${url02}">MES COMMANDES</a>
                </li>
                <li >
                    <c:url value="controller?section=connexion" var="url02" />
                    <a href="${url02}">CONNEXION</a>
                </li>
                <li >
                    <c:url value="controller" var="url02" />
                    <a href="${url02}">VOTRE COMPTE</a>
                </li>
                <img src="/LibrairyAFPA_GFCE/image/iconeLivre.jpg" alt="" style="height: 40px;width: 45px;  margin-left: 20px"/>



            </ul>
        </nav>

        <hr />
        <div id="conteneur">
            <div id="sectionFixeGauche" class="section">
                <aside>
                    <a style="padding-bottom: 70px; padding-top: 20px" >
                        <jsp:useBean class="Beans.Panier" scope="session" id="monPanier" />
                        <h3>Mon Panier</h3>
                        <c:if test="${monPanier.isEmpty()}" >
                            Panier Vide !
                        </c:if>

                        <c:if test="${!monPanier.isEmpty()}" >

                            <p style="padding-top: 5px; text-align: justify"> Nombres d'articles: ${monPanier.nomBreDarticleTotal()} </p>
                            <p style="text-align: justify">Total Commande: ${monPanier.prixTotal()}€</p>
                            <br>
                                <a href="controller?section=affichagePanier"><p style="background: darkgray; padding-top: 35px;padding-bottom: 35px"> Voir Panier </p> </a>
                            </c:if>
                    </a>
                    <a>
                        <a href="controller?section=viderPanier">  <p  style="background: darkgray; padding-top: 25px;padding-bottom: 25px"> Vider Panier </p></a>
                    </a>
                    <c:url value="controller?section=afficher-ouvrage" var="url04" />
                    <a href="${url04}" style="padding-bottom: 200px; padding-top: 10px "> Catalogue </a>
                </aside>
            </div>
            <div class="section">
                <!--BLOCK COMMUN</a>-->

                <c:if test="${connexionAff}" >
                    <jsp:include page="../jspConnexion.jsp"/>
                </c:if>
                <c:if test="${connexionInsc}" >
                    <jsp:include page="../jspInscription.jsp"/>
                </c:if>
                <c:if test="${listCommandeB}" >
                    <jsp:include page="../jspCommandeS.jsp"/>
                </c:if>

                <c:if test="${affCommande}" >
                    <jsp:include page="../jspDetailCommande.jsp"/>
                </c:if>

                <c:if test="${adresseClientB}" >
                    <jsp:include page="../jspAdresse.jsp"/>
                </c:if>

                <c:if test="${affichageChoixFormuleLivraison}" >
                    <h2>Formule De Livraison</h2>
                    <jsp:include page="../jspFormuleLivraison.jsp"/>
                </c:if>

                <c:if test="${affOuvrage}" >
                    <jsp:include page="../ouvrages.jsp"/>
                </c:if>

                <c:if test="${affUnOuvrage}" >
                    <jsp:include page="../resumeOuvrage.jsp"/>
                </c:if>

                <c:if test="${affPanier}" >
                    <jsp:include page="../jspPanier.jsp"/>
                </c:if>

                <c:if test="${connexionClient}" >
                    <jsp:include page="../jspConnexionClient.jsp"/>
                </c:if>
                <c:if test="${panierValider}" >
                    <jsp:include page="../jspPanierValide.jsp"/>
                </c:if>

                <c:if test="${affFact}" >
                    <jsp:include page="../jspDetailFacturation.jsp"/>
                </c:if>
                    
                    <c:if test="${ajoutAvis}" >
                    <jsp:include page="../insertionAvis.jsp"/>
                </c:if>
            </div>

            <div class="section">
                <c:if test="${adresseClientB}" >
                    <jsp:include page="../jspAdresseFacturation.jsp"/>
                </c:if>
            </div>
        </div>
        <footer id="sectionFixeBas">
            <p>Createur : Guillaume/Francis/Cedric/Eva</p>
        </footer>
    </body>
</html>