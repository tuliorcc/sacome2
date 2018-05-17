/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacome.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tulio
 */
public class AddPacienteFormBean {
    
    private String cpf, nome, senha, telefone, dataDeNascimento, dataString, sexo;

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    @SuppressWarnings("empty-statement")
    public List<String> validar() {
        List<String> mensagens = new ArrayList<String>();
        if (nome.trim().length() == 0) {
            mensagens.add("Nome não pode ser vazio!");
        }
        if (senha.trim().length() == 0) {
            mensagens.add("Nome não pode ser vazio!");
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.parse(dataDeNascimento);
        } catch (ParseException pe) {
           
            mensagens.add("Data de nascimento inválida!");
        }
        
        if (telefone.trim().length() == 0) {
            mensagens.add("Telefone não pode ser vazio!");
        }
        
        if (sexo.trim().length() == 0){
            mensagens.add("Sexo não pode ser vazio!");
        }
        if (!sexo.equals("M") && !sexo.equals("F") && !sexo.equals("X")){
            mensagens.add("Sexo inválido");
        }
        
        //Validação CPF
        if (cpf.length() == 14){
            String cleanCPF = cpf.replaceAll("[.-]", "");
            String digitos = cleanCPF.substring(0, 9);;
            String dvs = cleanCPF.substring(9, 11);

            String dv1 = gerarDV(digitos);
            String dv2 = gerarDV(digitos + dv1);

            if (!(dvs.equals(dv1 + dv2)) || cleanCPF.equals("00000000000")
                    || cleanCPF.equals("99999999999")){
                mensagens.add("CPF inválido.");
            }
        } else {    
            mensagens.add("CPF de tamanho inválido!");
        }
        
        
        
        return mensagens;
    }
    
    private String gerarDV(String digitos) {
        int peso = digitos.length() + 1;
        int dv = 0;
        for (int i = 0; i < digitos.length(); i++) {
            dv += Integer.parseInt(digitos.substring(i, i + 1)) * peso;
            peso--;
        }
         
        dv = 11 - (dv % 11);
         
        if (dv > 9) {
            return "0";
        }
         
        return String.valueOf(dv);
    }
    // Fim validação cpf
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
}
