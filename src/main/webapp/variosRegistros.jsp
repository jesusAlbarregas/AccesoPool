<%-- 
    Document   : unRegistro
    Created on : 26-oct-2017, 16:28:15
    Author     : Jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="es.albarregas.beans.Ave, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
    </head>
    <%
        ArrayList<Ave> aves = (ArrayList<Ave>) request.getAttribute("aves");
    %>
    <body>
        <h1>Datos de las aves</h1>
        <table>

            <%
                if (!aves.isEmpty()) {
            %>
            <tr>
                <th>Anilla</th>
                <th>Especie</th>
                <th>Lugar</th>
                <th>Fecha</th>
            </tr>
            <%
                for (Ave ave : aves) {
            %>
            <tr>
                <td><%=ave.getAnilla()%></td>
                <td><%=ave.getEspecie()%></td>
                <td><%=ave.getLugar()%></td>
                <td><%=ave.getFecha()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">No existen aves que visualizar</td>
            </tr>
            <%
                }
            %>
        </table>

        <p><a href="<%=request.getContextPath()%>">Volver</a></p>
    </body>
</html>
