/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy
 */
public class Escalonador {

    int tipo;
    int tempo;
    boolean ordena = false;
    Relatorio relatorio;
    int preemp[] = new int[20];
    int contPreemp = 0;

    public Escalonador(int t) {
        tipo = t;
        tempo = 0;
    }

    public void Escalonar(ArrayList<Processo> processos, int time) {
        relatorio = new Relatorio(processos.size());
        char pAnterior = ' ';
        double utilizacao = 0;

        ArrayList<Processo> pTodos = new ArrayList<Processo>();
        System.out.print("Tempo\t");
        for (Processo p : processos) {
            utilizacao += ((double) p.getCusto() / (double) p.getPeriodo());
            pTodos.add(new Processo(p.getCusto(), p.getPeriodo(), p.getNome(), p.getDeadLine()));
            System.out.print("P"+p.getNome() + "\t");
        }
        //System.out.print("Utilizacao :"+utilizacao);
        relatorio.setUtilizacao(utilizacao);
        /* for(int x=0;x<pTodos.length;x++){
         JOptionPane.showInputDialog("Todos"+processos[x].getNome());
         }*/
        System.out.println("Em Execução");
        processos = Ordenar(processos);
        do {
            ordena = false;
            processos = VerificaTempo(processos);

            processos = VerificaPeriodo(processos);

            /*for(int x=0;x<processos.length;x++){
             JOptionPane.showInputDialog("Processos"+processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
             }/*
             for(int x=0;x<pTodos.length;x++){
             JOptionPane.showInputDialog("Todos"+processos[x].getNome());
             }*/
            if (ordena) {
                processos = Ordenar(processos);
            }

            char pAtual = NomePrimeiroProcessoNaoTerminado(processos);

            if (tempo == 0) {
                pAnterior = pAtual;
            }

            System.out.print(tempo + "-" + (tempo + 1) + "\t");

            for (Processo pTodo : pTodos) {
                if (pTodo.getNome() == pAtual) {
                    System.out.print("#\t");
                } else {
                    System.out.print("|\t");
                }
            }
            System.out.println("P"+pAtual);

            if (pAnterior != pAtual && pAtual != '-') {
                relatorio.addTroca();
            }

            for (Processo processo : processos) {
                //JOptionPane.showInputDialog(processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
                if (processo.getNome() == pAtual) {
                    processo.setContTempo(1 + processo.getContTempo());
                }
            }

            for (Processo processo : processos) {
                if (processo.getNome() == pAnterior && processo.getNome() != pAtual && !processo.isAcabou() && processo.getContTempo() != 0) {
                    relatorio.addPreempcao();
                    preemp[contPreemp] = tempo;
                    contPreemp++;
                }
            }

            pAnterior = pAtual;

            tempo++;

        } while (tempo < time);

        processos = VerificaTempo(processos);
        processos = VerificaPeriodo(processos);
        addProcessosNaoTerminadosRelatorio(processos);
        /*for (int l = 0; l < contPreemp; l++) {
            System.out.print(preemp[l] + "-");
        }*/
        relatorio.Exibir();

    }

    public void addProcessosNaoTerminadosRelatorio(ArrayList<Processo> processos) {
        for (Processo p : processos) {
            if (!p.isFezPrimeiro()) {
                relatorio.addPrimeiro(0, p.getNome());
            }
            ordena = true;
        }
    }

    public char NomePrimeiroProcessoNaoTerminado(ArrayList<Processo> p) {
        for (Processo p1 : p) {
            if (!p1.isAcabou()) {
                return p1.getNome();
            }
        }
        return '-';
    }

    public boolean Terminou(ArrayList<Processo> processos) {
        for (Processo p : processos) {
            if (p.getContTempo() != 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Processo> VerificaTempo(ArrayList<Processo> processos) {

        for (Processo p : processos) {
            if (p.getContTempo() == p.getCusto()) {
                p.setContTempo(0);
                p.setAcabou(true);
                if (!p.isFezPrimeiro()) {
                    p.setFezPrimeiro(true);
                    relatorio.addPrimeiro(tempo, p.getNome());
                }
                ordena = true;
            }
        }
        return processos;
    }

    public ArrayList<Processo> VerificaPeriodo(ArrayList<Processo> processos) {
        for (Processo p : processos) {
            if ((tempo - p.getDeadLine()) % p.getPeriodo() == 0 && tipo == 3) {
                if (!p.isAcabou()) {
                    if (tempo != 0) {
                        relatorio.setEscalonou(false);
                        System.out.println("ERRO!!! IMPOSSÍVEL ESCALONAR P" + p.getNome() + ". Tempo:" + tempo);
                    }
                }
            }

            if (tempo % p.getPeriodo() == 0) {
                if (!p.isAcabou()) {
                    if (tempo != 0) {
                        relatorio.setEscalonou(false);
                        System.out.println("ERRO!!! IMPOSSÍVEL ESCALONAR P" + p.getNome() + " Tempo:" + tempo);
                    }
                }
                ordena = true;
                p.setContTempo(0);
                p.setAcabou(false);
            }
        }
        return processos;
    }

    public ArrayList<Processo> Ordenar(ArrayList<Processo> p) {
        if (tipo ==1) {
            Collections.sort(p, new Comparator<Processo>() {
                @Override
                public int compare(Processo o1, Processo o2) {
                    if (o1.getPeriodo() < o2.getPeriodo()) {
                        return -1;
                    } else if (o1.getPeriodo() > o2.getPeriodo()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
//            for (int i = 0; i < p.size(); i++) {
//                for (int j = i + 1; j < p.size(); j++) {
//                    if (p.get(i).getPeriodo() > p.get(j).getPeriodo()) {
//                        Processo aux = p.get(i);
//                        p.get(i) = p.get(j);
//                        p.get(j) = aux;
//                    }
//                }
//            }

        } else if (tipo ==2) {
            Collections.sort(p, new Comparator<Processo>() {
                @Override
                public int compare(Processo o1, Processo o2) {
                    if (((tempo / o1.getPeriodo()) + 1) * o1.getPeriodo() < ((tempo / o2.getPeriodo()) + 1) * o2.getPeriodo()) {
                        return -1;
                    } else if (((tempo / o1.getPeriodo()) + 1) * o1.getPeriodo() > ((tempo / o2.getPeriodo()) + 1) * o2.getPeriodo()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
//            for (int i = 0; i < p.size(); i++) {
//                for (int j = i + 1; j < p.size(); j++) {
//                    if (((tempo / p.get(i).getPeriodo()) + 1) * p.get(i).getPeriodo() > ((tempo / p.get(j).getPeriodo()) + 1) * p.get(j).getPeriodo()) {
//                        Processo aux = p.get(i);
//                        p.get(i) = p.get(j);
//                        p.get(j) = aux;
//                    }
//                }
//            }

        } else if (tipo ==3) {

            Collections.sort(p, new Comparator<Processo>() {
                @Override
                public int compare(Processo o1, Processo o2) {
                    if (o1.getDeadLine() < o2.getDeadLine()) {
                        return -1;
                    } else if (o1.getDeadLine() > o2.getDeadLine()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
//            for (int i = 0; i < p.size(); i++) {
//                for (int j = i + 1; j < p.size(); j++) {
//                    if (p.get(i).getDeadLine() > p.get(j).getDeadLine()) {
//                        Processo aux = p.get(i);
//                        p.get(i) = p.get(j);
//                        p.get(j) = aux;
//                    }
//                }
//            }

        }
        return p;
    }

}
