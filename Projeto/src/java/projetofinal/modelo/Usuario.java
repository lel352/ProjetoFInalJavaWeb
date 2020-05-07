package projetofinal.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author leandro
 */
public class Usuario {
    private int codigo;
    private String nome;
    private String email;
    private Date nascimento;    
    private String nascimentoS;    
    private String login;
    private String senha;

    public String getNascimentoS() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        nascimentoS = df.format(getNascimento());
        return nascimentoS;
    }

    public void setNascimentoS(String nascimentoS) {
        this.nascimentoS = nascimentoS;
    }
    
    
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
}
