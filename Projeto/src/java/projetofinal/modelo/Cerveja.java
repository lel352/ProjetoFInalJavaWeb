package projetofinal.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author leandro
 */
public class Cerveja {
    private int codigo;
    private int codigoTipo;
    private String nome;
    private String nomeTipo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
