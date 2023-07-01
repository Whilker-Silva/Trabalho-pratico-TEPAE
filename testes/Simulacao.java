package testes;

import java.util.Random;

public class Simulacao {

    private Fila fila;
    private int tempoSimulacao;
    private int tempoSimulacaoAtual;
    private Random rand = new Random();
    private int tempoEntrada;

    public Simulacao(int tempoSimulacao){
        this.tempoSimulacao = tempoSimulacao;
        tempoSimulacaoAtual = 0;
    }

    public void execultarSimulacao(){
        criarFila();
        gerarAlunos();
    }

    public void gerarAlunos(){

        
        while(tempoSimulacaoAtual + gerarTempo() <= tempoSimulacao){
            adicionarAluno(tempoEntrada);
            tempoSimulacaoAtual += (rand.nextInt(3)+1);
        }
        fila.lerFila();
        System.out.println("Fim!");

    }

    public int gerarTempo(){
        tempoEntrada = 1 + rand.nextInt(4);

        return tempoEntrada;
    }


    public void criarFila(){
        fila = new Fila();
    }

    public void adicionarAluno(int tempoEntrada){
        fila.adicionarAluno(new Aluno(tempoEntrada, tempoSimulacaoAtual));
    }


}
