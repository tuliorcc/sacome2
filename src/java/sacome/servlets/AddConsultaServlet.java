/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import sacome.beans.Consulta;
import sacome.dao.ConsultaDAO;
import sacome.forms.AddConsultaFormBean;


/**
 *
 * @author tulio
 */
@WebServlet(name = "AddConsultaServlet", urlPatterns = {"/paciente/addConsulta"})
public class AddConsultaServlet extends HttpServlet {

    @Resource(name="jdbc/sacomeDBlocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AddConsultaFormBean npfb = new AddConsultaFormBean();
        try {
            // Obs: BeanUtils é uma classe da biblioteca
            // Apache Commons BeanUtils
            // http://commons.apache.org/beanutils/
            BeanUtils.populate(npfb, request.getParameterMap());
            ConsultaDAO cdao = new ConsultaDAO(dataSource);         
            List<String> mensagens = new ArrayList<>();           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataConsulta = null;          
            try {
            dataConsulta = sdf.parse(npfb.getDataConsulta());
            } catch (ParseException pe) {
                mensagens.add("Data inválida.");
                request.setAttribute("mensagens", mensagens);
                request.getRequestDispatcher("/dashboardPaciente.jsp").forward(request, response);
            }
            sdf.applyPattern("dd/MM/yyyy");
            npfb.setDataString(sdf.format(dataConsulta));
            
            request.getSession().setAttribute("novaConsulta", npfb);
            
            
             
            List<Consulta> consultasmed = cdao.buscarConsultaMedico(npfb.getCrm(), dataConsulta);
            if(consultasmed != null){
                mensagens.add("Médico já possui uma consulta neste horário.");
            }
            
            List<Consulta> consultaspac = cdao.buscarConsultaPaciente(npfb.getCpf(), dataConsulta);
            if(consultaspac != null){
                mensagens.add("Você já possui uma consulta neste horário.");
            }
            
            if (mensagens.isEmpty()) {
            request.getRequestDispatcher("/confirmarConsulta.jsp").forward(request, response);
            } else {
                request.setAttribute("mensagens", mensagens);
                request.getRequestDispatcher("/dashboardPaciente.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
