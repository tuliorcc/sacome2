/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.views;

import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.sql.DataSource;
import sacome.beans.Paciente;
import sacome.dao.PacienteDAO;


@Named
@SessionScoped
public class NovoPaciente implements Serializable {


    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;


    Paciente dadosPaciente;
    @Inject PacienteDAO pacienteDao;


    public NovoPaciente() {
        dadosPaciente = new Paciente();
    }


    @PostConstruct
   public void init() {
      // medicoDao = new MedicoDAO(dataSource);

   }
   public Paciente getDadosPaciente() {
        return dadosPaciente;
    }


    public void setDadosPaciente(Paciente dadosPaciente) {
        this.dadosPaciente = dadosPaciente;
    }
    
    public String recomecar() {
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "index?faces-redirect=true";
   }

   public String gravarPaciente() throws SQLException, NamingException {
       pacienteDao.gravarPaciente(dadosPaciente);
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Flash flash = facesContext.getExternalContext().getFlash();
       flash.setKeepMessages(true);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente gravado com sucesso."));

       return recomecar();
   }
}
