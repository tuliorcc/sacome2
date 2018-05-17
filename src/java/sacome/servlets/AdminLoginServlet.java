/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import sacome.dao.AdminsDAO;

@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/admin"})
public class AdminLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            String login = request.getParameter("login");
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            AdminsDAO adao = new AdminsDAO(dataSource);           
            
            if(request.getParameter("sair") != null) {
                request.getSession().removeAttribute("acessoAdmin");
            }
            
            if(login == null) {
                String acesso = (String) request.getSession().getAttribute("acessoAdmin");
                if("ok".equals(acesso)) {
                    request.setAttribute("adminValid", true);
                    request.setAttribute("adminInvalid", false);
                    request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
                }else{
                    request.setAttribute("adminInvalid", false);
                    request.setAttribute("adminValid", false);
                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);      
                }
            }else{
                if (adao.validarAdminLogin(usuario, senha)){
                    request.getSession().setAttribute("acessoAdmin", "ok");
                    request.setAttribute("adminValid", true);
                    request.setAttribute("adminInvalid", false);
                    request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
                } else {
                    request.setAttribute("adminInvalid", true);
                    request.setAttribute("adminValid", false);
                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                }
            }
      
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
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
