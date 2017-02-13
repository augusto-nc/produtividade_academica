/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producao_academica;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose_Augusto
 */
public class Main {

    public static Scanner sc;
    public static ArrayList<Colaborador> colaboradores;
    public static ArrayList<Projeto> projetos;
    public static ArrayList<ProducaoAcademica> producoesAcademicas;


    public static void iniciarTeste(){
        Professor p= new Professor("Eduardo Toledo","eduardo@ic.ufal.br");
        colaboradores.add(p);
        Publicacao publi= new Publicacao("Estudo Java","Conferencia 1","09/01/2010",new Colaborador[]{p});
        producoesAcademicas.add(publi);   
        Projeto proj= new Projeto("projeto Ic",System.currentTimeMillis(),System.currentTimeMillis()+86400000,"PIBIC","Estudar computação","Estudo da computação",colaboradores);
        projetos.add(proj);
        proj.addPublicacao(publi);
       
    }
    
    
    public static void main(String[] args) {
        colaboradores= new ArrayList<>();
        projetos= new ArrayList<>();
        producoesAcademicas= new ArrayList<>();
        iniciarTeste();
        sc= new Scanner(System.in);
        System.out.println("--------PRODUÇÃO ACADÉMICA-------");
        
        int r=-1;
         while(r!=0){        
             System.out.println("");
            System.out.println("1.Colaboradores");
            System.out.println("2.Projeto");   
            System.out.println("3.Producão Académica");   
            System.out.println("4.Relatório");        
            System.out.println("0.Sair"); 
            r =sc.nextInt();
            switch(r){
                case 1:
                    colaboradores();
                    break;
                 case 2:    
                    projeto();
                    break;
                  case 3:    
                    producaoAcademica();
                    break;   
                  case 4:
                      relatorio();
                      break;
                    
                
            }        
                    
        
        
       
         }
            
        }
        
        
      
    public static void projeto(){
          System.out.println("--PROJETOS--");
        int r=-1;
        while(r!=0){
            System.out.println("1.Adicionar");
            System.out.println("2.Editar");
            System.out.println("3.Remover");  
            System.out.println("4.Visualizar projeto");  
            System.out.println("5.Adicionar Publicacao");  
            System.out.println("0.Sair"); 
            r=sc.nextInt();  
            switch(r){
                case 1:
                    Projeto p= new Projeto(colaboradores);
                    p.setDataProjeto(sc);
                    projetos.add(p);
                    System.out.println("Adicionado com sucesso");
                    break;
                case 2:
                    Projeto p1= Projeto.getProjeto(projetos,sc);
                     if(p1!=null){
                        p1.setDataProjeto(sc);
                    }
                    break;
                case 3:
                    Projeto p2= Projeto.getProjeto(projetos, sc);
                     if(p2!=null){
                         projetos.set(p2.auxId, null);
                        System.out.println("Removido com sucesso");
                    }
                    break;
                  case 4:
                    Projeto p3= Projeto.getProjeto(projetos, sc);
                     if(p3!=null){
                         p3.imprimirProjeto();
                         System.out.println("Publicações: ");
                         p3.exibirPublicacoes();
                         System.out.println("----"
                                 + "");
                         System.out.println("");
                    }else{
                         System.out.println("Projeto não encontrado");
                     }
                    break;     
                  case 5:
                     Projeto p4= Projeto.getProjeto(projetos, sc);
                      if(p4!=null){
                         Publicacao publi= (Publicacao) ProducaoAcademica.getProducao(sc, producoesAcademicas, true, false);
                         if(publi!=null){
                            if(p4.getStatus()==Projeto.ANDAMENTO){
                              p4.addPublicacao(publi);
                             System.out.println("Publicação adicionada");
                            }else{
                                System.out.println("Projeto precisa estar em andamento");
                            } 
                        
                         } else{
                             
                             System.out.println("Projeto não encontrado");
                        }
                         
                    
                    }else{
                         System.out.println("Projeto não encontrado");
                     }

                    
            }    
        }
        
    }
    
