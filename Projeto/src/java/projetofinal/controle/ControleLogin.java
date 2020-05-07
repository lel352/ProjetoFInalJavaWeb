package projetofinal.controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import projetofinal.controle.bancodao.LoginDao;
import projetofinal.controle.bancodao.UsuarioDao;
import projetofinal.modelo.Login;
import projetofinal.modelo.Usuario;

/**
 *
 * @author Delphi
 */
public class ControleLogin {
    
    /**
     * Método - validar se foi digatado algo ao entrar
     * 
     * @param login objeto - usario login
     * @throws Exception retorna erros se os campos do objeto estão vazio
     */
    private void validaLogin(Login login) throws Exception{
        if(login.getLogin() == null || login.getLogin().trim().length() == 0)
             throw new Exception("Login não pode ser em branco !!!");
        if(login.getSenha() == null || login.getSenha().trim().length() == 0)
             throw new Exception("Senha não pode ser em branco !!!");
    }
    
    /**
     * Método - validar o login, ver se usuario esta cadastrado no banco 
     * 
     * @param login objeto - usario login
     * @throws Exception retorn erros se login não encontado no banco e senha invalida
     */
    private void validaLoginSenha(Login login) throws Exception{
        LoginDao loginDao = new LoginDao();
        Login loginBusca = loginDao.getUser(login.getLogin());
        if (loginBusca == null)
            throw new Exception("Login não encontrado !!!");
        if (!login.getSenha().equals(loginBusca.getSenha()))
            throw new Exception("Senha  inválida !!!");
    }    
    
    /**
     * Método - Confirmar dados do login
     * 
     * @param login objeto * usuario login  
     * @throws Exception lança erros  
     */
    public void confirmarLogn(Login login) throws Exception{
          validaLogin(login);
          validaLoginSenha(login);          
    }
    
    
}
