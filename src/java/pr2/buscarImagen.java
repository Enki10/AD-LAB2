package pr2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pr2.DB;
import pr2.imagen;

/**
 *
 * @author marcu
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            session.setAttribute("errorReturnPath","buscarImagen.java");
            
            String imageTitle = request.getParameter("imageTitle");
            Object usuarioObj = session.getAttribute("username");
            Object passwordObj = session.getAttribute("password");
            
            DB db = new DB();
            
            if (usuarioObj == null || passwordObj == null) request.getRequestDispatcher("error.jsp").forward(request, response); //ERROR
            if (!db.usuarioExists(usuarioObj.toString(), passwordObj.toString())) request.getRequestDispatcher("error.jsp").forward(request, response); //ERROR
            if(imageTitle == null) out.println("ERROR");
            imagen img = db.getImage(imageTitle);
            
            if(img == null) request.getRequestDispatcher("error.jsp").forward(request, response); //ERROR                       
            String path = getServletContext().getRealPath("WEB-INF/");
            String fullPath = path + File.separator + imageTitle+".jpg";
                                   
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+img.getTitle() +"</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + img.getTitle() +"</h1>");
            out.println("<h1> De: " + img.getAuthor() +"</h1>");                                 
            out.println("<img src=images/file1.jpg alt=\"ERROR\"> ");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
