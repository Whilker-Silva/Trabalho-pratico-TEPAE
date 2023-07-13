package br.ufla.gac111.grupo3;

/**
 * Classe que contém o método main, responsável apenas por instanciar o objeto
 * Simulacao e chamar o método para executar a simulação.
 * 
 * @author Mateus Henrique Teixeira
 * @author Pedro Henrique Pigozzi Gomes
 * @author Victor Hugo Daia Lorenzato
 * @author Whilker Hentique Dos Santos Silva
 */
public class Principal {

    public static void main(String[] args) {
        Simulacao simulador;
        simulador = Simulacao.getInstance();
        simulador.executarSimulacao(200,4);
    }
}
