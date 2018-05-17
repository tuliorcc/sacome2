package sacome.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import sacome.dao.PacienteDAO;
import sacome.forms.AddPacienteFormBean;

/**
 *
 * @author tulio
 */
@WebServlet(name = "AddPacienteServlet", urlPatterns = {"/admin/addPaciente"})
public class AddPacienteServlet extends HttpServlet {
    
    @Resource(name="jdbc/sacomeDBlocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AddPacienteFormBean npfb = new AddPacienteFormBean();
        try {
            // Obs: BeanUtils é uma classe da biblioteca
            // Apache Commons BeanUtils
            // http://commons.apache.org/beanutils/
            BeanUtils.populate(npfb, request.getParameterMap());
            List<String> mensagens = npfb.validar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascimento = null;       
            try {
            dataNascimento = sdf.parse(npfb.getDataDeNascimento());
            } catch (ParseException pe) {
                request.getSession().setAttribute("novoPaciente", npfb);
                mensagens.add("Data de nascimento inválida.");
                request.setAttribute("pac_mensagens", mensagens);
                request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
            }
            sdf.applyPattern("dd/MM/yyyy");
            npfb.setDataString(sdf.format(dataNascimento));
            request.getSession().setAttribute("novoPaciente", npfb);
            
            
            PacienteDAO pdao = new PacienteDAO(dataSource);
            Boolean checkPac = pdao.checarCPF(npfb.getCpf());
            if(checkPac){
                mensagens.add("Já existe um paciente com CPF '"+npfb.getCpf()+"' cadastrado.");
            }
            if (mensagens.isEmpty()) {
                request.getRequestDispatcher("/confirmarPaciente.jsp").forward(request, response);
            } else {
                request.setAttribute("pac_mensagens", mensagens);
                request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
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
