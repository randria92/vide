<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul class="menu">
    <li>
        <c:url value="controller" var="url01" />
        <a href="${url01}">home</a> |
    </li>
    <li>
        <c:url value="controller?section=listCommande" var="url02" />
        <a href="${url02}">Commande</a>
    </li>
</ul>
