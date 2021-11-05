<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aves</title>
    </head>
    <body>
        <h1>Página de avistamientos</h1>
        <form action="acceso" method="post">
        <table>
            <tr>
                <td>Introduce una anilla</td>
                <td colspan="2"><input name="anilla" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Anilla" name="una"/></td>
                <td><input type="submit" value="Todas" name="todas"/></td>
                <td><input type="submit" value="Algunas" name="rand"/></td>
            </tr>
        </table>
        </form>
    </body>
</html>
