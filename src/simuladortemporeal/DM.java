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
public class DM {

    ArrayList<Processo> list;

    public DM(ArrayList<Processo> lista) {
        list = lista;
    }

    public void atualizaOrdem(int tempo) {
        Processo aux;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) // sempre 1 elemento à frente
            {
//                if ((ordem.get(i).getPeriodo()-tempo) > (ordem.get(j).getPeriodo()-tempo)) {
//                   aux=ordem.get(i);
//                   ordem.set(i, ordem.get(j));
//                   ordem.set(j, aux);
//                   
            }
        }
    } // fim
}
