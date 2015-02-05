package simuladortemporeal2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sammy
 */
public class Processo {
    private int custo;
    private int deadLine;
    private int periodo;
    private int contTempo;
    private char nome;
    private boolean acabou;
    private boolean fezPrimeiro;

   /* public Processo(int c,int p,String n){
        custo=c;
        periodo=p;
        contTempo=0;
    }*/
    public Processo(int c,int p,char n,int d){
        custo=c;
        periodo=p;
        nome=n;
        deadLine=d;
        contTempo=0;
        acabou=false;
        fezPrimeiro=false;
    }

    public boolean IsFezPrimeiro() {
        return fezPrimeiro;
    }

    public void FezPrimeiro() {
        this.fezPrimeiro = true;
    }
    
    public void Acabou(){
        acabou=true;
    }
    public void Recomecou(){
        acabou=false;
    }
    public void IncrementaTempo(){
        contTempo++;
    }
    public void ZeraTempo(){
        contTempo=0;
    }
    public int getCusto(){
        return custo;
    }
    public int getPeriodo(){
        return periodo;
    }
    public int getDeadLine(){
        return deadLine;
    }
    public int getTempo(){
        return contTempo;
    }
    public char getNome(){
        return nome;
    }
    public boolean getAcabou(){
        //if(custo==contTempo)acabou=true;
        return acabou;
    }
}
