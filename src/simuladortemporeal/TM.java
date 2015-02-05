/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Windows
 */
public class TM {

    ArrayList<Processo> list;
    ArrayList<Processo> ordem;
    int tempoMaximo;

    public TM(ArrayList<Processo> lista) {

        list = lista;
        ordem = (ArrayList<Processo>) lista.clone();
        Collections.sort(ordem, new Comparator<Processo>() {
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
        //primeiraOrdem();
        tempoMaximo = 0;
        for (Processo p : list) {
            if (p.getPeriodo() > tempoMaximo) {

                tempoMaximo = p.getPeriodo();

            }
        }
        for (Processo list1 : list) {
            list1.preenchePeriodos(tempoMaximo);
        }
        for (int i = 0; i < ordem.size(); i++) {
            ordem.get(i).setPrioridade(ordem.size()-i);
        }
        executa();

    }

//    public void primeiraOrdem() {
//        Processo aux;
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i + 1; j < list.size(); j++) // sempre 1 elemento à frente
//            {
//                if ((ordem.get(i).getPeriodo()) > (ordem.get(j).getPeriodo())) {
//                    aux = ordem.get(i);
//                    ordem.set(i, ordem.get(j));
//                    ordem.set(j, aux);
//
//                }
//            }
//        } // fim
//    }
    private void executa() {
        ArrayList[] a = new ArrayList[list.size()];

        for (int i = 0; i < a.length; i++) {
            a[i] = new ArrayList<String>();
        }
        int cont = 0;
        for (int tempo = 0; tempo < tempoMaximo; tempo++) {
            //

            atualizaOrdem(tempo);
        }

    }

    private void atualizaOrdem(int tempo) {
        //se tempo maior que ordem[0].executado e menor que periodos[1] 
    }

}
