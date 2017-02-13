/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jose_Augusto
 */
public class Colaborador {
    public static final int COLABORADOR=1;
    private String nome;
    protected String email;
    public int id;
    public static Colaborador getColaborador(ArrayList<Colaborador> colaboradores,Scanner sc,Integer tipo){
            System.out.println("Colaboradores: ");
     
        for(int i=0;i<colaboradores.size();i++){
            if(colaboradores.get(i)!=null && (tipo==null || tipo==colaboradores.get(i).getTipo())){
                  System.out.println(i+"-"+colaboradores.get(i).getTipoString()+": "+colaboradores.get(i).getNome());
            }         
        }
        System.out.print("Colaborador:(id) ");
        int k=sc.nextInt();
        try{
            if(tipo==null || colaboradores.get(k).getTipo()==tipo)
                 return colaboradores.get(k);
            else
                return null;
        }catch(IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
        
        
     
    }
    
    
    public static Colaborador getInstance(Scanner sc){
        System.out.println("Escolha o Tipo");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.println("3. Pesquisador");
        System.out.println("4. Colaborador");

        int r=sc.nextInt();
        Colaborador c;
        if(r==1){
            c= new Aluno("");
        }else if(r==2){
             c= new Professor("");
        }else if(r==3){
             c= new Pesquisador("");
        }else if(r==4){
            c=new Colaborador("");
        }else{
             System.out.println("Tipo inválido");
               return null;
        }
        c.edit(sc);
        return c;
    }
    
    
    public void show(){
        
        System.out.println(getTipoString()+": "+(nome!=null ? nome : ""));
        System.out.println("Email: "+(email!=null ? email : ""));

    }
    
    public void edit(Scanner sc){
        
        int r=-1;
        while(r!=0){
        System.out.println("Escolha o item à ser definido: ");
        System.out.println("1- Nome");
        System.out.println("2- Email");
        System.out.println("0- Voltar");
                
        r= sc.nextInt();
        
        switch(r){
            case 1:
                System.out.print("Nome: ");   
                sc.nextLine();
                nome=sc.nextLine();
                break;
            case 2:
                System.out.print("Email: ");    
                sc.nextLine();
                email=sc.nextLine();
                break;  
        }
        show();
        }
    }
    
    
    public Colaborador(String nome) {
        this.nome=nome;
    }
    
    
    
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    
    public int getTipo(){
        return COLABORADOR;
    }
     public String getTipoString(){
        return "Colaborador";
    }

    @Override
    public boolean equals(Object obj) {
        return nome.equals(((Colaborador)obj).nome);
    }
     
     
     
 
    @Override
    public int hashCode() {
        return nome.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
     
     
    
    
}
