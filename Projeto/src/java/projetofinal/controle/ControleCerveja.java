/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle;

import java.util.ArrayList;
import projetofinal.controle.bancodao.CervejaDao;
import projetofinal.modelo.Cerveja;

/**
 * Class para controlar dados da Cerveja
 * 
 * @author Cássio, Leandro
 */
public class ControleCerveja {
    
    /**
     * Método pega o último código cadastrado
     * 
     * @return último código cadastrado
     * @throws Exception 
     */
     public int maxCodigoCerveja() throws Exception{
        CervejaDao cervejaDao = new CervejaDao();
        int codigosequencial = cervejaDao.getMaxCodigo();
        if (codigosequencial == -1){
             throw new Exception("Erro no banco !!!");                     
        }     
        return codigosequencial;
    }
     
    /***
     * Método para validação dos campos
     * 
     * @param cerveja - objeto
     * @throws Exception - erro de campo inválido
     */ 
    private void validaCampos(Cerveja cerveja) throws Exception{
        if (cerveja.getNome() == null || cerveja.getNome().trim().length() == 0)
            throw new Exception("Nome não pode ser em branco !!!");        
        if (cerveja.getCodigoTipo() <= 0)
            throw new Exception("Tipo de cerveja inváldo !!!");        
    } 
    
    /**
     * Método para gravação de tipo de cerveja no banco
     * 
     * @param cerveja - objeto cerveja
     * @throws Exception - erro de gravação
     */
    public void gravaCerveja(Cerveja cerveja) throws Exception{
        validaCampos(cerveja);
        CervejaDao cervejaDao = new CervejaDao();
        
        if (cervejaDao.getCerveja(cerveja.getCodigo()) == null){ // se não existe, grava
            int result = cervejaDao.insert(cerveja);
            if (result == 0)
                throw new Exception("Erro ao efetuar a gravação");
        }
        else{ //se existe, edita
            int result = cervejaDao.update(cerveja); 
            if (result == 0)
                throw new Exception("Erro ao efetuar a alteração");
        }
    }

    /**
     * Método para consultar Cervejas
     * 
     * @param consulta texto a ser consultado
     * @return - retorna uma lista de cerveja
     */
    public ArrayList<Cerveja> consultarCerveja(String consulta) {
        CervejaDao cervejaDao = new CervejaDao();
        if (consulta == null || consulta.trim().length() == 0){
           return cervejaDao.getAll(); 
        }
        else{
            return cervejaDao.getCerveja(consulta);
        }
    }
    
    /**
     * Método para deletar Cerveja
     * 
     * @param codigo da cerveja
     * @throws Exception 
     */
    public void deletar(int codigo) throws Exception{
        CervejaDao cervejaDao = new CervejaDao();
        if (cervejaDao.deletar(codigo)) 
            return;
        throw new Exception("Não é possivel excluir! Possivelmente ja foi feito um checkin em uma cerveja !!!"); 
    }
    
}
