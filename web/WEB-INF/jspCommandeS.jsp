<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>



        <h1>${c}</h1>


   



    <table>
        <caption>Client </caption> <!--Titre du Tableau-->
        <thead> <!-- En-tête du tableau -->
            <tr>
                <c:forEach items="${clefsListCommande}" var="c" >
                    <th>${c}</th>
                    </c:forEach>
            </tr>
        </thead>
        
            <tbody> <!-- Corps du tableau -->
                <c:forEach items="${listCommande}" var="c" > 
                <tr>
                    <td>${c.getNumCommande()}</td>
                    <td>${c.getStatusCommande(c.getStatusCommande())}</td>
                    <td>${c.quantiteTotalExpedieCommande(c.getNumCommande(),c.getNumClient()) }</td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
        <h1>là</h1>
        <h1>Hello World!</h1>
    </body>
</html>
