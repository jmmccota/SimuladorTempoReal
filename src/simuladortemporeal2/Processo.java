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

    public Processo(int custo, int periodo, char nome, int deadLine) {
        this.custo = custo;
        this.deadLine = deadLine;
        this.periodo = periodo;
        this.nome = nome;
        contTempo = 0;
        acabou = false;
        fezPrimeiro = false;
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
