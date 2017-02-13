/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Jose_Augusto
 */
public class Orientacao extends ProducaoAcademica{
    
   Professor professorOrientador;
   ArrayList<Colaborador> orientados;
   
   @Override
    public boolean containColaborador(Colaborador c){
        return orientados.contains(c) || professorOrientador.equals(c);
       
   }
   
      public static Orientacao getInstance(Scanner sc,ArrayList<Colaborador> colaboradores) throws Exception{
                Orientacao p= new Orientacao();
                System.out.print("titulo: ");    
                p.titulo=sc.nextLine();
               System.out.print("Data de orientção: (dd/MM/yyyy) :");
                String data= sc.nextLine();
                try{
                      SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                      p.dataPublicacao= f.parse(data).getTime();
                }catch(ParseException e){
                       throw new Exception("Data Inválida");
                }
                
                
                
               System.out.print("Adicionar professor orientador: ");
                Professor prof=(Professor)Colaborador.getColaborador(colaboradores, sc, Professor.PROFESSOR);
                if(prof==null)
                    throw new Exception("Professor Inválido");
                p.professorOrientador=prof;
                p.adicionarOrientados(sc, colaboradores);
                
            return p;
    }
      
      
     public void imprimir(){
       SimpleDateFormat dateFormat= new SimpleDateFormat("EEEE dd/MM/YYYY",Locale.getDefault());
       
       System.out.println("Titulo orientação: "+titulo);
       System.out.println("Orientador: "+professorOrientador);
       System.out.println("Data : "+ dateFormat.format(new Date(dataPublicacao)));
   } 
      
      
     public void adicionarOrientados(Scanner sc,ArrayList<Colaborador> colaboradores){
          
                int r=0;
                do{
                System.out.print("Adicionar Orientados :");
                Colaborador c=Colaborador.getColaborador(colaboradores, sc, Aluno.ALUNO);
                if(c==null){
                    System.out.println("Orientado inválido");
                }
                System.out.println("Adiconar mais orientados? 1-sim,2-Não");
                r=sc.nextInt();
                }while(r==1);
     } 
    
      private Orientacao(){
          orientados=new ArrayList<>();
          
          
      }
    
    
}
