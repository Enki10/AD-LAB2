<%-- 
    Document   : registrarImagen
    Created on : 27/09/2020, 19:18:42
    Author     : marcu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <form action="registrarImagen" method="POST"  enctype="multipart/form-data">
            <br>
            <h1>REGISTRE IMATGE</h1>
            <br><br>
            <label>Imatge: </label>
            <input type="file" name="imatge">
            <br><br>
            <label>Titol: </label>
            <input type="titol" name="titol">
            <br><br>
            <label>Descripció: </label>
            <input type="descripcio" name="descripcio">
            <br><br>
            <label>Paraules clau: </label>
            <input type="pclau" name="pclau">
            <br>     
            <br>     
            <input type="submit" value="Registar Imatge" name="registrarImagen">
        </form> 
    </body>
</html>
