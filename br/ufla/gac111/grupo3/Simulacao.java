package br.ufla.gac111.grupo3;

import java.util.Random;

/**
 * Responsavel pela simulacao.
 * <p>
 * Importante obeservar que foi usado o padrão de projeto singleton para essa
 * clase, dessa forma é possível instanciar apenas um único objeto do tipo
 * Simulacao
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author
 */
public class Simulacao {

    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Mamute mamute;
    private PontoParada pontoEmbarque;
    private PontoParada pontoDesembarque;

    private static Simulacao instanciaUnica;

    private Simulacao() {
        mapa = new Mapa(40, 40);

        pontoEmbarque = new PontoParada(new Localizacao(35, 1));
        mapa.adicionarItem(pontoEmbarque);
        Localizacao ponto1 = pontoEmbarque.getLocalizacaoAtual();

        pontoDesembarque = new PontoParada(new Localizacao(5, 1));
        mapa.adicionarItem(pontoDesembarque);
        Localizacao ponto2 = pontoDesembarque.getLocalizacaoAtual();

        Localizacao posInicial = new Localizacao(ponto1.getX() + 1, ponto1.getY() - 1);
        Localizacao posFinal = new Localizacao(ponto2.getX() + 1, ponto2.getY() - 1);

        mamute = new Mamute(posInicial, posFinal);
        mapa.adicionarItem(mamute);

        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    public static Simulacao getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Simulacao();
        }
        return instanciaUnica;
    }

    public void executarSimulacao(int tempoSimulacao) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < tempoSimulacao; i++) {
<<<<<<<< HEAD:codigo/1/Simulacao.java
            // System.out.print("Tempo: ");
            // System.out.println(i);
========
>>>>>>>> Mateus:br/ufla/gac111/grupo3/Simulacao.java
            executarUmPasso(i);
        }
    }

    private void executarUmPasso(int tempoSimulacao) {
        criarAlunos(tempoSimulacao, pontoEmbarque);
<<<<<<<< HEAD:codigo/1/Simulacao.java
        embarcarAluno(tempoSimulacao, pontoEmbarque);
        pontoEmbarque.moverFila();
========
>>>>>>>> Mateus:br/ufla/gac111/grupo3/Simulacao.java
        criarAlunos(tempoSimulacao, pontoDesembarque);

        embarcarAluno(tempoSimulacao, pontoEmbarque);
        embarcarAluno(tempoSimulacao, pontoDesembarque);
<<<<<<<< HEAD:codigo/1/Simulacao.java
        pontoEmbarque.moverFila();

        janelaSimulacao.executarAcao();
========

        movimentaFila(pontoEmbarque, tempoSimulacao);        
        movimentaFila(pontoDesembarque, tempoSimulacao);
    }

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(3);

        if (!pontoParada.estaCheia()) {
            for (int i = 0; i < qtdAlunos; i++) {
                int tempoEntrada = e.nextInt(2) + 1;
                Localizacao inicioFila = new Localizacao(pontoParada.getLocalizacaoAtual().getX() + 1,
                        pontoParada.getLocalizacaoAtual().getY());
                Aluno aluno = new Aluno(pontoParada.posicaoLivre(), inicioFila, tempoEntrada);
                atualizarPontoParada(pontoParada);
                mapa.adicionarItem(aluno);
                pontoParada.adicionarAluno(aluno);
            }
        }
    }

    private void embarcarAluno(int tempoSimulacao, PontoParada pontoParada) {
        if (mamute.estaDisponivel(tempoSimulacao, pontoParada) && !pontoParada.estaVazia()) {
            if (!pontoParada.alunoEmbarcado()) {
                mamute.setTempoProximaEntrada(pontoParada.getPrimeiroAluno().getTempoEntrada(), tempoSimulacao);
                pontoParada.embarcarAluno();
            } else {
                Aluno aluno = pontoParada.removerAluno();
                mamute.embarcarAluno(aluno, tempoSimulacao);
                atualizarPontoParada(pontoParada);
                mapa.removerItem(aluno);
                janelaSimulacao.executarAcao();
            }
        } else {
            movimentaMamute(tempoSimulacao, pontoEmbarque, pontoDesembarque);
        }
    }

    private void movimentaFila(PontoParada pontoParada, int tempoSimulacao) {

        if (!pontoParada.estaVazia()) {
            boolean aux = true;
            while (aux) {
                aux = false;
                for (int i = 0; i <= pontoParada.tamanhoFila() - 1; i++) {
                    if (pontoParada.atualizaFila(i)) {
                        aux = true;
                        movimentaMamute(tempoSimulacao, pontoEmbarque, pontoDesembarque); 
                    }
                }
            }
        }
>>>>>>>> Mateus:br/ufla/gac111/grupo3/Simulacao.java
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

<<<<<<<< HEAD:codigo/1/Simulacao.java
    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(3);

        if (!pontoParada.estaCheia()) {
            for (int i = 0; i < qtdAlunos; i++) {
                int tempoEntrada = e.nextInt(3) + 1;
                Localizacao inicioFila = new Localizacao(pontoParada.getLocalizacaoAtual().getX() + 1,
                        pontoParada.getLocalizacaoAtual().getY());
                Aluno aluno = new Aluno(pontoParada.posicaoLivre(), inicioFila, tempoEntrada);
                mapa.adicionarItem(aluno);
                pontoParada.adicionarAluno(aluno);
            }
        }
    }

    private void embarcarAluno(int tempoSimulacao, PontoParada pontoParada) {
        if (mamute.estaDisponivel(tempoSimulacao, pontoParada) && !pontoParada.estaVazia()) {
            if (!pontoParada.getPrimeiroAluno().getEmbarcou()) {
                mamute.setTempoProximaEntrada(pontoParada.getPrimeiroAluno().getTempoEntrada(), tempoSimulacao);
                pontoParada.embarcarAluno();
                System.out.println("embarcou");
            } else {
                Aluno aluno = pontoParada.removerAluno();
                mamute.embarcarAluno(aluno, tempoSimulacao);
                mapa.removerItem(aluno);
                System.out.println("removeu");
            }
        } else {
            if (mamute.estaCheio())
                mamute.realizarPercurso(tempoSimulacao, pontoEmbarque.getLocalizacaoAtual(),
                        pontoDesembarque.getLocalizacaoAtual());
        }
========
    private void movimentaMamute(int tempoSimulacao, PontoParada ponto1, PontoParada ponto2) {
        if (mamute.estaCheio()) {
            mamute.realizarPercurso(tempoSimulacao, ponto1.getLocalizacaoAtual(), ponto2.getLocalizacaoAtual());
        }
        esperar(50);
        janelaSimulacao.executarAcao();
        esperar(50);
    }

    private void atualizarPontoParada(PontoParada pontoParada) {
        for (Aluno a : pontoParada.getFila())
            mapa.atualizarMapa(a);
>>>>>>>> Mateus:br/ufla/gac111/grupo3/Simulacao.java
    }
}