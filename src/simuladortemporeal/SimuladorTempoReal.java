/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Windows
 */
public class SimuladorTempoReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean continua = true;
        while (continua) {
            System.out.println("\t Escolha o processo de escalonamento:");
            System.out.println("1 - Taxa Monotônica;");
            System.out.println("2 - Earliest Deadline First;");
            System.out.println("3 - Deadline Monotônico;");
            System.out.println("4 - Sair");
            Scanner ent = new Scanner(System.in);
            int opcao;

            System.out.println("Opçao: ");
            opcao = ent.nextInt();
            int nproc = 0;
            if (opcao == 1) {
                System.out.println("Entre com o número de processos que deseja escalonar: ");
                nproc = ent.nextInt();
                ArrayList<Processo> lista = new ArrayList<Processo>();
                int tempo, peri;
                for (int i = 0; i < nproc; i++) {
                    System.out.println("Entre com o tempo de execução do processo " + (i + 1) + ": ");
                    tempo = ent.nextInt();
                    System.out.println("Entre com o período do processo " + (i + 1) + ": ");
                    peri = ent.nextInt();
                    Processo p;
                    p = new Processo(i + 1, tempo, peri, peri);
                    lista.add(p);
                }
                //pegar maior periodo pra colocar tempo limite
                //pegar ordem de menor período
                TM t;
                t = new TM(lista);

            } else if (opcao == 2) {
                System.out.println("Entre com o número de processos que deseja escalonar: ");
                nproc = ent.nextInt();
                ArrayList<Processo> lista = new ArrayList<Processo>();
                int tempo, peri;
                for (int i = 0; i < nproc; i++) {
                    System.out.println("Entre com o tempo de execução do processo p" + (i + 1) + ": ");
                    tempo = ent.nextInt();
                    System.out.println("Entre com o deadline do processo p" + (i + 1) + ": ");
                    peri = ent.nextInt();
                    Processo p;
                    p = new Processo(i + 1, tempo, peri, peri);
                    lista.add(p);
                }
                //pegar maior periodo pra colocar tempo limite
                //pegar ordem de menor deadline
                EDF t;
                t = new EDF(lista);
            } else if (opcao == 3) {
                System.out.println("Entre com o número de processos que deseja escalonar: ");
                nproc = ent.nextInt();
                ArrayList<Processo> lista = new ArrayList<Processo>();
                int tempo, peri, dead;
                for (int i = 0; i < nproc; i++) {
                    System.out.println("Entre com o tempo de execução do processo p" + (i + 1) + ": ");
                    tempo = ent.nextInt();
                    System.out.println("Entre com o periodo do processo p" + (i + 1) + ": ");
                    peri = ent.nextInt();
                    System.out.println("Entre com o deadline do processo p" + (i + 1) + ": ");
                    dead = ent.nextInt();
                    Processo p;
                    p = new Processo(i + 1, tempo, peri, dead);
                    lista.add(p);
                }
                //pegar maior periodo pra colocar tempo limite
                //pegar ordem de menor deadline
                DM t;
                t = new DM(lista);
            } else if (opcao == 4) {
                continua = false;
            }
        }

    }

}
