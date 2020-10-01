/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author marcu
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

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
            DB db;
            
            HttpSession session = request.getSession();
            Object authorObj =session.getAttribute("username");
            
            
            String titol = request.getParameter("titol");
            String descripcio = request.getParameter("descripcio");
            String paraulesClau = request.getParameter("pclau");
            String author = authorObj.toString();
                        
            Part filePart = request.getPart("imatge");
            
            byte[] bImatge;
            
            if(filePart != null && filePart.getContentType().equals("image/jpeg")){

                db = new DB();
                if(!db.imageExists(titol)){
                    
                    out.println(filePart.getName());
                    out.println(filePart.getSize());
                    out.println(filePart.getContentType());
                                                           
                    String creationDate = "9999/99/99";
                    boolean res = db.insertImage(titol, descripcio, paraulesClau, author , creationDate);
                    
                    if(res){
                        OutputStream outStream = null;
                        InputStream filecontent = null;

                        String path = getServletContext().getRealPath("images/");
                        String fullPath = path + File.separator + titol+".jpg";
                        outStream = new FileOutputStream(new File(fullPath));
                        filecontent = filePart.getInputStream();                    

                        int read = 0;
                        final byte[] bytes = new byte[1024];

                        while ((read = filecontent.read(bytes)) != -1) {
                            outStream.write(bytes, 0, read);
                        }

                        outStream.close();
                        filecontent.close();
                    } else {
                        //errror al insertar en DB
                    }                    
                } else {
                    //error la imatge ja existeix;
                }
                                    
            }
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
