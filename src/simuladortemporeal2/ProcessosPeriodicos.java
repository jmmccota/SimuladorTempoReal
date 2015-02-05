/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal2;

import static java.lang.Character.toLowerCase;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Sammy
 */
public class ProcessosPeriodicos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //ArrayList<Processo> processos = new ArrayList<Processo>();
        Escalonador escalonador;
        int custo, periodo, deadLine;
        char nome;
        ArrayList<Processo> processos;
        System.out.println("\t Escolha o processo de escalonamento:");
        System.out.println("1 - Taxa Monotônica;");
        System.out.println("2 - Earliest Deadline First;");
        System.out.println("3 - Deadline Monotônico;");
        System.out.println("Opçao: ");
        Scanner ent = new Scanner(System.in);
        int opcao;
        opcao = ent.nextInt();
        int qtd = 0;
        System.out.println("Entre com o número de processos que deseja escalonar: ");
        qtd = ent.nextInt();
        processos = new ArrayList<>();
        for (int x = 0; x < qtd; x++) {
            int i = x+1;
            nome = (char) ('0' + i);
            //nome =(char) a;
            System.out.println("Entre com o tempo de execução do processo P"+ nome + ": ");
            custo = ent.nextInt();
            System.out.println("Informe o período do processo P"+ nome + ": ");
            periodo = ent.nextInt();
            System.out.println("Informe o deadline do processo P"+ nome + ": ");
            deadLine = ent.nextInt();
            processos.add(new Processo(custo, periodo, nome, deadLine));

        }
        int tempo;
        System.out.println("Informe o tempo de execução: ");
        tempo=ent.nextInt();
        /*
         processos=new Processo[3];
         processos[0]=new Processo(3,9,'A',9);
         processos[1]=new Processo(4,18,'B',18);
         processos[2]=new Processo(4,12,'C',12);
         //processos[3]=new Processo(3,24,'D',24);        
         */
        escalonador = new Escalonador(opcao);
        //escalonador=new Escalonador('m');
        escalonador.Escalonar(processos, tempo);
        //escalonador.Escalonar(processos,18);
    }

}
