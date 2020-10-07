<%-- 
    Document   : modificarImagen
    Created on : 27/09/2020, 20:46:46
    Author     : marcu
--%>

<%@page import="java.io.File"%>
<%@page import="pr2.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="modificarImagen" method="POST">
        <h1>MODIFICAR IMAGEN</h1>
    </form> 
</html>

<%
    session = request.getSession();
    Object usuarioObj = session.getAttribute("username");
    Object passwordObj = session.getAttribute("password");
    DB db = new DB();
    if (usuarioObj == null || passwordObj == null) out.println("ERROR");//ERROR
    if (!db.usuarioExists(usuarioObj.toString(), passwordObj.toString())) out.println("ERROR");//ERROR
    db.getImage(session.getAttribute("imageTitle").toString());    
%>
