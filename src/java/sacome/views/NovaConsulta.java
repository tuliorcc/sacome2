/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.views;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    PacienteDAO pacienteDao;
    ConsultaDAO consultaDao;


    public NovaConsulta() {
        dadosConsulta = new Consulta();
    }


    @PostConstruct
   public void init() {
//       medicoDao = new MedicoDAO(dataSource);
       pacienteDao = new PacienteDAO(dataSource);
       consultaDao = new ConsultaDAO(dataSource);

   }
   public Consulta getDadosConsulta() {
        return dadosConsulta;
    }


    public void setDadosConsulta(Consulta dadosConsulta) {
        this.dadosConsulta = dadosConsulta;
    }
}
