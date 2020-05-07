/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle;

import java.util.ArrayList;
import projetofinal.controle.bancodao.TipoCervejaDao;
import projetofinal.modelo.TipoCerveja;

/**
 * Class para controlar dados de Tipo de Cerveja
 * 
 * @author Cássio, Leandro
 */
public class ControleTipo {
    
    /**
     * Método pega o último código cadastrado
     * 
     * @return último código cadastrado
     * @throws Exception 
     */
     public int maxCodigoTipo() throws Exception{
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        int codigosequencial = tipoCervejaDao.getMaxCodigo();
        if (codigosequencial == -1){
            throw new Exception("Erro no banco !!!");                     
        }     
        return codigosequencial;
    }
     
    /***
     * Método para validação dos campos
     * 
     * @param tipoCerveja - objeto
     * @throws Exception - erro de campo inválido
     */ 
    private void validaCampos(TipoCerveja tipoCerveja) throws Exception{
        if (tipoCerveja.getNome() == null || tipoCerveja.getNome().trim().length() == 0)
            throw new Exception("Nome não pode ser em branco !!!");        
    } 
    
    /**
     * Método para gravação de tipo de cerveja no banco
     * 
     * @param tipoCerveja - objeto
     * @throws Exception - erro de gravação
     */
    public void gravaTipo(TipoCerveja tipoCerveja) throws Exception{
        validaCampos(tipoCerveja);
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        
        if (tipoCervejaDao.getTipoCerveja(tipoCerveja.getCodigo()) == null){ // se não existe, grava
            int result = tipoCervejaDao.insert(tipoCerveja);
            if (result == 0)
                throw new Exception("Erro ao efetuar a gravação");
        }
        else{ //se existe, edita
            int result = tipoCervejaDao.update(tipoCerveja); 
            if (result == 0)
                throw new Exception("Erro ao efetuar a alteração");
        }
    }

    /***
     * Método para executar a consultar conforme o que foi informado 
     * 
     * @param consulta
     * @return 
     */
    public ArrayList<TipoCerveja> consultarTipo(String consulta) {
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        if (consulta == null || consulta.trim().length() == 0){
           return tipoCervejaDao.getAll(); 
        }
        else{
            return tipoCervejaDao.getTipoCerveja(consulta);
        }
    }
    
    /**
     * Método para deletar tipo de cerveja
     * 
     * @param codigo
     * @throws Exception - se der erro ou se não é possivel deletar 
     */
    public void deletar(int codigo) throws Exception{
        TipoCervejaDao tipoCervejaDao = new TipoCervejaDao();
        if (tipoCervejaDao.deletar(codigo)) 
            return;
        throw new Exception("Não é possivel excluir! Possivelmente Tipo está cendo usado em alguma cerveja !!!"); 
    }
    
}
