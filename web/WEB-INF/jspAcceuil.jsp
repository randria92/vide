<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="/LibrairyAFPA_GFCE/CSS/monCSS.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav>
            <c:url value="controller?section=menu-main" var="url01" />
            <c:import url="${url01}" />
        </nav>
        
        
        
    </body>
</html>
