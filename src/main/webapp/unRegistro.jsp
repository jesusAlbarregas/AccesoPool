<%-- 
    Document   : unRegistro
    Created on : 26-oct-2017, 16:28:15
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="es.albarregas.beans.Ave" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos</title>
    </head>
    <%
        Ave ave = (Ave)request.getAttribute("una");
    %>
    <body>
        <h1>Datos del ave</h1>
        <ul>
            <li>Anilla: <%= ave.getAnilla() %></li>
            <li>Especie: <%= ave.getEspecie() %></li>
            <li>Lugar: <%= ave.getLugar() %></li>
            <li>Fecha: <%= ave.getFecha() %></li>
        </ul>
        <p><a href="<%=request.getContextPath()%>">Volver</a></p>
    </body>
</html>
