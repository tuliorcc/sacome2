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
import sacome.dao.MedicoDAO;
 
@SessionScoped
@Named
public class loginMedico implements Serializable {
 
 private String crm;
 private String senha;
@Inject MedicoDAO medicoDao;
     
 public void login() throws SQLException, NamingException {
         
    FacesContext context = FacesContext.getCurrentInstance();
    Flash flash = context.getExternalContext().getFlash();
       flash.setKeepMessages(true);
    Medico medico = medicoDao.validarLogin(this.crm, this.senha);
    if(medico != null){
       context.getExternalContext().getSessionMap().put("medCRM", medico.getCrm());
       context.getExternalContext().getSessionMap().put("medNome", medico.getNome());
       try {
           context.getExternalContext().redirect("medicoDashboard.xhtml");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }else{
        context.addMessage("loginForm:login", new FacesMessage("Credenciais inválidas. Verifique o CRM e senha informados."));
    } 
}
 
public void logout() throws IOException {
   FacesContext context = FacesContext.getCurrentInstance();
   context.getExternalContext().getSessionMap().remove("medCRM");
   context.getExternalContext().getSessionMap().remove("medNome");
   context.getExternalContext().redirect("medicoLogin.xhtml");
}
        
 public String getCrm() {
 return crm;
 }
 
 public void setCrm(String crm) {
 this.crm = crm;
 }
 
 public String getSenha() {
 return senha;
 }
 
 public void setSenha(String senha) {
 this.senha = senha;
 }
}
