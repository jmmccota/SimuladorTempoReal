/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladortemporeal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author JM
 */
public class Simulador {
    public void TaxaMonotonica(ArrayList<Processo> processos, long limiteTempo){
	ArrayList<Processo> processosOrdenado = (ArrayList<Processo>) processos.clone();
        //Collections.sort(processosOrdenado);
        for (Processo p : processosOrdenado){
            p.setDead(p.getTempo());
        }
        

                        

    }
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();
        a.add("Joao");
        a.add("Ana");
        a.add("Ana2");
        System.err.println(a.size()+"");
        ArrayList<String> b = (ArrayList<String>) a.clone();
        System.err.println(b.size()+"");
        Collections.sort(b);
        for (Iterator<String> iterator = b.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            System.out.println(next);
        }
    }

}
