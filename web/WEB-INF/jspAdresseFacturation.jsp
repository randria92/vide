<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="/LibrairyAFPA_GFCE/JS/afficher_cacher_div.js"></script>

    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choix Adresse</title>
    
    <body>
        <form method="POST" action="controller?section=ajoutAdresse"> 

            <table>
                <label for="adresses"><h3>Adresses de Facturation</h3></label>
                <tbody> <!-- Corps du tableau -->

                    <c:forEach items="${afficheAdresse}" var="c" > 
                        <tr>
                            <td><input type="radio" name="adresseFacturation" value="${c.numAdresse}"</td>
                            <td>${c}</td>
                        </tr> 
                    </c:forEach>
                    <tr></tr>
                    <tr></tr>
                    <tr><td><hr></td><td><hr></td></tr>
                    <tr></tr>
                    <tr></tr>
                    <tr>
                        <td> <input type="radio" onclick="afficher_cacher('dvFac')" name="adresseFacturation" value="nouvelleAdresse" /> </td>
                        <td><label for="nouvelleAdresses">Nouvelle Adresse de Facturation</label></td>
                </tr>

                </tbody>
            </table>
            <br>
                <div id="dvFac" class="dvFac" >
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
                afficher_cacher('dvFac');
            </script>
            <c:if test="${!adresseFacValidee}" ><!--Adresse facturation validée-->
                <input  type="submit" value="Valider adresse de facturation"  />
                <c:if test="${infosAdresseFacturation}" >
                    <h3 style="color: red">Informations Adresses Incomplete</h3>
                </c:if>
                <c:if test="${adresseFacAjoute}" >
                    <h3 style="color: green">Adresse Ajoutée</h3>
                </c:if>
            </c:if>
            <c:if test="${adresseFacValidee}" ><!--Adresse facturation validée-->
                <img class="imgValidee" src="/LibrairyAFPA_GFCE/image/valide.png">
                <c:forEach items="${afficheAdresse}" var="c" > 
                    <c:if test="${c.numAdresse eq adresseFacturation}">
                        ${c}
                    </c:if>
                </c:forEach>
                <a href="controller?section=reinitialiserAdresseFact"> <img class="imgModifier" src="/LibrairyAFPA_GFCE/image/modifier.png"> </a>
                </c:if>
        </form>
        <form action="controller?section=ajoutAdresse" method="POST">
            <input class="boutonAdresseLF"  type="submit" value="Validations des adresses de Livraison et Facturation"  />
        </form>

    </body>

