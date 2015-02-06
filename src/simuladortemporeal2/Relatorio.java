

package simuladortemporeal2;

public class Relatorio {
    
    private int trocas;
    private int preempcao;
    private double utilizacao;
    private int[] tempoPrimeiro;
    private char[] nomePrimeiro;
    private boolean escalonou;
    private int cont;
    
    public Relatorio(int qtdProcessos){
        tempoPrimeiro=new int[qtdProcessos];
        nomePrimeiro=new char[qtdProcessos]; 
        escalonou=true;
        cont=0;
        preempcao=0;
        trocas=0;
    }   
    
    public void sorProccessByTN(){ //tempo e nome
        for (int i = 0; i < tempoPrimeiro.length; i++) {
            for (int j = i+1; j < tempoPrimeiro.length; j++) {
                if (nomePrimeiro[i]> nomePrimeiro[j]) {  
                    char aux = nomePrimeiro[i]; 
                    nomePrimeiro[i] = nomePrimeiro[j]; 
                    nomePrimeiro[j] = aux; 
                    int aux2 = tempoPrimeiro[i]; 
                    tempoPrimeiro[i] = tempoPrimeiro[j]; 
                    tempoPrimeiro[j] = aux2; 
                } 
            } 
        }
    }
        
    public void showResult(){
        String escalona="";          
        escalona=escalonou?" É escalonavel!! ":" Impossível escalonar!! ";
        String primeiros="";
        
        for (int j=0; j < tempoPrimeiro.length; j++) {
            primeiros+="\t P"+nomePrimeiro[j]+"= "+tempoPrimeiro[j]+"\n";
                  
        }
        System.out.println("\n\n Relatório ");
        System.out.println("\t "+escalona);
        System.out.println(" Quantidade de trocas: "+trocas);
        System.out.println(" Quantidade de preempções: "+preempcao);
        System.out.println(" Utilização CPU: "+utilizacao);
        System.out.println(" Primeiro escalonamento: \n"+primeiros);
        
    }
    
    public void addChange(){
        trocas++;
    }
    
    public void addPreemp(){
        preempcao++;
    }
    
    public void addFirst(int tempo,char nome){
        tempoPrimeiro[cont]=tempo;
        nomePrimeiro[cont]=nome;
        cont++;
    }
    
    public int getTrocas() {
        return trocas;
    }

    public void setTrocas(int trocas) {
        this.trocas = trocas;
    }

    public int getPreempcao() {
        return preempcao;
    }

    public void setPreempcao(int preempcao) {
        this.preempcao = preempcao;
    }

    public double getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(double utilizacao) {
        this.utilizacao = utilizacao;
    }

    public int[] getPrimeiro() {
        return tempoPrimeiro;
    }

    public void setPrimeiro(int[] primeiro) {
        this.tempoPrimeiro = primeiro;
    }
    
    public boolean isEscalonou() {
        return escalonou;
    }

    public void setEscalonou(boolean escalonou) {
        this.escalonou = escalonou;
    }
}
