/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.dao;

import sacome.beans.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;


@RequestScoped
public class MedicoDAO {
    private final static String CRIAR_MEDICO_SQL = "insert into Medico"
            + " (crm, nome, senha, especialidade)"
            + " values (?,?,?,?)";


    private final static String LISTAR_MEDICO_SQL = "select"
            + " u.id as medicoId, u.crm, u.nome, u.senha, u.especialidade"
            + " from Medico u";


    private final static String LISTAR_MEDICO_POR_ESPECIALIDADE_SQL = "select"
            + " u.id as medicoId, u.crm, u.nome, u.senha, u.especialidade"
            + " from Medico u"
            + " where especialidade = ?";
    
    private final static String BUSCAR_MEDICO_POR_CRM = "select"
            + " u.id as medicoId, u.crm, u.nome, u.senha, u.especialidade"
            + " from Medico u"
            + " where crm = ?";

        private final static String MEDICO_VALIDAR_LOGIN_SQL = "select"
        + " id, crm, nome, senha"
        + " from medico"
        + " where crm=? and senha=?";
        
    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;


    public Medico gravarMedico(Medico p) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_MEDICO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, p.getCrm());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getSenha());
            ps.setString(4, p.getEspecialidade());
            ps.execute();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                p.setId(rs.getInt(1));
            }
        }
        return p;
    }

    public Boolean checarCRM(String crm) throws SQLException, NamingException {
        Boolean ret = false;
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_MEDICO_POR_CRM)) {
            ps.setString(1, crm);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public List<Medico> listarTodosMedicos() throws SQLException, NamingException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_MEDICO_SQL)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {  
                    Medico u = new Medico();
                    u.setId(rs.getInt("medicoId"));
                    u.setCrm(rs.getString("crm"));
                    u.setNome(rs.getString("nome"));
                    u.setSenha(rs.getString("senha"));
                    u.setEspecialidade(rs.getString("especialidade"));
                    ret.add(u);
                }
            }
        }
        return ret;
    }


    public List<Medico> listarTodosMedicosPorEspecialidade(String especialidade) throws SQLException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_MEDICO_POR_ESPECIALIDADE_SQL)) {
            ps.setString(1, especialidade);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico u = new Medico();
                    u.setId(rs.getInt("medicoId"));
                    u.setCrm(rs.getString("crm"));
                    u.setNome(rs.getString("nome"));
                    u.setSenha(rs.getString("senha"));
                    u.setEspecialidade(rs.getString("especialidade"));
                    ret.add(u);                }
            }
        }
        return ret;
    }
    
    public Medico validarLogin(String crm, String senha) throws SQLException, NamingException {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(MEDICO_VALIDAR_LOGIN_SQL)) {
        ps.setString(1, crm);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Medico u = new Medico();
            u.setCrm(rs.getString("crm"));
            u.setNome(rs.getString("nome"));
            return u;
        }else{
            return null;
        }
    }
}
}

