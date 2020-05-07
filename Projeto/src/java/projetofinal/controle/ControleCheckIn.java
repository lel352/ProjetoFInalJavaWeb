/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controle;

import projetofinal.controle.bancodao.CheckInDao;
import projetofinal.modelo.CheckIn;

/**
 *
 * @author leandro
 */
public class ControleCheckIn {
    
    private void validaCampos(CheckIn checkIn) throws Exception{
        if (checkIn.getCodigoCerveja() == 0)
            throw new Exception("Codigo da cerveja inválido !!!");        
    } 
    
    /**
     * Método para gravação de tipo de cerveja no banco
     * 
     * @param checkIn - objeto
     * @throws Exception - erro de gravação
     */
    public void grava(CheckIn checkIn) throws Exception{
        validaCampos(checkIn);
        CheckInDao checkInDao = new CheckInDao();
        
        int result = checkInDao.insert(checkIn);
        if (result == 0)
            throw new Exception("Erro ao efetuar a gravação");
    }
    
}
