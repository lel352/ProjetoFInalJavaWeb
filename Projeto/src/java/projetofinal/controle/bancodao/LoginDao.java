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
import projetofinal.controle.conexaobanco.Conectar;
import projetofinal.modelo.Login;


/**
 *
 * @author Leandro
 * 
 * classe para trazer as informações do login do banco
 */
public class LoginDao {
    
    /**
     * Método - Retorna os dados do usarios conforme o login informado
     * 
     * @param userLogin login de entrada
     * @return Objeto Login
     */
     public Login getUser(String userLogin){
        Connection conn = null;
        PreparedStatement ps = null;
        Login login = null;
        try {
            conn = Conectar.conectar();
            String sql = "select login_usuario, senha_usuario from usuario where login_usuario = '"+userLogin+"'";
            ps = conn.prepareStatement(sql);            

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userLoginb = rs.getString(1);
                String userSenha = rs.getString(2);
                
                login = new Login();
                login.setLogin(userLoginb);
                login.setSenha(userSenha);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            login = null;
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
        return login;
     }     
}
