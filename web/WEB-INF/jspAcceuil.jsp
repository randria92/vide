<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <c:url value="controller?section=menu-main" var="url01" />
            <c:import url="${url01}" />
        </nav>
        <h1>Hello World33333333333!</h1>
        
        <hr />
    </body>
</html>
