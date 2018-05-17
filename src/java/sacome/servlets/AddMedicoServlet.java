package sacome.servlets;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import sacome.dao.MedicoDAO;
import sacome.forms.AddMedicoFormBean;

/**
 *
 * @author tulio
 */
@WebServlet(name = "AddMedicoServlet", urlPatterns = {"/admin/addMedico"})
public class AddMedicoServlet extends HttpServlet {
    
    @Resource(name="jdbc/sacomeDBlocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AddMedicoFormBean npfb = new AddMedicoFormBean();
        try {
            // Obs: BeanUtils é uma classe da biblioteca
            // Apache Commons BeanUtils
            // http://commons.apache.org/beanutils/
            BeanUtils.populate(npfb, request.getParameterMap());
            request.getSession().setAttribute("novoMedico", npfb);
            List<String> mensagens = npfb.validar();
            MedicoDAO mdao = new MedicoDAO(dataSource);
            Boolean checkMed = mdao.checarCRM(npfb.getCrm());
            if(checkMed){
                mensagens.add("Já existe um médico com CRM '"+npfb.getCrm()+"' cadastrado.");
            }
            
            if (mensagens.isEmpty()) {
                request.getRequestDispatcher("/confirmarMedico.jsp").forward(request, response);
            } else {
                request.setAttribute("med_mensagens", mensagens);
                request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
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
