import java.util.Random;

/**
 * Responsavel pela simulacao.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {

    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Mamute mamute;
    private PontoParada pontoEmbarque;
    private PontoParada pontoDesembarque;

    public Simulacao() {
        mapa = new Mapa(40, 40);

        pontoEmbarque = new PontoParada(new Localizacao(0, 1));
        mapa.adicionarItem(pontoEmbarque);
        Localizacao ponto1 = pontoEmbarque.getLocalizacaoAtual();

        pontoDesembarque = new PontoParada(new Localizacao(12, 1));
        mapa.adicionarItem(pontoDesembarque);
        Localizacao ponto2 = pontoDesembarque.getLocalizacaoAtual();

        Localizacao posInicial = new Localizacao(ponto1.getX() + 1, ponto1.getY() - 1);
        Localizacao posFinal = new Localizacao(ponto2.getX() + 1, ponto2.getY() - 1);

        mamute = new Mamute(posInicial, posFinal);
        mapa.adicionarItem(mamute);

        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    public void executarSimulacao(int tempoSimulacao) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i);
            esperar(200);
        }
    }

    private void executarUmPasso(int tempoSimulacao) {

        criarAlunos(tempoSimulacao, pontoEmbarque);
        movimentarFila(tempoSimulacao, pontoEmbarque);

        // if(mamute.estaCheio()){
        mamute.realizarPercurso(tempoSimulacao, pontoEmbarque.getLocalizacaoAtual(),
                pontoDesembarque.getLocalizacaoAtual());
        // }

        janelaSimulacao.executarAcao();
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada) {
        Random e = new Random();
        int qtdAlunos = e.nextInt(4);

        for (int i = 0; i < qtdAlunos; i++) {
            int tempoEntrada = e.nextInt(3) + 1;
            Localizacao inicioFila = new Localizacao(pontoParada.getLocalizacaoAtual().getX()+1, pontoParada.getLocalizacaoAtual().getY());
            Aluno aluno = new Aluno(pontoParada.posicaoLivre(), inicioFila, tempoEntrada);
            mapa.adicionarItem(aluno);
            pontoParada.montarFila(aluno);
        }
    }

    private void movimentarFila(int tempoSimulacao, PontoParada pontoParada) {
        if (mamute.estaDisponivel(tempoSimulacao) && !pontoParada.estaVazia() && pontoParada.posicaoEntrada()) {
            Aluno aluno = pontoParada.removerAluno();
            mapa.removerItem(aluno);
            mamute.adicionarAluno(aluno, tempoSimulacao);
            mamute.setTempoProximaEntrada(aluno.getTempoEntrada(), tempoSimulacao);
            System.out.println("removeu");

        }

    }
}