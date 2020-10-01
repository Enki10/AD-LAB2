/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcu
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    private String userPath;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");         
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */    

            DB db = new DB();   

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username.isEmpty() || password.isEmpty()){
                request.getRequestDispatcher("error.jsp").forward(request, response); 
            } else {
                if(db.usuarioExists(username, password)){
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);

                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                } else {
                    out.println("db.usuarioExistis = false");
                    request.getRequestDispatcher("error.jsp").forward(request, response); 
                }
            }
        }

      
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
