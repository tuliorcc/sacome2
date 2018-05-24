/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.views;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import sacome.beans.Medico;
import sacome.dao.MedicoDAO;

@Named
@RequestScoped
public class ListaMedicos implements Serializable {
    List<Medico> listaMedicos;
    @Inject MedicoDAO medicoDAO;


    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }


    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }
    
    public String verTodosMedicos() throws SQLException, NamingException {
        listaMedicos = medicoDAO.listarTodosMedicos();
        return "listaMedicos";
    }
    
     public String verTodosMedicos(String especialidade) throws SQLException, NamingException {
        if (especialidade == ""){
        listaMedicos = medicoDAO.listarTodosMedicos();
        }else{
        listaMedicos = medicoDAO.listarTodosMedicosPorEspecialidade(especialidade);
        }
        return "listaMedicos";
    }


}
