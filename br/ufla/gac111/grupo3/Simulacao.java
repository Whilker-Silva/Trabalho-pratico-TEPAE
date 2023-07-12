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

    private Simulacao() {
        mapa = new Mapa(40, 40);

        pontoEmbarque = new PontoParada(new Localizacao(35, 1));
        mapa.adicionarItem(pontoEmbarque);


        pontoDesembarque = new PontoParada(new Localizacao(5, 1));
        mapa.adicionarItem(pontoDesembarque);

        Localizacao ponto1 = pontoEmbarque.getLocalizacaoAtual();
        Localizacao ponto2 = pontoDesembarque.getLocalizacaoAtual();


        Localizacao posInicial = new Localizacao(ponto1.getX() + 1, ponto1.getY() - 1);
        Localizacao posFinal = new Localizacao(ponto2.getX() + 1, ponto2.getY() - 1);

        mamute = new Mamute(posInicial, posFinal);
        mapa.adicionarItem(mamute);

        
        criaItensDecoracao();

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
            executarUmPasso(i);
        }
    }

    private void executarUmPasso(int tempoSimulacao) {
        criarAlunos(tempoSimulacao, pontoEmbarque);
        criarAlunos(tempoSimulacao, pontoDesembarque);

        moverCachorro();

        embarcarAluno(tempoSimulacao, pontoEmbarque);
        embarcarAluno(tempoSimulacao, pontoDesembarque);

        movimentaFila(pontoEmbarque, tempoSimulacao);        
        movimentaFila(pontoDesembarque, tempoSimulacao);
    }

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(2);

        if (!pontoParada.estaCheia()) {
            for (int i = 0; i < qtdAlunos; i++) {
                int tempoEntrada = e.nextInt(2) + 1;
                Localizacao inicioFila = new Localizacao(pontoParada.getLocalizacaoAtual().getX() + 1, pontoParada.getLocalizacaoAtual().getY());
                Aluno aluno = new Aluno(pontoParada.posicaoLivre(), inicioFila, tempoEntrada);
                pontoParada.adicionarAluno(aluno);
                atualizarPontoParada(pontoParada);
                mapa.adicionarItem(aluno);
            }
        }
    }

    private void embarcarAluno(int tempoSimulacao, PontoParada pontoParada) {
        if (mamute.estaDisponivel(tempoSimulacao, pontoParada) && !pontoParada.estaVazia()) {
            if (!pontoParada.alunoEmbarcado()) {
                mamute.setTempoProximaEntrada(pontoParada.getPrimeiroTempoEntrada(), tempoSimulacao);
                pontoParada.embarcarAluno();
            } else {
                Aluno aluno = pontoParada.removerAluno();
                mamute.embarcarAluno(aluno);
                atualizarPontoParada(pontoParada);
                mapa.removerItem(aluno);
                janelaSimulacao.executarAcao();
            }
        } else {
            movimentaMamute(pontoEmbarque, pontoDesembarque);
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
                        movimentaMamute(pontoEmbarque, pontoDesembarque); 
                        moverCachorro();
                    }
                }
            }
        }
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void movimentaMamute(PontoParada ponto1, PontoParada ponto2) {
        mamute.realizarPercurso(ponto1.getLocalizacaoAtual(), ponto2.getLocalizacaoAtual());
        esperar(50);
        janelaSimulacao.executarAcao();
        esperar(50);
    }

    private void atualizarPontoParada(PontoParada pontoParada) {
        for (Aluno a : pontoParada.getFila())
            mapa.atualizarMapa(a);
    }

    private void moverCachorro(){
        cachorro.realizarPercurso(new Localizacao(32, 2), new Localizacao(8, 2));
    }

    private void criaItensDecoracao(){
        decoracao = new ArrayList<Item>();
        int inicio = 11;
        int y = 4;

        cachorro = new Cachorro(new Localizacao(32, y-2), new Localizacao(8, y-2));
        mapa.adicionarItem(cachorro);

        for (int i = 0; i < 4; i++) {
            decoracao.add(new Decoracao(new Localizacao((inicio+0)+6*i, y-1),"Imagens/Arvore1.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio+1)+6*i, y),"Imagens/Casa1.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio+2)+6*i, y),"Imagens/Casa2.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio+3)+6*i, y),"Imagens/Casa3.png"));
            decoracao.add(new Decoracao(new Localizacao((inicio+4)+6*i, y-1),"Imagens/Arvore2.png"));

        }
        for (Item i : decoracao)
            mapa.adicionarItem(i);
    }

}