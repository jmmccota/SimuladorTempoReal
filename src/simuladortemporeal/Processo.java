/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal;

import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class Processo implements Comparable<Processo>{
    
    private int num,tempoExec,periodo,deadline,qualRodada=0,executado;
    ArrayList<Integer> periodos=new ArrayList<Integer>();
    ArrayList<Integer> deads=new ArrayList<Integer>();

    public Processo(int numero, int tempo, int peri, int dead) {
        num=numero;
        tempoExec=tempo;
        periodo=peri;
        deadline=dead;
        executado=0;
    }
    
    public void setNum(int numero){
        num=numero;
    }
    public void setTempo(int numero){
        tempoExec=numero;
    }
    public void setPeriodo(int numero){
        periodo=numero;
    }
    public void setDead(int numero){
        deadline=numero;
    }
    public int getNum(){
        return num;
    }
    public void setRodada(){
        qualRodada++;
    }
    public int getExe(){
        return executado;
    }
    public void setExe(){
        executado++;
    }
    public int getRodada(){
        return qualRodada;
    }
    public int getTempo(){
        return tempoExec;
    }
    public int getPeriodo(){
        return periodo;
    }
    public int getDead(){
        return deadline;
    }

    public void preenchePeriodos(int tempoMaximo) {
        int tt=periodo;
        while(tt<tempoMaximo){
            periodos.add(tt);
            tt+=periodo;
        }
        periodos.add(tt);
        preencheDeads();
        
    }

    public void preencheDeads() {
        int dd=deadline;
        for(int i=0;i<periodos.size();i++){
            deads.add(dd);
            dd+=periodo;
        }
        deads.add(dd);
    }

    @Override
    public int compareTo(Processo o) {
        if(this.periodo < o.getPeriodo()){
            return -1;
        } else if(this.periodo > o.getPeriodo()) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
