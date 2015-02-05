/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuladortemporeal2;

import javax.swing.JOptionPane;

/**
 * Relatório do escalonamento
     Número de trocas de contexto
     Número de trocas de contexto por preempção
     Percentual de utilização da CPU
     Tempo do primeiro escalonamento de cada processo
     Indicar se foi possível realizar o escalonamento
 */
public class Relatorio {
    
    private int trocas;
    private int preempcao;
    private double utilizacao;
    private int[] tempoPrimeiro;
    private char[] nomePrimeiro;
    private boolean escalonou;
    private int cont;
    
    public Relatorio(int qtdProcessos){
        tempoPrimeiro=new int[qtdProcessos];
        nomePrimeiro=new char[qtdProcessos]; 
        escalonou=true;
        cont=0;
        preempcao=0;
        trocas=0;
    }   
    
    public void ordenarVetoresTempoNome(){
        for (int i = 0; i < tempoPrimeiro.length; i++) {
            for (int j = i+1; j < tempoPrimeiro.length; j++) {
                if (nomePrimeiro[i]> nomePrimeiro[j]) {  
                    char aux = nomePrimeiro[i]; 
                    nomePrimeiro[i] = nomePrimeiro[j]; 
                    nomePrimeiro[j] = aux; 
                    int aux2 = tempoPrimeiro[i]; 
                    tempoPrimeiro[i] = tempoPrimeiro[j]; 
                    tempoPrimeiro[j] = aux2; 
                } 
            } 
        }
    }
        
    public void Exibir(){
        String escalona="";          
        escalona=escalonou?"E escalonavel":"Nao e escalonavel";
        String primeiros="";
        
        for (int j=0; j < tempoPrimeiro.length; j++) {
            primeiros+="| "+nomePrimeiro[j]+"="+tempoPrimeiro[j]+"\n";
                  
        }
        
        JOptionPane.showMessageDialog(null,
                  "__________RELATORIO__________\n"
                + "| "+escalona+"\n"
                + "| Qtd. de trocas: "+trocas+"\n"
                + "| Qtd. de preempcoes: "+preempcao+"\n"
                + "| Utilizacao da CPU: "+utilizacao*100+"%\n"
                + "| Primeiro Escalonamento:\n"
                + primeiros);   
    }
    
    public void addTroca(){
        trocas++;
    }
    
    public void addPreempcao(){
        preempcao++;
    }
    
    public void addPrimeiro(int tempo,char nome){
        tempoPrimeiro[cont]=tempo;
        nomePrimeiro[cont]=nome;
        cont++;
    }
    
    public int getTrocas() {
        return trocas;
    }

    public void setTrocas(int trocas) {
        this.trocas = trocas;
    }

    public int getPreempcao() {
        return preempcao;
    }

    public void setPreempcao(int preempcao) {
        this.preempcao = preempcao;
    }

    public double getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(double utilizacao) {
        this.utilizacao = utilizacao;
    }

    public int[] getPrimeiro() {
        return tempoPrimeiro;
    }

    public void setPrimeiro(int[] primeiro) {
        this.tempoPrimeiro = primeiro;
    }
    
    public boolean isEscalonou() {
        return escalonou;
    }

    public void setEscalonou(boolean escalonou) {
        this.escalonou = escalonou;
    }
    
    
}
