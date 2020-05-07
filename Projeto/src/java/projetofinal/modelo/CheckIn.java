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
public class CheckIn {
    private int codigo;
    private int codigoCerveja;
    private String nomeCerveja;
    private int codigoUsuario;
    private double valor;    
    private int nota;
    private String cometario;  
    private Date data;    
    private String datas;    

    public String getDatas() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        datas = df.format(getData());
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNomeCerveja() {
        return nomeCerveja;
    }

    public void setNomeCerveja(String nomeCerveja) {
        this.nomeCerveja = nomeCerveja;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }    
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    

    public int getCodigoCerveja() {
        return codigoCerveja;
    }

    public void setCodigoCerveja(int codigoCerveja) {
        this.codigoCerveja = codigoCerveja;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }
    
}
