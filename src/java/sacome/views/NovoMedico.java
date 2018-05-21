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
import sacome.beans.Medico;
import sacome.dao.MedicoDAO;


@Named
@SessionScoped
public class NovoMedico implements Serializable {


    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;


    Medico dadosMedico;
    @Inject MedicoDAO medicoDao;


    public NovoMedico() {
        dadosMedico = new Medico();
    }


    @PostConstruct
   public void init() {
      // medicoDao = new MedicoDAO(dataSource);

   }
   public Medico getDadosMedico() {
        return dadosMedico;
    }


    public void setDadosMedico(Medico dadosMedico) {
        this.dadosMedico = dadosMedico;
    }
    
    public String recomecar() {
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "index?faces-redirect=true";
   }

   public String gravarMedico() throws SQLException, NamingException {
       medicoDao.gravarMedico(dadosMedico);
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Flash flash = facesContext.getExternalContext().getFlash();
       flash.setKeepMessages(true);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Médico gravado com sucesso."));

       return recomecar();
   }
}
