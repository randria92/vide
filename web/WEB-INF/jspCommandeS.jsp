<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        
    <c:forEach items="${listCommande}" var="c" >
        
        <h1>${c.toString()}</h1>
        
        
    </c:forEach>
        <h1>là</h1>
        <h1>Hello World!</h1>
    </body>
</html>
