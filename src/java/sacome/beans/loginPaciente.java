/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
 
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.naming.NamingException;
import sacome.dao.PacienteDAO;
 
@SessionScoped
@Named
public class loginPaciente implements Serializable {
 
 private String cpf;
 private String senha;
@Inject PacienteDAO pacienteDAO;
     
 public void login() throws SQLException, NamingException {
         
    FacesContext context = FacesContext.getCurrentInstance();
    Flash flash = context.getExternalContext().getFlash();
       flash.setKeepMessages(true);
    Paciente paciente = pacienteDAO.validarLogin(this.cpf, this.senha);
    if(paciente != null){
       context.getExternalContext().getSessionMap().put("pacCPF", paciente.getCpf());
       context.getExternalContext().getSessionMap().put("pacNome", paciente.getNome());
       try {
           context.getExternalContext().redirect("pacienteDashboard.xhtml");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }else{
        context.addMessage("loginForm:login", new FacesMessage("Credenciais inválidas. Verifique o CPF e senha informados."));
    } 
}
 
public void logout() throws IOException {
   FacesContext context = FacesContext.getCurrentInstance();
   context.getExternalContext().getSessionMap().remove("pacCPF");
   context.getExternalContext().getSessionMap().remove("pacNome");
   context.getExternalContext().redirect("pacienteLogin.xhtml");
}
        
 public String getCpf() {
 return cpf;
 }
 
 public void setCpf(String cpf) {
 this.cpf = cpf;
 }
 
 public String getSenha() {
 return senha;
 }
 
 public void setSenha(String senha) {
 this.senha = senha;
 }
}
