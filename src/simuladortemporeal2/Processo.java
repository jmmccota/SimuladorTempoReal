package simuladortemporeal2;

import java.util.ArrayList;

public class Processo {

    private int custo;
    private int deadLine;
    private int periodo;
    private int contTempo;
    private char nome;
    private boolean acabou;
    private boolean fezPrimeiro;
    private ArrayList<String> imprimir;

    public Processo(int custo, int periodo, char nome, int deadLine) {
        this.custo = custo;
        this.deadLine = deadLine;
        this.periodo = periodo;
        this.nome = nome;
        contTempo = 0;
        acabou = false;
        fezPrimeiro = false;
        imprimir = new ArrayList<String>();
    }
    public ArrayList<String> getImprimir(){
        return imprimir;
    }
    public void setImprimir(String s){
        imprimir.add(s);
    }
    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getContTempo() {
        return contTempo;
    }

    public void setContTempo(int contTempo) {
        this.contTempo = contTempo;
    }

    public char getNome() {
        return nome;
    }

    public void setNome(char nome) {
        this.nome = nome;
    }

    public boolean isAcabou() {
        return acabou;
    }

    public void setAcabou(boolean acabou) {
        this.acabou = acabou;
    }

    public boolean isFezPrimeiro() {
        return fezPrimeiro;
    }

    public void setFezPrimeiro(boolean fezPrimeiro) {
        this.fezPrimeiro = fezPrimeiro;
    }

}
