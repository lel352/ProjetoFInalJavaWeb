/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle;

import projetofinal.controle.bancodao.UsuarioDao;
import projetofinal.modelo.Login;
import projetofinal.modelo.Usuario;

/**
 * Class para controlar dados do Usuário
 * 
 * @author Cássio, Leandro
 */
public class ControleUsuario {
    
    /***
     * Método para validação dos campos
     * 
     * @param usuario - objeto
     * @throws Exception - erro de campo inválido
     */ 
    private void validaCampos(Usuario usuario) throws Exception{
        if (usuario.getNome() == null || usuario.getNome().trim().length() == 0)
            throw new Exception("Nome não pode ser em branco !!!");        
        if (usuario.getEmail() == null || usuario.getEmail().trim().length() == 0)
            throw new Exception("E-mail não pode ser em branco !!!");        
        if (usuario.getLogin() == null || usuario.getLogin().trim().length() == 0)
            throw new Exception("Login não pode ser em branco !!!");        
        if (usuario.getSenha() == null || usuario.getSenha().trim().length() == 0)
            throw new Exception("Senha não pode ser em branco !!!");        
    } 
    
    /**
     * Método para trazer os dados do Usuário do banco, conforme o login informado
     * 
     * @param login - objeto login
     * @return - usuario
     */
    public Usuario getUsuario(Login login){
        UsuarioDao usuarioDao = new UsuarioDao();
        return usuarioDao.getUsuario(-1,login.getLogin());
    }
        
    public void grava(Usuario usuario) throws Exception{
        validaCampos(usuario);
        UsuarioDao usuarioDao = new UsuarioDao();
        
        if (usuarioDao.getUsuario(usuario.getCodigo(),null) == null){ // se não existe, grava
            int result = usuarioDao.insert(usuario);
            if (result == 0)
                throw new Exception("Erro ao efetuar a gravação");
        }
        else{ //se existe, edita
            int result = usuarioDao.update(usuario); 
            if (result == 0)
                throw new Exception("Erro ao efetuar a alteração");
        }
    }
    
}
