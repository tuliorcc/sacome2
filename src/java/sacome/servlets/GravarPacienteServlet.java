package sacome.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import sacome.beans.Paciente;
import sacome.dao.PacienteDAO;
import sacome.forms.AddPacienteFormBean;


@WebServlet(name = "GravarPacienteServlet", urlPatterns = {"/admin/gravarPaciente"})
public class GravarPacienteServlet extends HttpServlet {
    
    @Resource(name="jdbc/sacomeDBlocal")
    DataSource dataSource;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException {
        AddPacienteFormBean npfb = (AddPacienteFormBean) request.getSession().getAttribute("novoPaciente");
        request.getSession().removeAttribute("novoPaciente");

        PacienteDAO udao = new PacienteDAO(dataSource);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse(npfb.getDataDeNascimento());
        } catch (ParseException e) {
            request.setAttribute("pac_mensagens", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        try {
            Paciente u = new Paciente();
            u.setCpf(npfb.getCpf());
            u.setNome(npfb.getNome());
            u.setSenha(npfb.getSenha());
            u.setTelefone(npfb.getTelefone());
            u.setDataDeNascimento(dataNascimento);
            u.setSexo(npfb.getSexo());
            u = udao.gravarPaciente(u);
           
            request.setAttribute("pac_mensagens", "Paciente cadastrado com sucesso!");
            request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GravarPacienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GravarPacienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
