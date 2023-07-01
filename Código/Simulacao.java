/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Item aluno;
    private Item mamute;
    private Item pontoParada;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();
        aluno = new Aluno(new Localizacao(1,10));//Cria um veiculo em uma posicao aleatoria
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
}
