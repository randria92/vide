<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:url value="controller?section=menu-main" var="url01" />
        <h1>Connexion Client</h1>
        
        <a href="controller?section=pageSuivante"  >Valider</a>
    </body>

