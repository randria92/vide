<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url value="controller?section=menu-main" var="url01" />
        <title> Avis et Commentaire </title>
    </head>
    <body>
        <h1>Votre Avis !</h1>
        <form method="post" action="controller?section=ajoutAvis&ISBNAvis=${ISBNAvis}&numLigneComAvis=${numLigneComAvis}">


            Cochez une note :<br><br>
            <label for="TresSatisfait">Tres Satisfait</label>
            <input type="radio" name="note" value="TresSatisfait"  id="TresSatisfait" checked />
            <label for="Satisfait">Satisfait</label>
            <input type="radio" name="note" value="Satisfait"  id="Satisfait" /> 
            <label for="PasMal">Pas mal</label>
            <input type="radio" name="note" value="PasMal"  id="PasMal" />
            <label for="Moyen">Moyen</label>
            <input type="radio" name="note" value="Moyen"  id="Moyen" /> 
            <label for="Nul">Nul</label>
            <input type="radio" name="note" value="Nul" id="Nul" />                
            <br>


            <textarea name="commentaire" rows="20" cols="50"> 
            </textarea><br>

            <input type ="reset" value ="Restaurer"/>  
            <input type ="submit" value ="Envoyer" name="Envoyer"/>
            bool : ${validationAjoutAvis}
            <c:if test="${validationAjoutAvis}">
                <p> Avis ajouté <p>
                    Note : ${objetAvis.noteAvis}<br>
                    Commentaire : <c:if test="${empty objetAvis.commentaireAvis}"> Vide </c:if> <c:if test="${!empty objetAvis.commentaireAvis}"> ${objetAvis.commentaireAvis} </c:if>
                    
                </c:if>        
        </form>    

    </body>
</html>
