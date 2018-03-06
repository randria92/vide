<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="/LibrairyAFPA_GFCE/CSS/monCSS.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<div class="tableCommandes">
    <c:url value="controller?section=menu-main" var="url01" />
    <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
           border-spacing: 2px; border-color: gray">
        <caption><h2>Clients:</h2></caption> <!--Titre du Tableau-->
        <thead style="border-style: solid"> <!-- En-te?te du tableau -->
            <tr>
                <c:forEach items="${clefsListCommande}" var="c" >
                    <th>${c}</th>
                    </c:forEach>
            </tr>
        </thead>

        <tbody  > <!-- Corps du tableau -->

            <c:forEach items="${listCommande}" var="c" > 
                <c:url value="controller?section=detailCommande&statusCommande=${c.getStatusCommande()}&numCommande=${c.getNumCommande()}" var="varDetailCommande" />
                <tr class="commande" onclick="document.location = '${varDetailCommande}'" >    

                    <td style="text-align: center;">${c.getNumCommande()}</td>
                    <td style="text-align: center;">${c.getStatusCommande(c.getStatusCommande())}</td>
                    <td style="text-align: center;">${c.quantiteTotalExpedieCommande(c.getNumCommande(),c.getNumClient()) }</td>
                    <td style="text-align: center;">${c.totalCommande(c.getNumCommande())}</td>
                    <td style="text-align: center;">${c.getDateCommande(c.getDateCommande())}</td>
                    
                </tr> 
                
            </c:forEach>
        </tbody>
    </table>
</div>



