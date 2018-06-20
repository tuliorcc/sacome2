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
import sacome.dao.AdminsDAO;
 
@SessionScoped
@Named
public class loginAdmin implements Serializable {
 
 private String usuario;
 private String senha;
@Inject AdminsDAO adminsDao;
     
 public void login() throws SQLException, NamingException {
         
    FacesContext context = FacesContext.getCurrentInstance();
    Flash flash = context.getExternalContext().getFlash();
       flash.setKeepMessages(true);
    Admins admin = adminsDao.validarLogin(this.usuario, this.senha);
    if(admin != null){
       context.getExternalContext().getSessionMap().put("admID", admin.getId());
       context.getExternalContext().getSessionMap().put("admNome", admin.getNome());
       try {
           context.getExternalContext().redirect("adminDashboard.xhtml");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }else{
        context.addMessage("loginForm:login", new FacesMessage("Credenciais inválidas. Verifique o usuário e senha informados."));
    } 
}
 
public void logout() throws IOException {
   FacesContext context = FacesContext.getCurrentInstance();
   context.getExternalContext().getSessionMap().remove("admID");
   context.getExternalContext().getSessionMap().remove("admNome");
   context.getExternalContext().redirect("adminLogin.xhtml");
}
        
 public String getUsuario() {
 return usuario;
 }
 
 public void setUsuario(String usuario) {
 this.usuario = usuario;
 }
 
 public String getSenha() {
 return senha;
 }
 
 public void setSenha(String senha) {
 this.senha = senha;
 }
}
