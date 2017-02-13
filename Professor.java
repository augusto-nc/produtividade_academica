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
public class Professor extends Colaborador{

    public static final int PROFESSOR=2;

    public Professor(String nome) {
        super(nome);
    }

    Professor(String nome, String email){
        super(nome);
        this.email=email;
    }

    
    @Override
    public int getTipo() {
        return PROFESSOR;
   
    }
     public String getTipoString(){
        return "Professor";
    }
    
    
}
