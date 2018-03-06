
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/LibrairyAFPA_GFCE/JS/afficher_cacher_div.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Choix Adresse</title>

<body>
    <form method="POST" action="controller?section=ajoutAdresse"  > 
        <label for="adresses"><h3>Adresses de Livraison</h3></label>
        <table>
            <tbody> <!-- Corps du tableau -->

                <c:forEach items="${afficheAdresse}" var="c" > 
                    <tr>
                        <td><input type="radio" name="adresse" value="${c.numAdresse}"</td>
                        <td>${c}</td>
                    </tr> 
                </c:forEach>
                <tr></tr>
                <tr></tr>
                <tr><td><hr></td><td><hr></td></tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td><input type="radio" onclick="afficher_cacher('dvLiv')" name="adresse" value="nouvelleAdresse" /></td>
                    <td><label for="nouvelleAdresses">Nouvelle Adresse de Livraison</label></td>
                </tr>

            </tbody>
        </table>
        <br>
        <div id="dvLiv" class="dvLiv" >
            <table>    
                <tbody>
                    <tr><td>             </td><td>Nom Réceptionnaire</td><td><input type="text" name="nomReceptionnaire" value="" /></td></tr>
                    <tr><td>             </td><td> Prenom Réceptionnaire</td><td><input type="text" name="prenomReceptionnaire" value="" /></td></tr>
                    <tr><td>             </td><td>N° voie</td><td><input type="text" name="numVoie" value="" /></td></tr>
                    <tr><td>             </td><td>Type voie</td><td><input type="text" name="typeVoie" value="" /></td></tr>
                    <tr><td>             </td><td>Nom voie</td><td><input type="text" name="nomVoie" value="" /></td></tr>
                    <tr><td>             </td><td>Code Postal</td><td><input type="text" name="codePostal" value="" /></td></tr>
                    <tr><td>             </td><td>Ville</td><td><input type="text" name="ville" value="" /></td></tr>
                    <tr><td>             </td><td>Pays</td><td><input type="text" name="pays" value="" /></td></tr>
                    <tr><td>             </td><td>Compléments</td><td><textarea name="complement" rows="4" cols="20"> </textarea></td></tr>

                </tbody>
            </table>
        </div>
        <script type="text/javascript">
                afficher_cacher('dvLiv');
            </script>
        <c:if test="${!adresseLivValidee}" ><!--Adresse facturation validée-->
            <input  type="submit" value="Valider adresse de livraison"  />
            <c:if test="${infosAdresse}" >
                <h3 style="color: red">Informations Adresses Incomplete</h3>
            </c:if>
            <c:if test="${adresseLivAjoute}" >
                <h3 style="color: green">Adresse Ajoutée</h3>
            </c:if>
        </c:if>
        <c:if test="${adresseLivValidee}" ><!--Adresse facturation validée-->
            <img class="imgValidee" src="/LibrairyAFPA_GFCE/image/valide.png">
            <c:forEach items="${afficheAdresse}" var="c" > 
                <c:if test="${c.numAdresse eq adresseLivraison}">
                    ${c}
                </c:if>
            </c:forEach>
            <a href="controller?section=reinitialiserAdresseLiv"> <img class="imgModifier" src="/LibrairyAFPA_GFCE/image/modifier.png"> </a>
            </c:if>
    </form>

</body>

