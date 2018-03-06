<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--div class="tableCommandes"-->
    <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
           border-spacing: 2px; border-color: gray">
        <h1>Bienvenue dans votre espace veuillez-vous inscrire</h1>
         <!--div id="dvInsc" class="dvInsc" -->
             <form method="POST" action="controller?section=inscrire"> 
                 <tbody><tr><td>Nom</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>Prenom</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>motDePasseClient</td><td><input type="password" name="" value="" /></td></tr>
                 <tr><td>numVoieAdresse</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>typeVoieAdresse</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>nomVoieAdresse</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>codePostalAdresse</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>villeAdresse</td><td><input type="text" name="" value="" /></td></tr>
                 <tr><td>paysAdresse</td><td><input type="text" name="" value="" /></td></tr>
                     <tr><td><input type="submit" name="" value="envoyer" /></td></tr></tbody>
             </form>
         </div>
    <body>
        
    </body>
</html>
