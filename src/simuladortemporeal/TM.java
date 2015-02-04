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
public class TM {

    ArrayList<Processo> list;
    ArrayList<Processo> ordem;
    int tempoMaximo;

    TM(ArrayList<Processo> lista) {

        list = lista;
        ordem = lista;
        primeiraOrdem();
        tempoMaximo = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPeriodo() > tempoMaximo) {
                tempoMaximo = list.get(i).getPeriodo();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).preenchePeriodos(tempoMaximo);
        }
        executa();

    }

    public void primeiraOrdem() {
        Processo aux;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) // sempre 1 elemento à frente
            {
                if ((ordem.get(i).getPeriodo()) > (ordem.get(j).getPeriodo())) {
                    aux = ordem.get(i);
                    ordem.set(i, ordem.get(j));
                    ordem.set(j, aux);

                }
            }
        } // fim
    }

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
