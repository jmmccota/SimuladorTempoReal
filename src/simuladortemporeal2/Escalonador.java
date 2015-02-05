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

    public void calculateEscalonish(ArrayList<Processo> processos, int time) {
        relatorio = new Relatorio(processos.size());
        char pAnterior = ' ';
        double utilizacao = 0;

        ArrayList<Processo> pTodos = new ArrayList<>();
        System.out.print("Tempo\t");
        for (Processo p : processos) {
            utilizacao += ((double) p.getCusto() / (double) p.getPeriodo());
            pTodos.add(new Processo(p.getCusto(), p.getPeriodo(), p.getNome(), p.getDeadLine()));
            System.out.print("P" + p.getNome() + "\t");
        }
        //System.out.print("Utilizacao :"+utilizacao);
        relatorio.setUtilizacao(utilizacao);
        /* for(int x=0;x<pTodos.length;x++){
         JOptionPane.showInputDialog("Todos"+processos[x].getNome());
         }*/
        System.out.println("Em Execução");
        processos = sortner(processos);
        do {
            ordena = false;
            processos = timeVerification(processos);

            processos = periodVerification(processos);

            /*for(int x=0;x<processos.length;x++){
             JOptionPane.showInputDialog("Processos"+processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
             }/*
             for(int x=0;x<pTodos.length;x++){
             JOptionPane.showInputDialog("Todos"+processos[x].getNome());
             }*/
            if (ordena) {
                processos = sortner(processos);
            }

            char pAtual = proccessNotEndedName(processos);

            if (tempo == 0) {
                pAnterior = pAtual;
            }

            System.out.print(tempo + "-" + (tempo + 1) + "\t");

            pTodos.stream().forEach((pTodo) -> {
                if (pTodo.getNome() == pAtual) {
                    System.out.print("0\t");
                    pTodo.setImprimir("*");
                } else {
                    System.out.print("|\t");
                    pTodo.setImprimir("-");
                }
            });
            System.out.println("P" + pAtual);

            if (pAnterior != pAtual && pAtual != '-') {
                relatorio.addChange();
            }

            for (Processo processo : processos) {
                //JOptionPane.showInputDialog(processos[x].getNome()+"/"+processos[x].getTempo()+"/"+processos[x].getAcabou());
                if (processo.getNome() == pAtual) {
                    processo.setContTempo(1 + processo.getContTempo());
                }
            }

            for (Processo processo : processos) {
                if (processo.getNome() == pAnterior && processo.getNome() != pAtual && !processo.isAcabou() && processo.getContTempo() != 0) {
                    relatorio.addPreemp();
                    preemp[contPreemp] = tempo;
                    contPreemp++;
                }
            }

            pAnterior = pAtual;

            tempo++;

        } while (tempo < time);

        processos = timeVerification(processos);
        processos = periodVerification(processos);
        addNotEndedProccess(processos);
        /*for (int l = 0; l < contPreemp; l++) {
         System.out.print(preemp[l] + "-");
         }*/
        for (int xx = 0; xx < processos.size(); xx++) {
            //linha120
            System.out.println(" ");
            System.out.println("P" + pTodos.get(xx).getNome() + ": ");
            for (int jj = 0; jj < pTodos.get(xx).getImprimir().size(); jj++) {
                System.out.print(pTodos.get(xx).getImprimir().get(jj)+" ");
            }
            
        }
        relatorio.showResult();

    }

    public void addNotEndedProccess(ArrayList<Processo> processos) {
        processos.stream().map((p) -> {
            if (!p.isFezPrimeiro()) {
                relatorio.addFirst(0, p.getNome());
            }
            return p;
        }).forEach((_item) -> {
            ordena = true;
        });
    }

    public char proccessNotEndedName(ArrayList<Processo> p) {
        for (Processo p1 : p) {
            if (!p1.isAcabou()) {
                return p1.getNome();
            }
        }
        return '-';
    }

    public boolean isEnded(ArrayList<Processo> processos) {
        if (!processos.stream().noneMatch((p) -> (p.getContTempo() != 0))) {
            return false;
        }
        return true;
    }

    public ArrayList<Processo> timeVerification(ArrayList<Processo> processos) {

        processos.stream().filter((p) -> (p.getContTempo() == p.getCusto())).map((p) -> {
            p.setContTempo(0);
            return p;
        }).map((p) -> {
            p.setAcabou(true);
            return p;
        }).map((p) -> {
            if (!p.isFezPrimeiro()) {
                p.setFezPrimeiro(true);
                relatorio.addFirst(tempo, p.getNome());
            }
            return p;
        }).forEach((_item) -> {
            ordena = true;
        });
        return processos;
    }

    public ArrayList<Processo> periodVerification(ArrayList<Processo> processos) {
        processos.stream().map((p) -> {
            if ((tempo - p.getDeadLine()) % p.getPeriodo() == 0 && tipo == 3) {
                if (!p.isAcabou()) {
                    if (tempo != 0) {
                        relatorio.setEscalonou(false);
                        System.out.println("ERRO!!! IMPOSSÍVEL ESCALONAR P" + p.getNome() + ". Tempo:" + tempo);
                    }
                }
            }
            return p;
        }).filter((p) -> (tempo % p.getPeriodo() == 0)).map((p) -> {
            if (!p.isAcabou()) {
                if (tempo != 0) {
                    relatorio.setEscalonou(false);
                    System.out.println("ERRO!!! IMPOSSÍVEL ESCALONAR P" + p.getNome() + " Tempo:" + tempo);
                }
            }
            return p;
        }).map((p) -> {
            ordena = true;
            return p;
        }).map((p) -> {
            p.setContTempo(0);
            return p;
        }).forEach((p) -> {
            p.setAcabou(false);
        });
        return processos;
    }

    public ArrayList<Processo> sortner(ArrayList<Processo> p) {
        if (tipo == 1) {
            Collections.sort(p, (Processo o1, Processo o2) -> {
                if (o1.getPeriodo() < o2.getPeriodo()) {
                    return -1;
                } else if (o1.getPeriodo() > o2.getPeriodo()) {
                    return 1;
                } else {
                    return 0;
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

        } else if (tipo == 2) {
            Collections.sort(p, (Processo o1, Processo o2) -> {
                if (((tempo / o1.getPeriodo()) + 1) * o1.getPeriodo() < ((tempo / o2.getPeriodo()) + 1) * o2.getPeriodo()) {
                    return -1;
                } else if (((tempo / o1.getPeriodo()) + 1) * o1.getPeriodo() > ((tempo / o2.getPeriodo()) + 1) * o2.getPeriodo()) {
                    return 1;
                } else {
                    return 0;
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

        } else if (tipo == 3) {

            Collections.sort(p, (Processo o1, Processo o2) -> {
                if (o1.getDeadLine() < o2.getDeadLine()) {
                    return -1;
                } else if (o1.getDeadLine() > o2.getDeadLine()) {
                    return 1;
                } else {
                    return 0;
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
