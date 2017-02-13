/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jose_Augusto
 */
public class Publicacao extends ProducaoAcademica{

    private String nomeConferencia;
    private Projeto projetoAssociado;
    private ArrayList<Colaborador> autores;
    
    
    public static Publicacao getInstance(Scanner sc) throws Exception{
                Publicacao p= new Publicacao();
                System.out.print("titulo: ");   
                sc.nextLine();
                p.titulo=sc.nextLine();
                System.out.print("Nome Conferencia: ");    
                p.nomeConferencia=sc.nextLine();
                System.out.print("Data de publicação: (dd/MM/yyyy) :");
                String data= sc.nextLine();
                try{
                      SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                      p.dataPublicacao= f.parse(data).getTime();
                }catch(ParseException e){
                       throw new Exception("Data Inválida");
                }
                
                
                
            return p;
    }

    Publicacao(String titulo, String conferencia, String data,Colaborador[] col) {
        this.titulo=titulo;
        this.nomeConferencia=conferencia;
        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            this.dataPublicacao= f.parse(data).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Publicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        autores= new ArrayList<>();
        for(Colaborador c:col){
            autores.add(c);
        }
        
        
    }
    
     @Override
    public boolean containColaborador(Colaborador c){
        return autores.contains(c);
       
   }
    
   @Override
   public void imprimir(){
       SimpleDateFormat dateFormat= new SimpleDateFormat("EEEE dd/MM/YYYY",Locale.getDefault());
       System.out.println("Titulo publicação: "+titulo);
       System.out.println("Conferencia: "+nomeConferencia);
       System.out.println("Data de publicação: "+ dateFormat.format(new Date(dataPublicacao)));
   }


       
       
    
    
    
    public void adicionarAutor(Scanner sc,ArrayList<Colaborador> allColaboradores){
        Colaborador c=Colaborador.getColaborador(allColaboradores, sc,null);
        if(c==null){
            System.out.println("Colaborador Inválido");
        }else{
            System.out.println("Autor adicionado com sucesso");
            autores.add(c);   
        }
    }
    
    
 
    
    private Publicacao(){
        autores= new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        Publicacao p=(Publicacao)obj;
        if(titulo==p.titulo)
            return true;
        else if(titulo==null || p.titulo==null)
            return false;
        return titulo.equals(p.titulo ) && dataPublicacao==p.dataPublicacao;
    }
    
    
     public void adicionarAProjeto(Scanner sc,ArrayList<Projeto> allProjetos){
        System.out.println("Escolha o projeto que deseja adionar à publicação:");
        boolean find=false;
        for(int i=0;i<allProjetos.size();i++){
            if(allProjetos.get(i)!=null && allProjetos.get(i).getStatus()==Projeto.ANDAMENTO){
                System.out.println(i+"- "+allProjetos.get(i));
                find=true;
            }
        }
        if(find){
             System.out.print("Projeto (id): ");
             int p= sc.nextInt();
             try{
             if(allProjetos.get(p).getStatus()!=Projeto.ANDAMENTO){
                 System.out.println("Projeto Inválido");
                 return;
             } 
             }catch(Exception e){
                System.out.println("Projeto Inválido");
                return; 
             }
             if(projetoAssociado!=null)
                 projetoAssociado.removePublibicacao(this);
             
             projetoAssociado=allProjetos.get(p);
             projetoAssociado.addPublicacao(this);
             
             System.out.println("Adicionado ao projeto com sucesso");
        }else{
             System.out.println("Não há projetos válidos");
        }
        
        
        
    }

    public ArrayList<Colaborador> getAutores() {
        return autores;
    }
    
 
    
    
    
}
