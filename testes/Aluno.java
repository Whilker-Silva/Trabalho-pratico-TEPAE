package testes;


public class Aluno {

    private int tempoEntrada;
    private int tempoChegada;
    

    public Aluno(int tempoEntrada, int tempoChegada){
        this.tempoChegada = tempoChegada;
        this.tempoEntrada = tempoEntrada;
    }


    public int getTempoEntrada() {
        return tempoEntrada;
    }


    public int getTempoChegada() {
        return tempoChegada;
    }


    @Override
    public String toString(){
        return "Tempo de chegada: " + getTempoChegada() + " Tempo de entrada: " + getTempoEntrada();
    }
    
}
