import java.util.Random;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Aluno aluno;
    private Mamute mamute;
    private PontoParada pontoEmbarque;    
    private PontoParada pontoDesembarque;  
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();
        aluno = new Aluno(new Localizacao(1,10),1,2);//Cria um veiculo em uma posicao aleatoria
        aluno.setLocalizacaoDestino(new Localizacao(1,1));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(aluno);//Inicializando o mapa com o veículo

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
        criarFila(tempoSimulacao);
        for (int i = 0; i < tempoSimulacao; i++) {
            executarUmPasso(i);
            esperar(500);
        }        
    }

    private void executarUmPasso(int tempoSimulacao) {
        
        aluno.executarAcao(tempoSimulacao);

        


        mapa.removerItem(mamute);
        mamute.realizarPercurso(tempoSimulacao, pontoEmbarque.getLocalizacaoAtual(), pontoDesembarque.getLocalizacaoAtual());
        mapa.adicionarItem(mamute);

        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void criarFila(int tempoSimulacao){
        Random e = new Random();
        
        int tempoChegada = e.nextInt(5)+1;
        int tempoEntrada = e.nextInt(3)+1;

        while (tempoChegada + tempoEntrada <= (tempoSimulacao - mamute.getTEMPO())){
            Aluno aluno = new Aluno(new Localizacao(1, 14), tempoChegada, tempoEntrada);

            pontoEmbarque.montarFila(aluno);
            tempoChegada += e.nextInt(5)+1;
            tempoEntrada = e.nextInt(3)+1;
            
            System.out.println(aluno);
        }
    }
}
