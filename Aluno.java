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
public class Aluno extends Colaborador{
    
        public static final int ALUNO=3;

    public Aluno(String nome) {
        super(nome);
    }

    
    @Override
    public int getTipo() {
        return ALUNO;
   
    }
     public String getTipoString(){
        return "Aluno";
    }
    
    
}
