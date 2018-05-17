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
import sacome.beans.Consulta;
import sacome.dao.ConsultaDAO;
import sacome.dao.PacienteDAO;

@WebServlet(name = "PacienteLoginServlet", urlPatterns = {"/paciente"})
public class PacienteLoginServlet extends HttpServlet {

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
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            PacienteDAO pdao = new PacienteDAO(dataSource);
            ConsultaDAO cdao = new ConsultaDAO(dataSource);
            List<Consulta> todasConsultas = null;
            
            if(request.getParameter("sair") != null) {
                request.getSession().removeAttribute("acessoPaciente");
                request.getSession().removeAttribute("cpfPaciente");
            }
            
            if(login == null) {
                String acesso = (String) request.getSession().getAttribute("acessoPaciente");
                if("ok".equals(acesso)) {
                    request.setAttribute("userValid", true);
                    request.setAttribute("userInvalid", false);
                    cpf = (String) request.getSession().getAttribute("cpfPaciente");
                    todasConsultas = cdao.listarConsultasPorPaciente(cpf);
                    request.setAttribute("listaConsultas", todasConsultas);
                    request.getRequestDispatcher("/dashboardPaciente.jsp").forward(request, response);
                }else{
                    request.setAttribute("userInvalid", false);
                    request.setAttribute("userValid", false);
                    request.getRequestDispatcher("/pacienteLogin.jsp").forward(request, response);      
                }
            }else{
                if (pdao.validarPacienteLogin(cpf, senha)){
                   request.getSession().setAttribute("acessoPaciente", "ok");
                   request.getSession().setAttribute("cpfPaciente", cpf);
                   request.setAttribute("userValid", true);
                   request.setAttribute("userInvalid", false);
                   todasConsultas = cdao.listarConsultasPorPaciente(cpf);
                   request.setAttribute("listaConsultas", todasConsultas);
                   request.getRequestDispatcher("/dashboardPaciente.jsp").forward(request, response);
               } else {
                   request.setAttribute("userInvalid", true);
                   request.setAttribute("userValid", false);
                   request.getRequestDispatcher("/pacienteLogin.jsp").forward(request, response);
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
