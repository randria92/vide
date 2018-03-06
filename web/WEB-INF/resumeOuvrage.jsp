<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resume Ouvrage</title>
        <link href="/librairieFrontEnd(Gui)/css/moncss.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <hr />
    <center><h1>Resume</h1>
        Titre : ${ouvrage.titre}<br>
        Sous titre : ${ouvrage.sousTitre}<br>
        Resume : ${ouvrage.resume}<br>
        Disponibilite : ${ouvrage.disponibilite}<br>
        Quantité dispo : ${ouvrage.quantiteDispo}<br>
        Prix HT : ${ouvrage.prixHT}€<br>
        TVA : ${ouvrage.TVA}%<br>
        Date parution: ${ouvrage.parution}<br>
        PromotionEn Cours: ${ouvrage.promotionOuvrage}<br>

    </center></body>

