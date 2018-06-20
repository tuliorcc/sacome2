/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.beans;

import java.io.IOException;
import java.sql.SQLException;
 
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.NamingException;
import sacome.dao.MedicoDAO;
 
@RequestScoped
@Named
public class loginMedico {
 
 private String crm;
 private String senha;
@Inject MedicoDAO medicoDao;
     
     
 public void login() throws SQLException, NamingException {
         
         FacesContext context = FacesContext.getCurrentInstance();
         if(medicoDao.validarMedicoLogin(this.crm, this.senha)){
             context.getExternalContext().getSessionMap().put("medico", crm);
             try {
 context.getExternalContext().redirect("medicoDashboard.xhtml");
 } catch (IOException e) {
 e.printStackTrace();
 }
         }
         else  {
                    //Send an error message on Login Failure 
             context.addMessage(null, new FacesMessage("Authentication Failed. Check username or password."));
 
         } 
     }
 
     public void logout() {
     	FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
         try {
 context.getExternalContext().redirect("medicoLogin.xhtml");
 } catch (IOException e) {
 e.printStackTrace();
 }
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
