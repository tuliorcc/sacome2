/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.dao;

import sacome.beans.Admins;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;


@RequestScoped
public class AdminsDAO {


    private final static String CRIAR_ADMINS_SQL = "insert into Admins"
            + " (nome, login, senha)"
            + " values (?,?,?)";


    private final static String ADMIN_VALIDAR_LOGIN_SQL = "select"
            + " id, nome, login, senha"
            + " from admins"
            + " where login=? and senha=?";
    
    @Resource(name = "jdbc/sacomeDBlocal")
    DataSource dataSource;
    
    public Admins gravarAdmin(Admins u) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_ADMINS_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.execute();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                u.setId(rs.getInt(1));
            }
        }
        return u;
    }

    public Admins validarLogin(String login, String senha) throws SQLException, NamingException {
    try (Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(ADMIN_VALIDAR_LOGIN_SQL)) {
        ps.setString(1, login);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Admins u = new Admins();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            return u;
        }else{
            return null;
        }
    }
}
    
    
    
}