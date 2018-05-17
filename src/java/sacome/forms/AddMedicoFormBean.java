/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.forms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tulio
 */
public class AddMedicoFormBean {
    
    private String crm, nome, senha, especialidade;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    public List<String> validar() {
        List<String> mensagens = new ArrayList<String>();
        if (nome.trim().length() == 0) {
            mensagens.add("Nome não pode ser vazio!");
        }
        if (senha.trim().length() == 0) {
            mensagens.add("Senha não pode ser vazio!");
        }
        if (crm.trim().length() == 0) {
            mensagens.add("CRM não pode ser vazio!");
        }
        if (especialidade.trim().length() == 0) {
            mensagens.add("Especialidade não pode ser vazio!");
        }

        return mensagens;
    }
}
