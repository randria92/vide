<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formule de Livraison </title>
    </head>
    <body>
        <form method="POST" action="controller?section=choixFormuleLivraison">
            <table style="margin-left: 50px;">
                
                <c:forEach var="c" items="${listFormuleLivraison}">
                    <tr >
                        <td> ${c.getNomTransporteur()}</td>
                        <td style="padding-left:30px">${c.getPrixFormuleDeLivraison()} €</td>
                        <td style="padding-left:30px"><input type="radio" name="F1" value="${c.numFormuleLivraison}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <input style="margin-left: 77px;" type="submit" value="Valider"  />
       </form>
    </body>
</html>
