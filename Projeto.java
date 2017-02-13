/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Jose_Augusto
 */
public class Projeto {
   public static final int ELABORACAO=0;
   public static final int ANDAMENTO=1;
   public static final int CONCLUIDO=2;

   public static String[] STATUS= new String[]{"ELABORACAO","ANDAMENTO","CONCLUIDO"};

   
   public static final String INDEFINIDO="[INDEFINIDO]";
    
   private String titulo;
   private long dataInicio;
   private long dataTermino;
   private String agenciaFinanciadora;
   private double valorFinanciado=-1;
   private String objetivo;
   private String descricao;
   ArrayList<Integer> participantes;
   ArrayList<Colaborador> allColaboradores;
   ArrayList<Publicacao> publibicacoes;
   public int auxId;
   
   private int status=ELABORACAO;
   
   private boolean isAllSet(){
      return titulo!=null && dataInicio!=0 && dataTermino!=0 &&
          agenciaFinanciadora!=null && objetivo != null && descricao !=null && containProfessor();
   }
   
   public int getStatus(){
       if(!isAllSet() ){
           return ELABORACAO;
       }
       return status;
   }

    public Projeto(String titulo, long dataInicio, long dataTermino, String agenciaFinanciadora, String objetivo, String descricao, ArrayList<Colaborador> allColaboradores) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.allColaboradores = allColaboradores;
        status=ANDAMENTO;
        participantes=new ArrayList<>();
        publibicacoes= new ArrayList<>(); 
    }
   
   

    public Projeto(ArrayList<Colaborador> allColaboradores) {
        participantes=new ArrayList<>();
        publibicacoes= new ArrayList<>();
        this.allColaboradores=allColaboradores;
        
    }
    public void exibirParticipantes(){
        for(int k: participantes){
            Colaborador c=allColaboradores.get(k);
            if(c!=null)
                System.out.println(c.getTipoString()+": "+c.getNome());
            }
    }
    
    public void adicionarParticipante(Scanner sc){
        
        Colaborador c=Colaborador.getColaborador(allColaboradores, sc, null);
        if(c!=null){
                participantes.add(c.id);
                exibirParticipantes();
        }else{
            System.out.println("colaborador Inválido");
        }

    }

   
    
    public void imprimirProjeto(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("EEEE dd/MM/YYYY",Locale.getDefault());
        
        System.out.println("Titulo: "+(titulo!=null ? titulo : INDEFINIDO) );
        System.out.println("Objetivo "+(objetivo!=null ? objetivo : INDEFINIDO) );
        System.out.println("Descricao: "+(descricao!=null ?descricao : INDEFINIDO) );
        System.out.println("Participantes: "+(participantes.size()==0 ? "Não possui" : "") );
        exibirParticipantes();
        System.out.println("----");
        System.out.println("Status: "+STATUS[getStatus()]);
        System.out.println("Data de início: "+ (dataInicio!=0 ? dateFormat.format(new Date(dataInicio))  : INDEFINIDO ));
        System.out.println("Data de término: "+ (dataTermino!=0 ? dateFormat.format(new Date(dataTermino))  : INDEFINIDO ));
        System.out.println("Financiadora: "+ (agenciaFinanciadora!=null ? agenciaFinanciadora  : INDEFINIDO ));
        System.out.println("Valor Financiado: "+ (valorFinanciado!=-1 ? String.format("R$: %.2f", valorFinanciado) : INDEFINIDO ));

    }
    
    public void exibirPublicacoes(){
        TreeMap<Long,Publicacao> tree= new TreeMap<>();
        
        
        for(Publicacao p:publibicacoes){
                tree.put(p.dataPublicacao, p);            
        }
        Set<Long> set= tree.keySet();
        for(Long l:set){
            tree.get(l).imprimir();
            System.out.println("");
        }
        
        
      
    }
    

    @Override
    public String toString() {
        return  titulo ;
    }
    
    
      public static Projeto getProjeto(ArrayList<Projeto> projetos,Scanner sc){
            System.out.println("Projetos: ");
     
        for(int i=0;i<projetos.size();i++){
            if(projetos.get(i)!=null){
                  System.out.println("id: "+i);
                  projetos.get(i).imprimirTitulo();
                  System.out.println("");
            }         
        }
        System.out.print("Projeto:(id) ");
        int k=sc.nextInt();
        try{
             projetos.get(k).auxId=k;
              return projetos.get(k);
        }catch(IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
    }
    
    
   public static TreeMap<Long,Projeto> getProjetos(Colaborador c,ArrayList<Projeto> projetos){
       TreeMap<Long,Projeto> projetosC= new TreeMap<Long, Projeto>();
       for(Projeto j:projetos){
           if(j.containColaborador(c)){
               projetosC.put(j.dataInicio,j);
           }
       }
       return projetosC;
   }   
      
    
    public void setDataProjeto(Scanner sc){
        
        int r=-1;
        while(r!=0){
        imprimirProjeto();
        System.out.println("");
        System.out.println("Escolha o item à ser definido: ");
        System.out.println("1- Titulo");
        System.out.println("2- Objetivo");
        System.out.println("3- Descricao");
        System.out.println("4- Participantes");
        System.out.println("5- Status");
        System.out.println("6- Data de inicio");
        System.out.println("7- Data de Término");
        System.out.println("8- Agencia Financiadora");
        System.out.println("9- Valor Financiado");
        System.out.println("0- Voltar");
        r= sc.nextInt();
        
        switch(r){
            case 1:
                System.out.print("Novo titulo: ");    
                sc.nextLine();
                titulo=sc.nextLine();
                break;
            case 2:
                System.out.print("Novo Objetivo: ");    
                sc.nextLine();
                objetivo=sc.nextLine();
                break;  
             case 3:
                System.out.print("Nova descricao: ");   
                sc.nextLine();
                descricao=sc.nextLine();
                break;
             case 4:
                System.out.print("Participantes: ");   
                exibirParticipantes();
                
                System.out.print("1- Adicionar Participante:");   
                System.out.print("2- Remover Participante :");   

                r=sc.nextInt();
                if(r==1){
                  adicionarParticipante(sc);  
                    
                }else if(r==2){
                    System.out.print("nome: ");
                    sc.nextLine();
                    String nome=sc.nextLine();
                    removerParticipante(nome);  
                }
                
                
                break;   
              case 5:
                if(!isAllSet()){
                    System.out.println("Conclua o cadastro do projeto");
                }else if(publibicacoes.size()==0 && status==ANDAMENTO){
                      System.out.println("Adicione pelo menos uma publicação para concluir o projeto");
                }else{{
                   for(int i=0;i<STATUS.length;i++){
                       System.out.println(i+"- "+STATUS[i]);
                      
                   }
                   status=sc.nextInt();
                }  
                }
                
                break;   
               case 6:
                    System.out.println("Data de inicio: (dd/MM/yyyy) :");
                    sc.nextLine();
                    String data= sc.nextLine();
                    try{
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        dataInicio= f.parse(data).getTime();
                    }catch(ParseException e){
                        System.out.println("Data Inválida");
                    }
                    break;   
                    
               case 7:
                    System.out.println("Data de Término: (dd/MM/yyyy) :");
                    sc.nextLine();
                    String dataF= sc.nextLine();
                    try{
                        SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy");
                        dataTermino= f.parse(dataF).getTime();
                    }catch(ParseException e){
                        System.out.println("Data Inválida");
                    }
                    break;  
                    
               case 8:
                    System.out.print("Nova Agencia Financiadora: ");   
                    sc.nextLine();
                    agenciaFinanciadora=sc.nextLine();
                    break;
               case 9:
                    System.out.print("Valor financiado R$: ");    
                    valorFinanciado=sc.nextDouble();
                    break;    
                   
        }
                
                
            
            
            
        }

        
         
    }
    
    
    private void removerParticipante(String nome){
        for(Integer k:participantes){
            Colaborador c= allColaboradores.get(k);
            if(c!=null && c.equals(new Colaborador(nome))){
                participantes.remove(k);
                System.out.println("Removido com sucesso");
                return;
            }
            
            
        }
      
           System.out.println("Nome: "+nome+ "Não encontrado");
        
    }

    private boolean containProfessor() {
            for(int k:participantes){
                Colaborador c= allColaboradores.get(k);
                if(c!=null){
                    if(c.getTipo()==Professor.PROFESSOR){
                        return true;
                    }
                }
            }
       return false; 
    }

    void addPublicacao(Publicacao p) {
        ArrayList<Colaborador> autores= p.getAutores();
        for(Colaborador c: autores){
            if(!participantes.contains(c.id))
                participantes.add(c.id);
        }
        
        
        publibicacoes.add(p);
    }

    void removePublibicacao(Publicacao aThis) {
        publibicacoes.remove(aThis);
    
    }

    private boolean containColaborador(Colaborador c) {
           return participantes.contains(c.id);
    }

    public void imprimirTitulo() {
          
                  System.out.println("Titulo: "+(titulo!=null ? titulo : INDEFINIDO) );


    }
    
  
        

}
