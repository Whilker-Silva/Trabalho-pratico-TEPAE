import java.util.Random;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    //private Aluno aluno;
    private Mamute mamute;
    private PontoParada pontoEmbarque;    
    private PontoParada pontoDesembarque;  
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();

        mamute = new Mamute(new Localizacao(1, 0));
        mapa.adicionarItem(mamute);

        pontoEmbarque = new PontoParada(new Localizacao(0, 1));
        mapa.adicionarItem(pontoEmbarque);

        pontoDesembarque = new PontoParada(new Localizacao(12, 1));
        mapa.adicionarItem(pontoDesembarque);

        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int tempoSimulacao){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i);
            esperar(500);
        }        
    }

    private void executarUmPasso(int tempoSimulacao) {

        criarAlunos(tempoSimulacao, pontoEmbarque);

        movimentarFila(tempoSimulacao, pontoEmbarque);

        //if(mamute.estaCheio())
            //mamute.realizarPercurso(tempoSimulacao, pontoEmbarque.getLocalizacaoAtual(), pontoDesembarque.getLocalizacaoAtual());

        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void criarAlunos(int tempoSimulacao, PontoParada pontoParada){
        Random e = new Random();
        int qtdAlunos = e.nextInt(4);

        for (int i = 0; i < qtdAlunos; i++){
            int tempoEntrada = e.nextInt(3)+1;
            Aluno aluno = new Aluno(pontoParada.posicaoLivre(), tempoSimulacao, tempoEntrada);
            mapa.adicionarItem(aluno);
            pontoParada.montarFila(aluno);
        }
    }

    private void movimentarFila(int tempoSimulacao, PontoParada pontoParada){
        if(mamute.estaDisponivel(tempoSimulacao) && !pontoParada.estaVazia() && pontoParada.posicaoEntrada()){
            Aluno aluno = pontoParada.removerAluno();
            mapa.removerItem(aluno);
            mamute.adicionarAluno(aluno, tempoSimulacao);
            mamute.setTempoProximaEntrada(aluno.getTempoEntrada(), tempoSimulacao);
            System.out.println("removeu");
            
        }
    }
}