<%-- 
    Document   : list
    Created on : 27/09/2020, 20:47:50
    Author     : marcu
--%>


<%@page import="java.util.List"%>
<%@page import="pr2.list"%>
<%@page import="pr2.DB"%>
<%@page import="pr2.imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action=\"list\" method=\"POST\">
        <h1>LIST</h1>
        <br><br>
    </form>
</html>




<%
    session = request.getSession();
    Object usuarioObj = session.getAttribute("username");
    Object passwordObj = session.getAttribute("password");
    DB db = new DB();
    
    List<imagen> l;

    if (usuarioObj == null || passwordObj == null) out.println("ERROR"); //ERROR
    if (!db.usuarioExists(usuarioObj.toString(), passwordObj.toString())) out.println("ERROR"); //ERROR
    

    List<imagen> images = db.getAllImagenes();
    for(imagen category : images) {
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
        if(category.getAuthor().equals(usuarioObj.toString())){            
            out.print("<button type=\"submit\" value=\""+category.getTitle()+"\" name=\"list\">Modificar</button>");
        }
        out.println("<br><br>");
    }

%>
