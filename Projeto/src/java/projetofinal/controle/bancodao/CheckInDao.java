package projetofinal.controle.bancodao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projetofinal.controle.conexaobanco.Conectar;
import projetofinal.modelo.CheckIn;

/**
 * Classe para controle dos checkin da cervejas
 * da tabela CheckIn
 * 
 * campos:
 * codigo_checkin INTEGER generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
 * codigocerveja_checkin INTEGER NOT NULL,
 * codigousuario_checkin INTEGER NOT NULL,
 * descricao_checkin varchar(100),
 * nota_checkin integer,
 * data_checkin date,
 * valor_checkin numeric(10,2)
 * FOREIGN KEY(codigocerveja_checkin) REFERENCES cerveja(codigo_cerveja) ON DELETE NO ACTION ON UPDATE NO ACTION,
 * FOREIGN KEY(codigousuario_checkin) REFERENCES usuario(codigo_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
 * 
 * @author Cássio, Leandro
 */
public class CheckInDao {
    
    /**
     * Método para inserir dados
     * 
     * @param checkIn - Obejto
     * @return  1 - gravado com sucesso 0- erro
     */
    public int insert(CheckIn checkIn) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result;
        try {
            conn = Conectar.conectar();
            String sql = "insert into CheckIn (codigocerveja_checkin,codigousuario_checkin,descricao_checkin,nota_checkin, data_checkin, valor_checkin) "
                    + "values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, checkIn.getCodigoCerveja());
            ps.setInt(2, checkIn.getCodigoUsuario());
            ps.setString(3, checkIn.getCometario());
            ps.setInt(4, checkIn.getNota());
            ps.setDate(5, new Date(checkIn.getData().getTime()));
            ps.setDouble(6, checkIn.getValor());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            result = 0;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
        return result;
    }
    
    /***
     * Método para pegar todos os registro da tabela
     * 
     * @param codigoUsuario - codigo do usuario
     * @return ArrayList Cerveja
     */
    public ArrayList<CheckIn> getAll(int codigoUsuario) {
        ArrayList<CheckIn> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conectar.conectar();
            String sql = "select CODIGO_checkin, CODIGOcerveja_checkin, NOME_CERVEJA, CODIGOusuario_checkin, descricao_checkin, nota_checkin, data_checkin, valor_checkin from checkin c left JOIN cerveja b ON b.CODIGO_Cerveja = c.CODIGOCerveja_Checkin "
                    + " where codigousuario_checkin = ? "
                    + " order by 7 desc";
            //String sql = "select codigo_tipo, nome_tipo from tipocerveja";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoUsuario);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                int codigoCerveja = rs.getInt(2);
                String nomeCerveja = rs.getString(3);
                String comentario = rs.getString(5);
                int nota = rs.getInt(6);
                Date date = rs.getDate(7);
                double valor = rs.getDouble(8);
               
                CheckIn checkIn = new CheckIn();
                checkIn.setCodigo(codigo);
                checkIn.setCodigoCerveja(codigoCerveja);
                checkIn.setNomeCerveja(nomeCerveja);
                checkIn.setCodigoUsuario(codigoUsuario);
                checkIn.setCometario(comentario);
                checkIn.setNota(nota);
                checkIn.setData(date);
                checkIn.setValor(valor);
                lista.add(checkIn);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            lista = null;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("ERRO: " + ex.getMessage());
                }
            }
        }
        return lista;
    }     
    
    
}