    public static void colaboradores(){
        System.out.println("--COLABORADORES--");
        int r=-1;
        while(r!=0){
            System.out.println("1.Adicionar");
            System.out.println("2.Editar");
            System.out.println("3.Remover");     
            System.out.println("4.Visualizar colaborador");        
            System.out.println("0.Sair"); 
            r=sc.nextInt();  
            switch(r){
                case 1:
                    Colaborador c= Colaborador.getInstance(sc);
                    if(c!=null){
                        colaboradores.add(c);
                        c.id=colaboradores.size()-1;
                        System.out.println("Adicionado com sucesso");
                    }
                    break;
                case 2:
                    Colaborador c1= Colaborador.getColaborador(colaboradores, sc, null);
                     if(c1!=null){
                        c1.edit(sc);
                    }
                    break;
                case 3:
                    Colaborador c2= Colaborador.getColaborador(colaboradores, sc, null);
                     if(c2!=null){
                        colaboradores.set(c2.id, null);
                         System.out.println("Removido com sucesso");
                    }
                    break;
                    
                 case 4:
                    Colaborador c3= Colaborador.getColaborador(colaboradores, sc, null);
                     if(c3!=null){
                         System.err.println("");
                         c3.show();
                         System.out.println("Projetos: ");
                         TreeMap<Long,Projeto> map= Projeto.getProjetos(c3, projetos);
                         Set<Long> set=map.keySet();
                         for(Long k:set){
                             map.get(k).imprimirTitulo();
                         }
                         System.out.println("");

                         System.out.println("Produção Académica: ");
                         TreeMap<Long,ProducaoAcademica> mapAc= ProducaoAcademica.getProducoes(c3,producoesAcademicas);
                         Set<Long> set1=mapAc.keySet();

                         for(Long k:set1){
                             mapAc.get(k).imprimir();
                             System.out.println("");
                         }
                         
                    }
                    break;  
                                           
            }
                
        }
      
    }

    private static void producaoAcademica(){
              System.out.println("--PRODUÇÃO ACADÉMICA--");
        int r=-1;
        while(r!=0){
            System.out.println("1.Nova publicação");
            System.out.println("2.Nova Orientação");
            System.out.println("3.Remover");
            System.out.println("4.Adiconar autores à publicação");
            System.out.println("5.Adiconar orientados à orientação");
            System.out.println("0.Sair"); 
            r=sc.nextInt();  
            switch(r){
                case 1:
                    Publicacao p;
            try {
                p = Publicacao.getInstance(sc);
                producoesAcademicas.add(p);
                System.out.println("Publicação criada");
                System.out.println("");
                System.out.println("Adicionar autores:");
                p.adicionarAutor(sc, colaboradores);
            } catch (Exception ex) {
                   System.out.println("Erro ao criar publicação");
            }
                    break;
                case 2:
            
                try {
                    Orientacao o= Orientacao.getInstance(sc, colaboradores);
                    System.out.println("Orientação criada com sucesso");
                    System.out.println("Adicionar orientados: ");
                    o.adicionarOrientados(sc, colaboradores);
                   
                } catch (Exception ex) {
                   System.out.println("Erro ao criar orientação");
                }
                break;
                
                case 3:
                   ProducaoAcademica c= ProducaoAcademica.getProducao(sc, producoesAcademicas, true, true); 
                   if(c!=null){
                       producoesAcademicas.remove(c);
                        System.out.println("Produção Removida com sucesso");
                   }else{
                       System.out.println("Produção inválida");
                   }
                   break;
                case 4:
                    Publicacao c1= (Publicacao) ProducaoAcademica.getProducao(sc, producoesAcademicas, true, false); 
                    if(c1!=null){
                        c1.adicionarAutor(sc, colaboradores);
                    }else{
                        System.out.println("Publicacão inválida");
                    }
                    break;
                    
                case 5:
                    Orientacao c2= (Orientacao) ProducaoAcademica.getProducao(sc, producoesAcademicas, false, true); 
                    if(c2!=null){
                        c2.adicionarOrientados(sc, colaboradores);
                    }else{
                        System.out.println("Orientação inválida ");
                    }
                    break;
                        
            }
            
    }
}

    private static void relatorio() {
        int size=0;
        for(Colaborador c: colaboradores){
            if(c!=null){
                size++;
            }
        }
        System.out.println("Quantidade de colaboradores: "+size);
        int sizeAndamento=0;
        int sizeElaboracao=0;
        int sizeConcluido=0;

        
         for(Projeto p: projetos){
            if(p!=null){
                if(p.getStatus()==Projeto.ANDAMENTO){
                    sizeAndamento++;
                }else if(p.getStatus()==Projeto.CONCLUIDO){
                    sizeConcluido++;
                }else if(p.getStatus()==Projeto.ELABORACAO){
                    sizeElaboracao++;
                }
                
            }
        }
        System.out.println("Quantidade de Projetos (Elaboração): "+sizeElaboracao);
        System.out.println("Quantidade de Projetos (Andamento): "+sizeAndamento);
        System.out.println("Quantidade de Projetos (Concluído): "+sizeConcluido);
        System.out.println("Total de Projetos: "+(sizeConcluido + sizeElaboracao+sizeAndamento));
         int sizePublic=0;
         int sizeOrient=0;

         for(ProducaoAcademica p: producoesAcademicas){
            if(p!=null){
                if(p instanceof Publicacao){
                    sizePublic++;
                }else if(p instanceof Orientacao){
                    sizeOrient++;
                }
                
            }
        }
        System.out.println("Quantidade de publicações: "+sizePublic);
        System.out.println("Quantidade de orientações: "+sizeOrient);


         
         
    }
}
