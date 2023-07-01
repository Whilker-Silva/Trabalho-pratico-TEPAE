import java.util.Random;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Aluno aluno;
    private Mamute mamute;
    private PontoParada pontoParada;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();
        aluno = new Aluno(new Localizacao(1,10),1,2);//Cria um veiculo em uma posicao aleatoria
        aluno.setLocalizacaoDestino(new Localizacao(1,1));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(aluno);//Inicializando o mapa com o veículo

        mamute = new Mamute(new Localizacao(1, 0));
        mapa.adicionarItem(mamute);

        pontoParada = new PontoParada(new Localizacao(0, 1));
        mapa.adicionarItem(pontoParada);
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        criarFila(numPassos);
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(500);
        }        
    }

    private void executarUmPasso() {
        mapa.removerItem(aluno);
        aluno.executarAcao();
        mapa.adicionarItem(aluno);

        mapa.removerItem(mamute);
        mamute.executarAcao();
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

    private void criarFila(int numPassos){
        Random e = new Random();
        
        int tempoChegada = e.nextInt(5)+1;
        int tempoEntrada = e.nextInt(3)+1;

        while (tempoChegada + tempoEntrada <= (numPassos - mamute.getTEMPO())){
            Aluno aluno = new Aluno(new Localizacao(1, 20), tempoChegada, tempoEntrada);

            pontoParada.montarFila(aluno);
            tempoEntrada = e.nextInt(3)+1;
            tempoChegada += e.nextInt(3)+1;
            System.out.println(aluno);
        }
    }
}
