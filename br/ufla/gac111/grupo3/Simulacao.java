package br.ufla.gac111.grupo3;

import java.util.ArrayList;
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
    private Cachorro cachorro;
    private ArrayList<Item> decoracao;

    private static Simulacao instanciaUnica;

    /**
     * -/-/-/-/-
     */
    private Simulacao() {
        mapa = new Mapa(22, 22);
        criaItensSimulacao();
        criaItensDecoracao(pontoEmbarque, pontoDesembarque);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    /**
     * Implementa o padrão de projeto Singleton para garantir que apenas
     * uma instância da classe Simulacao seja criada.
     * <p>
     * 
     * @return Única instância da classe Simulacao caso já tenha sido criada.
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
     * Chama o método executarAcao() da janela de simulação para exbir a janela.
     * Inicia um loop que executa o excutarumPasso() para cada tempo de simulação.
     * O número de etapas é determinado pelo parâmetro tempoSimulacao.
     * 
     * @param tempoSimulacao
     * @param qtdAlunosMax
     */

    public void executarSimulacao(int tempoSimulacao, int qtdAlunosMax) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i, qtdAlunosMax);
        }
    }

    /**
     * Representa um passo do aluno na simulação
     * <p>
     * Cria aluno nos pontos de embarque e desembarque.
     * Embarca aluno e movimenta a fila.
     * 
     * @param tempoSimulacao
     * @param qtdAlunosMax
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
     * Gera um número aleatório de alunos.
     * Se o ponto de parada não estiver cheio -/-/-/-/-/
     * 
     * @param tempoSimulacao
     * @param pontoParada
     * @param qtdAlunosMax
     */

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada, int qtdAlunosMax) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(qtdAlunosMax);

        if (!pontoParada.estaCheia() && tempoSimulacao % 3 == 0) {
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
     * Verifica se o mamute está disponível para embarque e se o ponto de parada não
     * está vazio.
     * Se as condições forem atendidas, verifica se o aluno embarcou.
     * Se o aluno não embarcou, define o tempo para a próxima entrada.
     * Se o aluno embarcou, remove o primeiro aluno da fila.
     * 
     * @param tempoSimulacao
     * @param pontoParada
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
                System.out.println(pontoParada.tamanhoFila());
                atualizarPontoParada(pontoParada);
                janelaSimulacao.executarAcao();
            }
        } else {
            movimentaMamute(pontoEmbarque, pontoDesembarque);
            moverCachorro();

        }
    }

    /**
     * Movimenta a fila nos pontos
     * <p>
     * Se o ponto de parada não estiver vazio, atualiza a fila para cada aluno.
     * Se o método atualizaFila() retornar true, o aluno foi movido.
     * 
     * @param pontoParada
     * @param tempoSimulacao
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
     * 
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
     * Chama o método realizarPercurso(), espera por um tempo e executa na janela de
     * simulação.
     * 
     * @param ponto1
     * @param ponto2
     */

    private void movimentaMamute(PontoParada ponto1, PontoParada ponto2) {
        mamute.realizarPercurso(ponto1.getLocalizacaoAtual(), ponto2.getLocalizacaoAtual());
        esperar(50);
        janelaSimulacao.executarAcao();
        esperar(50);
    }

    /**
     * Atualiza a posição dos alunos no ponto de parada no mapa
     * <p>
     * 
     * @param pontoParada
     */

    private void atualizarPontoParada(PontoParada pontoParada) {
        for (Aluno a : pontoParada.getFila())
            mapa.atualizarMapa(a);
    }

    /**
     * Movimenta o cachorro entr dois pontos
     * <p>
     * Chama o método realizarPercurso para excutar a ação de movimentá-lo.
     */
    private void moverCachorro() {
        cachorro.realizarPercurso(new Localizacao(15, 2), new Localizacao(4, 2));
    }

    /**
     * Cria itens de decoração entre os dois pontos de parada
     * <p>
     * Cria um cachorro e uma sequência de árvores e casas em posições específcas.
     * 
     * @param pontoEsquerda
     * @param pontoDireita
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
     * -/-/-/-/-/-
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