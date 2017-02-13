/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Jose_Augusto
 */
public class ProducaoAcademica {        
    String titulo;
    long dataPublicacao;

    
       public static TreeMap<Long,ProducaoAcademica> getProducoes(Colaborador c,ArrayList<ProducaoAcademica> prods){
       TreeMap<Long,ProducaoAcademica> prods1= new TreeMap<Long, ProducaoAcademica>();
       for(ProducaoAcademica pA:prods){
           if(pA.containColaborador(c)){
               prods1.put(pA.dataPublicacao,pA);
           }
       }
       return prods1;
   } 
      
      public static ProducaoAcademica getProducao(Scanner sc,ArrayList<ProducaoAcademica> prods,boolean publicacao,boolean orientacao){
          System.out.println("Produção Académica: ");
     
        for(int i=0;i<prods.size();i++){
            if(prods.get(i)!=null && ((prods.get(i) instanceof Publicacao && publicacao) || (prods.get(i) instanceof Orientacao && orientacao))){
                  System.out.print(i+"-");
                  prods.get(i).imprimir();
            }         
        }
        System.out.print("Produação:(id) ");
        int k=sc.nextInt();
        try{
            if(prods.get(k)!=null && ((prods.get(k) instanceof Publicacao && publicacao) || (prods.get(k) instanceof Orientacao && orientacao)))
                 return prods.get(k);
            else
                return null;
        }catch(IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
        
        
   }    
       
       
       
       
     public void imprimir(){
     
        }    
       
       
   public boolean containColaborador(Colaborador c){
       return false;
   }
      
    
    
}
