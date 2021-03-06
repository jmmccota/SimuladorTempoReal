package simuladortemporeal2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Escalonador {

    int tipo;
    int tempo;
    boolean ordena = false;
    Relatorio relatorio;
    int preemp[] = new int[40];
    int contPreemp = 0;

    public Escalonador(int t) {
        tipo = t;
        tempo = 0;
    }

    public void calculateEscalonish(ArrayList<Processo> processos, int time) {
        relatorio = new Relatorio(processos.size());
        char pAnterior = ' ';
        double utilizacao = 0;

        ArrayList<Processo> pTodos = new ArrayList<Processo>();
        System.out.print("Tempo\t");
        for (Processo p : processos) {
            utilizacao += ((double) p.getCusto() / (double) p.getPeriodo());
            pTodos.add(new Processo(p.getCusto(), p.getPeriodo(), p.getNome(), p.getDeadLine()));
            //System.out.print("P" + p.getNome() + "\t");
        }
        relatorio.setUtilizacao(utilizacao);
        System.out.println("Em Execução");
        processos = sortner(processos);
        do {
            ordena = false;
            processos = timeVerification(processos);

            processos = periodVerification(processos);

            if (ordena) {
                processos = sortner(processos);
            }

            char pAtual = proccessNotEndedName(processos);

            if (tempo == 0) {
                pAnterior = pAtual;
            }

            System.out.print(tempo + "-" + (tempo + 1) + "\t");

            for (Processo pTodo : pTodos) {
                if (pTodo.getNome() == pAtual) {
//                    System.out.print("0\t");
                    pTodo.setImprimir("*");
                } else {
//                    System.out.print("|\t");
                    pTodo.setImprimir("-");
                }
            }
            System.out.println("P" + pAtual);

            if (pAnterior != pAtual && pAtual != '-') {
                relatorio.addChange();
            }

            for (Processo processo : processos) {
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
        for (int xx = 0; xx < processos.size(); xx++) {
            //linha120
            System.out.println(" ");
            System.out.println("P" + pTodos.get(xx).getNome() + ": ");
            for (int jj = 0; jj < pTodos.get(xx).getImprimir().size(); jj++) {
                System.out.print(pTodos.get(xx).getImprimir().get(jj) + " ");
            }

        }
        relatorio.showResult();

    }

    public void addNotEndedProccess(ArrayList<Processo> processos) {
        for (Processo p : processos) {
            if (!p.isFezPrimeiro()) {
                relatorio.addFirst(0, p.getNome());
            }
            ordena = true;
        }
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
        for (Processo p : processos) {
            if (p.getContTempo() != 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Processo> timeVerification(ArrayList<Processo> processos) {

        for (Processo p : processos) {
            if (p.getContTempo() == p.getCusto()) {
                p.setContTempo(0);
                p.setAcabou(true);
                if (!p.isFezPrimeiro()) {
                    p.setFezPrimeiro(true);
                    relatorio.addFirst(tempo, p.getNome());
                }
                ordena = true;
            }
        }
        return processos;
    }

    public ArrayList<Processo> periodVerification(ArrayList<Processo> processos) {
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

    public ArrayList<Processo> sortner(ArrayList<Processo> p) {
        if (tipo == 1) {
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
        } else if (tipo == 2) {
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
        } else if (tipo == 3) {

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
        }
        return p;
    }

}
