/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

/**
 *
 * @author Jose_Augusto
 */
public class Pesquisador extends Colaborador{
    
        public static final int PESQUISADOR=4;

    public Pesquisador(String nome) {
        super(nome);
    }

    
    @Override
    public int getTipo() {
        return PESQUISADOR;
   
    }
     public String getTipoString(){
        return "Pesquisador";
    }
    
    
}
