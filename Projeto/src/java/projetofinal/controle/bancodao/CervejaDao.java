/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle.bancodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import projetofinal.controle.conexaobanco.Conectar;
import projetofinal.modelo.Cerveja;

/**
 * Classe para controle de entra e saida de dados do banco
 * da tabela Cerveja
 * 
 * campos:
 * codigo_cerveja INTEGER generated by default as identity (start with 1) NOT NULL PRIMARY KEY,
 * nome_cerveja varchar(100) NOT NULL,
 * codigotipo_cerveja INTEGER NOT NULL,
 * FOREIGN KEY(codigotipo_cerveja) REFERENCES tipocerveja(codigo_tipo) ON DELETE NO ACTION ON UPDATE NO ACTION
 * 
 * @author Cássio, Leandro
 */
public class CervejaDao {
    
    /**
     * Método para inserir dados
     * 
     * @param cerveja - Obejto
     * @return  1 - gravado com sucesso 0- erro
     */
    public int insert(Cerveja cerveja) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result;
        try {
            conn = Conectar.conectar();
            String sql = "insert into cerveja (nome_cerveja, codigotipo_cerveja) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cerveja.getNome());
            ps.setInt(2, cerveja.getCodigoTipo());
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
     * @param cerveja - objeto
     * @return 0-erro ao efetuar gravação 1-Gravado com sucesso 
     */
    public int update(Cerveja cerveja) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {//
            conn = Conectar.conectar();
            String sql;
            sql = "update cerveja set nome_cerveja = ?, codigotipo_cerveja = ? where codigo_cerveja = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cerveja.getNome());
            ps.setInt(2, cerveja.getCodigoTipo());
            ps.setInt(3, cerveja.getCodigo());
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
     * @param codigo - código da cerveja a ser buscado
     * @return retorn um objeto cerveja
     */
    public Cerveja getCerveja(int codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        Cerveja cerveja = null;
        try {
            conn = Conectar.conectar();
            String sql = "select CODIGO_CERVEJA, NOME_CERVEJA, CODIGOTIPO_CERVEJA, NOME_TIPO from CERVEJA c INNER JOIN TIPOCERVEJA t ON t.CODIGO_TIPO = c.CODIGOTIPO_CERVEJA where c.CODIGO_CERVEJA = ?";
            //String sql = "select codigo_cerveja, nome_cerveja, codigotipo_cerveja, nome_tipo from cerveja where codigo_cerveja = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString(2);
                int codigoTipo = rs.getInt(3);
                String nomeTipo = rs.getString(4);
               
                cerveja = new Cerveja();
                cerveja.setCodigo(codigo);
                cerveja.setNome(nome);
                cerveja.setCodigoTipo(codigoTipo);
                cerveja.setNomeTipo(nomeTipo);
            }
            
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            cerveja = null;
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
        return cerveja;
    }
    
    
    /***
     *Método registros na tabela cerveja
     * 
     * @param consulta - consultar parte do nome     
     * @return retorn uma lista objeto cerveja
     */
    public ArrayList<Cerveja> getCerveja(String consulta) {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<Cerveja> lista = new ArrayList<>();
        try {
            conn = Conectar.conectar();
            String sql = "select CODIGO_CERVEJA, NOME_CERVEJA, CODIGOTIPO_CERVEJA, NOME_TIPO from CERVEJA c left JOIN TIPOCERVEJA t ON t.CODIGO_TIPO = c.CODIGOTIPO_CERVEJA"
                        + " where Upper(c.NOME_CERVEJA) like '%"+consulta.toUpperCase()+"%'";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                int codigoTipo = rs.getInt(3);
                String nomeTipo = rs.getString(4);
               
                Cerveja cerveja = new Cerveja();
                cerveja.setCodigo(codigo);
                cerveja.setNome(nome);
                cerveja.setCodigoTipo(codigoTipo);
                cerveja.setNomeTipo(nomeTipo);
                lista.add(cerveja);
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
    
    /**
     * Método para pegar último codigo cadastrado
     * 
     * @return retorna o último codigo cadastrado
     */
    public int getMaxCodigo() {
        Connection conn = null;
        PreparedStatement ps = null;
        int codigo = 0;
        try {
            conn = Conectar.conectar();
            String sql = "select max(codigo_cerveja) from cerveja ";
            ps = conn.prepareStatement(sql);
            

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            codigo = -1;
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
        return codigo;
    }
    
    /***
     * Método para pegar todos os registro da tabela
     * 
     * @return ArrayList Cerveja
     */
    public ArrayList<Cerveja> getAll() {
        ArrayList<Cerveja> lista = new ArrayList<Cerveja>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conectar.conectar();
            String sql = "select CODIGO_CERVEJA, NOME_CERVEJA, CODIGOTIPO_CERVEJA, NOME_TIPO from CERVEJA c INNER JOIN TIPOCERVEJA t ON t.CODIGO_TIPO = c.CODIGOTIPO_CERVEJA";
            //String sql = "select codigo_tipo, nome_tipo from tipocerveja";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer codigo = rs.getInt(1);
                String nome = rs.getString(2);
                int codigoTipo = rs.getInt(3);
                String nomeTipo = rs.getString(4);
               
                Cerveja cerveja = new Cerveja();
                cerveja.setCodigo(codigo);
                cerveja.setNome(nome);
                cerveja.setCodigoTipo(codigoTipo);
                cerveja.setNomeTipo(nomeTipo);
                lista.add(cerveja);
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
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
    
    
    /***
     * Método para deletar cerveja
     * 
     * @param codigo - codigo do tipo a ser deletado
     * @return 
     */
    public boolean deletar(int codigo) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = true;
        try {
            conn = Conectar.conectar();
            String sql = "delete from cerveja where codigo_cerveja = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);

            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            result = false;            
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
}
