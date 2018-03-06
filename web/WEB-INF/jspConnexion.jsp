<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="tableCommandes">
    <table style="border-bottom: solid;border-top: solid; border-collapse: separate;
           border-spacing: 2px; border-color: gray">
        
         <div id="dvCon" class="dvCon" >
             <form method="POST" action="controller?section=connexion"> 
                     
                        <tbody><h1>Bienvenue dans votre espace</h1> 
                            <tr><td>             </td><td>Login</td><td><input type="text" name="nomClient" value="" /></td></tr>
                            <tr><td>             </td><td>Mot de passe</td><td><input type="password" name="motDePasseClient" value="" /></td></tr>
                            <tr><td>              </td><td><input type="submit" value="ok"></td></tr>
                        </tbody>
                        
                            <tr><td></td><td></td><td></td></tr>
                            <tr><td></td><td></td><td></td></tr>
                            <tr><td>Nouveau Client inscrivez-vous</td><td><a action href="controller?section=inscrire">créer un compte</a></td><td></td></tr>
                       
                    </table>
             </form> 
            
                </div>

