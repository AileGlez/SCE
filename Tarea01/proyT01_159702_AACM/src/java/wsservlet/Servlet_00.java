/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aalex
 */
@WebServlet(name = "ServletClase6", urlPatterns = {"/ServletClase6"})
public class Servlet_00 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String deDonde = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int a,b,c;
        a = Integer.parseInt(request.getParameter("opA"));
        b = Integer.parseInt(request.getParameter("opB"));
        c = a + b;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet_00</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_00 at " + request.getContextPath() + "</h1>");
            out.println("<br><h1>El resultado de la suma de " + a + " y " + b + " es " + c + "</h1>");
            out.println("<br><h1>Proviene de " + deDonde + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deDonde = "Get";
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deDonde = "Post";
        processRequest(request, response);
    }
}
