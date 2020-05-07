/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle.conexaobanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author leandro
 */
public class Conectar {
    
    
    /** 
     * Método para efetuar a conexão com o banco de dados
     * 
     * @param path - caminho do banco de dados
     * @return Connection - retorna a conexão com o banco
     * @throws SQLException  - erro de conexão
     */
    public static Connection conectar() throws SQLException {
        Connection conn = null;
        Statement stat = null;
        ResultSet rset = null;

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:hsql://127.0.0.1/data", "sa", "");
            stat = conn.createStatement();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
}
