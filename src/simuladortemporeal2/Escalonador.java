/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuladortemporeal2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy
 */
public class Escalonador {
    char tipo;
    int tempo;
    boolean ordena=false;
    Relatorio relatorio;
    int preemp[]=new int[20];
    int contPreemp=0;
    
    public Escalonador(char t){
        tipo=t;
        tempo=0;
    }
    
    public void Escalonar(Processo[] processos,int time){
        relatorio=new Relatorio(processos.length);
        char pAnterior=' ';
        double utilizacao=0;
        
        Processo[] pTodos=new Processo[processos.length];
        System.out.print("Tempo\t");
        for (int x=0;x< pTodos.length;x++){
            utilizacao+=((double)processos[x].getCusto()/(double)processos[x].getPeriodo());
            pTodos[x]=new Processo(processos[x].getCusto(),processos[x].getPeriodo(),processos[x].getNome(),processos[x].getDeadLine());
            System.out.print(pTodos[x].getNome()+"\t");
        }
        relatorio.setUtilizacao(utilizacao);
       /* for(int x=0;x<pTodos.length;x++){
                JOptionPane.showInputDialog("Todos"+processos[x].getNome());
            }*/
        System.out.println("Em Execução");
        processos=Ordenar(processos);
        do{            
            ordena=false;
            processos=VerificaTempo(processos);
            
            processos=VerificaPeriodo(processos);     
            
           /*for(int x=0;x<processos.length;x++){
                JOptionPane.showInputDialog("Processos"+processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
            }/*
            for(int x=0;x<pTodos.length;x++){
                JOptionPane.showInputDialog("Todos"+processos[x].getNome());
            }*/
                       
            if(ordena)processos=Ordenar(processos);
            
            char pAtual=NomePrimeiroProcessoNaoTerminado(processos);
            
            if(tempo==0){
                pAnterior=pAtual;
            }
            
            System.out.print(tempo+"-"+(tempo+1)+"\t");
             
            for (int x=0;x<pTodos.length;x++){
                if(pTodos[x].getNome()==pAtual){
                    System.out.print("#\t");
                }else{System.out.print("|\t");}
                
            }
            System.out.println(pAtual);
            
            if(pAnterior!=pAtual && pAtual!='-'){
                relatorio.addTroca();
            }
            
            for(int x=0;x<processos.length;x++){
                //JOptionPane.showInputDialog(processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
                if(processos[x].getNome()==pAtual){
                    processos[x].IncrementaTempo();  
                }
            }
            
           
            
            for(int x=0;x<processos.length;x++){
               
                if(processos[x].getNome()==pAnterior && processos[x].getNome()!=pAtual && !processos[x].getAcabou() && processos[x].getTempo()!=0){
                    relatorio.addPreempcao();
                    preemp[contPreemp]=tempo;contPreemp++;
                }
            }
            
            pAnterior=pAtual;
        
            tempo++;
        
        }while(tempo<time);
        
        processos=VerificaTempo(processos);
        processos=VerificaPeriodo(processos);     
        addProcessosNaoTerminadosRelatorio(processos);
        for(int l=0;l<contPreemp;l++){System.out.print(preemp[l]+"-");}
        relatorio.Exibir();
        
    }
    public void addProcessosNaoTerminadosRelatorio(Processo[] processos){
        for (int x=0;x<processos.length;x++){
            
            if(!processos[x].IsFezPrimeiro()){
                relatorio.addPrimeiro(0,processos[x].getNome());
            }
            
            ordena=true;               
                     
        }
    }
    public char NomePrimeiroProcessoNaoTerminado(Processo[] p){
        for (int x=0;x< p.length;x++) {
            if(!p[x].getAcabou()){
                return p[x].getNome();
            }
        }
        return '-';
    }
    public boolean Terminou(Processo[] p){
        for (int x=0;x< p.length;x++) {
            if(p[x].getTempo()!=0)return false;
        }
        return true;
    }
    public Processo[] VerificaTempo(Processo[] processos){
        
        for (int x=0;x<processos.length;x++){
            if(processos[x].getTempo()==processos[x].getCusto()){
                processos[x].ZeraTempo();
                processos[x].Acabou();
                if(!processos[x].IsFezPrimeiro()){
                    processos[x].FezPrimeiro();
                    relatorio.addPrimeiro(tempo,processos[x].getNome());
                }
                
                ordena=true;               
            }            
        }
        return processos;
    }
    public Processo[] VerificaPeriodo(Processo[] processos){
        for (int x=0;x<processos.length;x++){
            
            if((tempo-processos[x].getDeadLine())%processos[x].getPeriodo()==0 && tipo=='d'){
                if(!processos[x].getAcabou()){
                    if(tempo!=0){
                        relatorio.setEscalonou(false);
                        JOptionPane.showMessageDialog(null,"NAO FOI POSSIVEL ESCALONAR "+processos[x].getNome()+" Tempo:"+tempo);
                    }
                }
            }
            
            if(tempo%processos[x].getPeriodo()==0){
                if(!processos[x].getAcabou()){
                    if(tempo!=0){
                        relatorio.setEscalonou(false);
                        JOptionPane.showMessageDialog(null,"NAO FOI POSSIVEL ESCALONAR "+processos[x].getNome()+" Tempo:"+tempo);
                    }
                }
                ordena=true;
                processos[x].ZeraTempo();
                processos[x].Recomecou();
            }
        }
        return processos;
    }
    public Processo[] Ordenar(Processo[] p){
        if(tipo=='m'){
            
            for (int i = 0; i < p.length; i++) {
                for (int j = i+1; j < p.length; j++) {
                    if (p[i].getPeriodo() > p[j].getPeriodo()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }/*
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < p.length; j++) { 
                    if (p[i].getPeriodo() < p[j].getPeriodo()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }*/
        }else if(tipo=='e'){
            for (int i = 0; i < p.length; i++) {
                for (int j = i+1; j < p.length; j++) {
                    if (((tempo/p[i].getPeriodo())+1)*p[i].getPeriodo() > ((tempo/p[j].getPeriodo())+1)*p[j].getPeriodo()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }
            /*for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < p.length; j++) { 
                    if (p[i].getPeriodo()-p[i].getTempo() < p[j].getPeriodo()-p[j].getTempo()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }*/
        }else if(tipo=='d'){
             for (int i = 0; i < p.length; i++) {
                for (int j = i+1; j < p.length; j++) {
                    if (p[i].getDeadLine() > p[j].getDeadLine()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }
             /*
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < p.length; j++) { 
                    if (p[i].getDeadLine() < p[j].getDeadLine()) {  
                        Processo aux = p[i]; 
                        p[i] = p[j]; 
                        p[j] = aux; 
                    } 
                } 
            }*/
        }
        return p;
    }
    
}
