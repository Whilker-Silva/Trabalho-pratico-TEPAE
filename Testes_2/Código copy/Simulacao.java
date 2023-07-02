import java.util.Random;
import java.util.Random;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Item aluno;
    private Mamute mamute;
    private PontoParada pontoParada;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private Fila fila;
    private int numPassos;
    private Random rand = new Random();
    private int tempoEntrada;
    private int tempoSimulacaoAtual;
    private Predio predio1;
    private Predio predio2;
    private Predio predio3;
    private Predio predio4;
    

    public Simulacao() {
        mapa = new Mapa(); //Cria o mapa


        mamute = new Mamute(new Localizacao(14, 14)); //Cria o mamute
        mamute.realizarPercurso(new Localizacao(14,1));
        mapa.adicionarItem(mamute);

        predio1 = new Predio(new Localizacao(0,0)  , "Imagens/Predio1.jpg"); //cria os predios
        mapa.adicionarItem(predio1);
        predio2 = new Predio(new Localizacao(1,0)  , "Imagens/Predio2.png");
        mapa.adicionarItem(predio2);
        predio3 = new Predio(new Localizacao(0,2)  , "Imagens/Predio3.jpg");
        mapa.adicionarItem(predio3);
        predio4 = new Predio(new Localizacao(1,2)  , "Imagens/Predio4.jpg");
        mapa.adicionarItem(predio4);


        pontoParada = new PontoParada(new Localizacao(12, 13)); //Cria o ponto de parada
        mapa.adicionarItem(pontoParada);

        janelaSimulacao = new JanelaSimulacao(mapa);//Cria a janela de Simulacao


        aluno = new Aluno(new Localizacao(1,14),0,0);//Cria o aluno
        aluno.setLocalizacaoDestino(new Localizacao(12,14));//Define a posicao destino do aluno
        mapa.adicionarItem(aluno);//Inicializando o mapa com o aluno
        


        tempoSimulacaoAtual = 0;
        
    }


    




    public void gerarAlunos(){
        while(tempoSimulacaoAtual + gerarTempo() <= numPassos){
            adicionarAluno(tempoEntrada);
            tempoSimulacaoAtual += (rand.nextInt(3)+1);
        }

        fila.lerFila();
        System.out.println("Fim!");
    }



    public int gerarTempo(){
        tempoEntrada = 1 + rand.nextInt(4);
        return tempoEntrada;
    }




    public void adicionarAluno(int tempoEntrada){
        pontoParada.montarFila(new Aluno(new Localizacao(0, 0), tempoSimulacaoAtual, tempoEntrada));
    }

   


    







    public void executarSimulacao(int numPassos){
        this.numPassos = numPassos;
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

    public void criarFila(){
        fila = new Fila();

    }

}
