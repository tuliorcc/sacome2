/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.dao;

import sacome.beans.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author tulio
 */
public class PacienteDAO {
     private final static String CRIAR_PACIENTE_SQL = "insert into paciente"
            + " (cpf, nome, senha, telefone, dataDeNascimento, sexo)"
            + " values (?,?,?,?,?,?)";


    private final static String BUSCAR_PACIENTE_SQL = "select"
            + " id, cpf, nome, senha, telefone, dataDeNascimento, sexo"
            + " from paciente"
            + " where cpf=?";
    
    private final static String PACIENTE_VALIDAR_LOGIN_SQL = "select"
        + " id, nome, cpf, senha"
        + " from paciente"
        + " where cpf=? and senha=?";
    
    DataSource dataSource;


    public PacienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Paciente gravarPaciente(Paciente u) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(CRIAR_PACIENTE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, u.getCpf());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getSenha());
            ps.setString(4, u.getTelefone());
            ps.setDate(5, new java.sql.Date(u.getDataDeNascimento().getTime()));
            ps.setString(6, u.getSexo().toUpperCase());
            ps.execute();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                u.setId(rs.getInt(1));
            }
        }
        return u;
    }

    public Boolean checarCPF(String cpf) throws SQLException, NamingException {
        Boolean ret = false;
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_PACIENTE_SQL)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ret = true;
                }
            }
        }
        return ret;
    }
    
    public Boolean validarPacienteLogin(String cpf, String senha) throws SQLException, NamingException {
        boolean st = false;
        try (Connection con = dataSource.getConnection();
                          
            PreparedStatement ps = con.prepareStatement(PACIENTE_VALIDAR_LOGIN_SQL)) {
            ps.setString(1, cpf);
            ps.setString(2, senha); 
            
            try (ResultSet rs = ps.executeQuery()) {
                st = rs.next();

            } catch (Exception e){
                e.printStackTrace();
            }
          
            return st;
        }
    }
}
