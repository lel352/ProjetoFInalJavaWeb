/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle.bancodao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import projetofinal.controle.conexaobanco.Conectar;
import projetofinal.modelo.Usuario;

/**
 * Classe para controle de entra e saida de dados do banco
 * da tabela TipoCeveja
 * 
 * campos:
 *    codigo_usuario INTEGER NOT NULL IDENTITY PRIMARY KEY,  
 *    nome_usuario varchar(70) NOT NULL,
 *    email_usuario varchar(100) NOT NULL,
 *    nascimento_usuario date NOT NULL,	
 *    login_usuario varchar(10) NOT NULL, unique
 *    senha_usuario varchar(15) NOT NULL
 * 
 * 
 * @author Cássio, Leandro
 */
public class UsuarioDao {
    
    /**
     * Método para inserir dados
     * 
     * @param usuario - objeto
     * @return  1 - gravado com sucesso 0- erro
     */
    public int insert(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result;
        try {
            conn = Conectar.conectar();
            String sql = "insert into usuario (nome_usuario, email_usuario, nascimento_usuario, login_usuario, senha_usuario)"
                    + " values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setDate(3, new Date(usuario.getNascimento().getTime()));
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getSenha());
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
    
    /**
     * Métod para update do tipo conforme
     * 
     * @param usuario - objeto
     * @return 0-erro ao efetuar gravação 1-Gravado com sucesso 
     */
    public int update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {//
            conn = Conectar.conectar();
            String sql;
            sql = "update usuario set nome_usuario = ?, email_usuario = ?, nascimento_usuario = ?, senha_usuario = ? where codigo_usuario = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setDate(3, new Date(usuario.getNascimento().getTime()));
            ps.setString(4, usuario.getSenha());
            ps.setInt(5, usuario.getCodigo());
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
     *Método pegar um registro na tabela 
     * 
     * @param codigo - código do Usuario a ser buscado
     * @return retorn um objeto tipo cerveja
     */
    public Usuario getUsuario(int codigo, String login) {
        Connection conn = null;
        PreparedStatement ps = null;
        Usuario usuario = null;
        try {
            conn = Conectar.conectar();
            if (codigo > -1) {
                String sql = "select codigo_usuario, nome_usuario, email_usuario, nascimento_usuario, login_usuario, senha_usuario from usuario where codigo_usuario = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, codigo);
            }
            else {
                String sql = "select codigo_usuario, nome_usuario, email_usuario, nascimento_usuario, login_usuario, senha_usuario from usuario where login_usuario = '"+login+"'";
                ps = conn.prepareStatement(sql);               
            }
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt(1);
                String nome = rs.getString(2);
                String email = rs.getString(3);
                Date data = rs.getDate(4);
                login = rs.getString(5);
                String senha = rs.getString(6);
               
                usuario = new Usuario();
                usuario.setCodigo(codigo);
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setNascimento(data);
                usuario.setLogin(login);
                usuario.setSenha(senha);
            }
            
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            usuario = null;
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
        return usuario;
    }  
    
        
}
