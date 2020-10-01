<%-- 
    Document   : list
    Created on : 27/09/2020, 20:47:50
    Author     : marcu
--%>


<%@page import="pr2.imagen"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="modificar" method="POST">
        <h1>LIST</h1>
        <br><br>
    </form> 
</html>


<%
   List<imagen> list = (List<imagen>) request.getAttribute("imagenes"); 
   String user = (String) request.getAttribute("username");
    for(imagen category : list) {
        out.print(category.getTitle());
        out.print("        ");
        out.print(category.getDescription());
        out.print("        ");
        out.print(category.getAuthor());
        out.print("        ");
        out.print(category.getStorageDate());
        out.print("        ");
        out.print(category.getFilename());
        out.print("        ");
        if(category.getAuthor().equals(user)){
            out.print("<a href=\"modificarImagen\">MODIFICAR</a>");
        }
        out.println("<br><br>");
    }
%>
