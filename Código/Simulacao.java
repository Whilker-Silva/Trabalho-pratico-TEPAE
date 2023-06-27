/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Aluno aluno;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Mamute mamute;
    private PontoParada pontoParada;
    
    public Simulacao() {
        mapa = new Mapa();
        aluno = new Aluno(new Localizacao(5,34));//Cria um veiculo em uma posicao aleatoria
        aluno.setLocalizacaoDestino(new Localizacao(5,10));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(aluno);//Inicializando o mapa com o veículo
        janelaSimulacao = new JanelaSimulacao(mapa);

        mamute = new Mamute();
        pontoParada = new PontoParada();
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(500);
        }        
    }

    private void executarUmPasso() {
        mapa.removerItem(aluno);
        aluno.executarAcao();
        mapa.adicionarItem(aluno);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
