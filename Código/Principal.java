/**
 * Classe que contem o método main, responsável apenas por instanciar o objeto
 * Simulacao e chamar o método executar a simulação * 
 * @author Luiz Merschmann
 */
public class Principal {

    public static void main(String[] args) {
        Simulacao simulador = new Simulacao();
        simulador.executarSimulacao(100);
    }
}
