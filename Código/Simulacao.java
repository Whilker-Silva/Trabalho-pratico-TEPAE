import java.util.LinkedList;
import java.util.Queue;
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
    private Queue<Aluno> filaSimulacao;
    
    public Simulacao() {
        mapa = new Mapa();
        filaSimulacao = new LinkedList<>();

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
        criarFilaSimulacao(tempoSimulacao, pontoEmbarque);
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i);
            esperar(500);
        }        
    }

    private void executarUmPasso(int tempoSimulacao) {
        
        //aluno.executarAcao(tempoSimulacao);

        montarFila(tempoSimulacao, pontoEmbarque);

        movimentarFila(tempoSimulacao, pontoEmbarque);

        if(mamute.estaCheio())
            mamute.realizarPercurso(tempoSimulacao, pontoEmbarque.getLocalizacaoAtual(), pontoDesembarque.getLocalizacaoAtual());

        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void criarFilaSimulacao(int tempoSimulacao, PontoParada pontoParada){
        Random e = new Random();
        
        int tempoChegada = e.nextInt(5);
        int tempoEntrada = e.nextInt(3)+1;

        while (tempoChegada + tempoEntrada <= (tempoSimulacao - mamute.getTEMPO())){
            Aluno aluno = new Aluno(new Localizacao(0, 0), tempoChegada, tempoEntrada);
            aluno.setLocalizacaoDestino(new Localizacao(pontoParada.getLocalizacaoAtual().getX()+1, pontoParada.getLocalizacaoAtual().getY()));

            filaSimulacao.add(aluno);
            tempoChegada += e.nextInt(5);
            tempoEntrada = e.nextInt(3)+1;
            
            System.out.println(aluno);
        }
    }

    private void montarFila(int tempoSimulacao, PontoParada pontoParada){
        while(!filaSimulacao.isEmpty() && filaSimulacao.peek().getTempoChegada() <= tempoSimulacao) {
            Aluno aluno = filaSimulacao.peek();
            aluno.setLocalizacaoAtual(pontoParada.posicaoLivre());
            mapa.adicionarItem(aluno);
            pontoParada.montarFila(aluno);
            filaSimulacao.poll();
            
        }
    }

    private void movimentarFila(int tempoSimulacao, PontoParada pontoParada){
        if(mamute.estaDisponivel(tempoSimulacao) && !pontoParada.estaVazia()){
            Aluno aluno = pontoParada.removerAluno();
            mapa.removerItem(aluno);
            mamute.adicionarAluno(aluno);
        System.out.println("removeu");
        }
    }

    
}
