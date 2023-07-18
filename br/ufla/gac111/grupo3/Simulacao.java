package br.ufla.gac111.grupo3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simula todo o sistema do mamute.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author Mateus Henrique Teixeira
 * @author Whilker Henrique dos Santos Silva 
 * @author Victor Hugo Daia Lorenzato
 * @author Pedro Henrique Pigozzi Gomes 
 */

public class Simulacao {

    //Atributos
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Mamute mamute;
    private PontoParada pontoEmbarque;
    private PontoParada pontoDesembarque;
    private Cachorro cachorro;
    private ArrayList<Item> decoracao;

    private static Simulacao instanciaUnica;

    /**
     * Construtor do objeto Simulacao
     * <p>
     * Instancia objetos do tipo Mapa, JanelaSimulacao e os demais objetos da simulação e da decoração do mapa.
     */

    private Simulacao() {
        mapa = new Mapa(22, 22);
        criaItensSimulacao();
        criaItensDecoracao(pontoEmbarque, pontoDesembarque);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    /**
     * Instância única de Simulacao
     * <p>
     * Implementa o padrão de projeto Singleton para garantir que apenas
     * uma instância da classe Simulacao seja criada.
     * 
     * @return Simulacao - Única instância da classe Simulacao caso já tenha sido criada
     */

    public static Simulacao getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Simulacao();
        }
        return instanciaUnica;
    }

    /**
     * Inicia a execução da simulação
     * <p>
     * Atualiza a janela de simulação, para que todos os objetos criados no construtor sejam colocados na tela.
     * Além disso, inicia um loop que executa passo a passo da simulação.
     * @param tempoSimulacao - inteiro que define a quantidade de passos que serão executados da simulação
     * @param qtdAlunosMax - inteiro que define a quantidade máxima de alunos que serão criados por tempo de simulação
     */

    public void executarSimulacao(int tempoSimulacao, int qtdAlunosMax) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i, qtdAlunosMax);
        }
    }

    /**
     * Realiza um passo da simulação
     * <p>
     * Cria alunos nos pontos de embarque e desembarque, embarca alunos no mamute e movimenta a fila.
     * 
     * @param tempoSimulacao - inteiro que representa o tempo atual da simulação
     * @param qtdAlunosMax - inteiro que define a quantidade máxima de alunos que serão criados por tempo de simulação
     */

    private void executarUmPasso(int tempoSimulacao, int qtdAlunosMax) {
        criarAlunos(tempoSimulacao, pontoEmbarque, qtdAlunosMax);
        criarAlunos(tempoSimulacao, pontoDesembarque, qtdAlunosMax);

        embarcarAluno(tempoSimulacao, pontoEmbarque);
        embarcarAluno(tempoSimulacao, pontoDesembarque);

        movimentaFila(pontoEmbarque, tempoSimulacao);
        movimentaFila(pontoDesembarque, tempoSimulacao);
    }

    /**
     * Cria alunos nos pontos de embarque e desembarque
     * <p>
     * Gera um número aleatório de alunos por tempo de simulação, caso o ponto de parada não estiver cheio.-
     * Os alunos criados são posicionados na última posição livre da fila.
     * @param tempoSimulacao - inteiro que representa o tempo atual da simulação
     * @param pontoParada - PontoParada que indica o ponto que se deseja adicionar os alunos
     * @param qtdAlunosMax - inteiro que define a quantidade máxima de alunos que serão criados por tempo de simulação
     */

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada, int qtdAlunosMax) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(qtdAlunosMax);

        if (!pontoParada.estaCheia() && tempoSimulacao % 2 == 0) {
            for (int i = 0; i < qtdAlunos; i++) {
                int tempoEntrada = e.nextInt(2) + 1;
                Localizacao inicioFila = new Localizacao(pontoParada.getLocalizacaoAtual().getX() + 1,
                        pontoParada.getLocalizacaoAtual().getY());
                Aluno aluno = new Aluno(pontoParada.posicaoLivre(), inicioFila, tempoEntrada);
                pontoParada.adicionarAluno(aluno);
                atualizarPontoParada(pontoParada);
                mapa.adicionarItem(aluno);
            }
        }
    }

    /**
     * Realiza o embarque de alunos no mamute
     * <p>
     * Verifica se o mamute está disponível para embarque e se o ponto de parada não está vazio.
     * Se as condições forem atendidas, verifica se o aluno embarcou, para, logo em seguida, removê-lo do mapa
     * e do ponto de parada.
     * @param tempoSimulacao - inteiro que representa o tempo atual da simulação
     * @param pontoParada - PontoParada que indica o ponto que se deseja remover o aluno embarcado
     */

    private void embarcarAluno(int tempoSimulacao, PontoParada pontoParada) {
        if (mamute.estaDisponivel(tempoSimulacao, pontoParada) && !pontoParada.estaVazia()) {
            if (!pontoParada.alunoEmbarcado()) {
                mamute.setTempoProximaEntrada(pontoParada.getPrimeiroTempoEntrada(), tempoSimulacao);
                pontoParada.embarcarAluno();
            } else {
                Aluno aluno = pontoParada.removerAluno();
                mamute.embarcarAluno(aluno);
                mapa.removerItem(aluno);
                atualizarPontoParada(pontoParada);
                janelaSimulacao.executarAcao();
            }
        } else {
            movimentaMamute(pontoEmbarque, pontoDesembarque);
            moverCachorro();

        }
    }

    /**
     * Movimenta a fila de alunos para a posição de embarque
     * <p>
     * Se o ponto de parada não estiver vazio, atualiza a fila, movendo cada aluno para sua posicação adequada.
     * Isso ocorre até que todos os alunos da fila estejam na posição correta. Durante esse processo, os 
     * itens dinâmicos continuam se movimentando normalmente.
     * @param pontoParada - PontoParada que indica o ponto que se deseja atualizar a fila de alunos
     * @param tempoSimulacao - inteiro que representa o tempo atual da simulação
     */

    private void movimentaFila(PontoParada pontoParada, int tempoSimulacao) {
        if (!pontoParada.estaVazia()) {
            boolean aux = true;
            while (aux) {
                aux = false;
                for (int i = 0; i <= pontoParada.tamanhoFila() - 1; i++) {
                    if (pontoParada.atualizaFila(i)) {
                        aux = true;
                        movimentaMamute(pontoEmbarque, pontoDesembarque);
                        moverCachorro();
                    }
                }
            }
        }
    }

    /**
     * Pausa a execução do programa por alguns milissegundos
     * @param milisegundos
     */

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Movimenta o mamute entre os pontos de parada
     * <p>
     * Faz com que o mamute se movimente, atualizando a janela de simulação e esperando por um tempo (milissegundos)
     * @param ponto1 - Localizacao do primeiro ponto
     * @param ponto2 - Localizacao do segundo ponto
     */

    private void movimentaMamute(PontoParada ponto1, PontoParada ponto2) {
        mamute.realizarPercurso(ponto1.getLocalizacaoAtual(), ponto2.getLocalizacaoAtual());
        esperar(50);
        janelaSimulacao.executarAcao();
        esperar(50);
    }

    /**
     * Atualiza a posição dos alunos no ponto de parada no mapa
     * @param pontoParada - PontoParada que se deseja atualizar
     */

    private void atualizarPontoParada(PontoParada pontoParada) {
        for (Aluno a : pontoParada.getFila())
            mapa.atualizarMapa(a);
    }

    /**
     * Movimenta o cachorro entre dois pontos
     */

    private void moverCachorro() {
        cachorro.realizarPercurso(new Localizacao(15, 2), new Localizacao(4, 2));
    }

    /**
     * Cria itens de decoração entre os dois pontos de parada
     * <p>
     * Cria um cachorro, o asfalto e uma sequência de árvores e casas em posições específcas.
     * @param pontoEsquerda - Localizacao do ponto a esquerda
     * @param pontoDireita - Localizacao do ponto a direita
     */

    private void criaItensDecoracao(PontoParada pontoEsquerda, PontoParada pontoDireita) {
        decoracao = new ArrayList<Item>();
        int inicio = 5;
        int y = 4;

        cachorro = new Cachorro(new Localizacao(20, y - 2), new Localizacao(4, y - 2));
        mapa.adicionarItem(cachorro);

        for (int i = 0; i < 2; i++) {
            decoracao.add(new Decoracao(new Localizacao((inicio + 0) + 6 * i, y - 1), "Imagens/Arvore1.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio + 1) + 6 * i, y), "Imagens/Casa1.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio + 2) + 6 * i, y), "Imagens/Casa2.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio + 3) + 6 * i, y), "Imagens/Casa3.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio + 4) + 6 * i, y - 1), "Imagens/Arvore2.png"));

        }

        for (int i = pontoEsquerda.getLocalizacaoAtual().getX() + 2; i <= pontoDireita.getLocalizacaoAtual().getX()
                - 1; i++) {
            decoracao.add(new Decoracao(new Localizacao(i, pontoDireita.getLocalizacaoAtual().getY()),
                    "Imagens/Asfalto.png"));
        }

        for (Item i : decoracao)
            mapa.adicionarItem(i);
    }

    /**
     * Cria os itens principais da simulação
     * <p>
     * Cria um ponto de embarque, um de desembarque e um mamute.
     */

    private void criaItensSimulacao() {
        pontoEmbarque = new PontoParada(new Localizacao(1, 1));
        mapa.adicionarItem(pontoEmbarque);

        pontoDesembarque = new PontoParada(new Localizacao(18, 1));
        mapa.adicionarItem(pontoDesembarque);

        Localizacao ponto1 = pontoEmbarque.getLocalizacaoAtual();
        Localizacao ponto2 = pontoDesembarque.getLocalizacaoAtual();

        Localizacao posInicial = new Localizacao(ponto1.getX() + 1, ponto1.getY() - 1);
        Localizacao posFinal = new Localizacao(ponto2.getX() + 1, ponto2.getY() - 1);

        mamute = new Mamute(posInicial, posFinal);
        mapa.adicionarItem(mamute);
    }
}