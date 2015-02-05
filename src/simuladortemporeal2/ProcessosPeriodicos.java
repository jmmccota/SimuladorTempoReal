/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuladortemporeal2;

import static java.lang.Character.toLowerCase;
import java.util.ArrayList;
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
        int custo,periodo,deadLine;
        char nome;
        ArrayList<Processo> processos;
        
        int qtd=0;
        qtd=Integer.parseInt(JOptionPane.showInputDialog("Informe a Quantidade de processos"));
        processos = new ArrayList<>();
        for(int x=0;x<qtd;x++){
            String nomeString=JOptionPane.showInputDialog("Informe o nome do processo");
            nome=nomeString.toCharArray()[0];
            custo=Integer.parseInt(JOptionPane.showInputDialog("Informe o Custo de CPU de "+nome));
            periodo=Integer.parseInt(JOptionPane.showInputDialog("Informe o Periodo de "+nome));
            deadLine=Integer.parseInt(JOptionPane.showInputDialog("Informe o DeadLine de "+nome));
            processos.add(new Processo(custo,periodo,nome,deadLine));
            
        }
        String tipoEscal=JOptionPane.showInputDialog("Informe o tipo de Escalonamento(Monotonica/EDF/DeadLine Monotonico)");
        char tipoEscalonamento=toLowerCase(tipoEscal.toCharArray()[0]);
        int tempo=Integer.parseInt(JOptionPane.showInputDialog("Informe o tempo de execucao"));
            /*
        processos=new Processo[3];
        processos[0]=new Processo(3,9,'A',9);
        processos[1]=new Processo(4,18,'B',18);
        processos[2]=new Processo(4,12,'C',12);
        //processos[3]=new Processo(3,24,'D',24);        
        */
        escalonador=new Escalonador(tipoEscalonamento);
        //escalonador=new Escalonador('m');
        escalonador.Escalonar(processos,tempo);
        //escalonador.Escalonar(processos,18);
    }
    
}
