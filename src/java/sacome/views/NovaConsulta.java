/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.views;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.sql.DataSource;
import sacome.beans.Consulta;
import sacome.dao.ConsultaDAO;
import sacome.dao.MedicoDAO;
import sacome.dao.PacienteDAO;


@Named
@SessionScoped
public class NovaConsulta implements Serializable {


    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;

    Consulta dadosConsulta;
    @Inject MedicoDAO medicoDao;
    @Inject PacienteDAO pacienteDao;
    @Inject ConsultaDAO consultaDao;
    
    UIInput cpfInput;
    UIInput crmInput;
    List<Consulta> consultaEncontradaPaciente;
    List<Consulta> consultaEncontradaMedico;
    
    public NovaConsulta() {
        dadosConsulta = new Consulta();
    }


    @PostConstruct
   public void init() {
       //medicoDao = new MedicoDAO(dataSource);
       //pacienteDao = new PacienteDAO(dataSource);
       //consultaDao = new ConsultaDAO(dataSource);

   }

   
   public Consulta getDadosConsulta() {
        return dadosConsulta;
    }

   public UIInput getCpfInput() {
        return cpfInput;
    }
   
    public void setCpfInput(UIInput cpfInput) {
        this.cpfInput = cpfInput;
    }
    
   public UIInput getCrmInput() {
        return crmInput;
    }
   
    public void setCrmInput(UIInput crmInput) {
        this.crmInput = crmInput;
    }


    public void setDadosConsulta(Consulta dadosConsulta) {
        this.dadosConsulta = dadosConsulta;
    }
    
    
   public String recomecar() {
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "index?faces-redirect=true";
   }

   public String gravarConsulta() throws SQLException, NamingException {
       consultaDao.gravarConsulta(dadosConsulta);
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Flash flash = facesContext.getExternalContext().getFlash();
       flash.setKeepMessages(true);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consulta agendada com sucesso!"));

       return recomecar();
   }
 

   public void validarConsulta(FacesContext context, UIComponent toValidate, Date value) throws SQLException, ValidatorException {
       String Cpf = (String) cpfInput.getValue();
       String Crm = (String) crmInput.getValue();
       
       // Procura consultas do paciente pelo cpf
       consultaEncontradaPaciente = consultaDao.buscarConsultaPaciente(Cpf,
                                                               value);    
       if (consultaEncontradaPaciente != null) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Paciente já tem consulta agendada nesse horário! CPF = "+Cpf);
            context.addMessage(toValidate.getClientId(context), message);
        } 
       
       // Procura consultas do medico pelo crm
       consultaEncontradaMedico = consultaDao.buscarConsultaMedico(Crm,
                                                               value);
       if (consultaEncontradaMedico != null) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message2 = new FacesMessage("Médico já tem consulta agendada nesse horário! CRM = "+Crm);
            context.addMessage(toValidate.getClientId(context), message2);
        } 
    }

}
