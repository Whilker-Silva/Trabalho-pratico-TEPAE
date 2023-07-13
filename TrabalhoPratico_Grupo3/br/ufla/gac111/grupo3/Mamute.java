package TrabalhoPratico_Grupo3.br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Responsável pelo comportamento do mamute, o qual se movimenta entre
 * dois pontos de parada e transporta alunos.
 * @author Mateus Henrique Teixeira
 * @author Victor Hugo Daia Lorenzato
 * @author Whilker Henriqur Dos Santo Silva
 */

public class Mamute extends ItemDinamico implements MovimentacaoPercurso {

    //Atributos
    private int tempoProximaEntrada;
    private Deque<Aluno> pilhaAlunos;
    private final int CAPACIDADE = 10;
    private final int VELOCIDADE = 2;

    /**
     * Construtor de objetos do tipo Mamute
     * <p>
     * Atribui uma localizacao para os atributos localizacaoAtual e para localizacaoDestino.
     * Além disso, inicializa a pilha de alunos e define a imagem do mamute através do método setImagem.
     * @param localizacaoAtual - Localizacao que representa a posição atual
     * @param localizacaoDestino - Localizacao que representa a posição de destino
     */

    public Mamute(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual, localizacaoDestino);
        pilhaAlunos = new LinkedList<>();
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
    }

    /**
     * Método que verifica se o mamute está disponível para receber alunos
     * <p>
     * Para isso, verifica se o mamute não está cheio e se o tempo da próxima entrada é menor
     * ou igual ao tempo de simulação.
     * Além disso, observa se o mamute está posicionado no ponto de parada desejado.
     * @param tempoSimulacao - inteiro que representa o tempo atual da simulação
     * @param pontoParada - PontoParada que representa o ponto que se deseja verificar a disponibilidade do mamute
     * @return boolean - true se o mamute estiver disponível, se não,
     *         retorna false.
     */

    public boolean estaDisponivel(int tempoSimulacao, PontoParada pontoParada) {
        if ((!estaCheio()) && (tempoProximaEntrada <= tempoSimulacao)
                && estaPosicionado(definePosicao(pontoParada.getLocalizacaoAtual()))) {
            return true;
        }
        return false;
    }

    /**
     * Realiza o percurso do mamute entre dois pontos
     * <p>
     * Se o mamute etiver cheio, faz com que o mamute mude sua localização no sentido de seu destino.
     * Além disso, esvazia o mamute e prepara-o para ir ao próximo ponto de parada.  
     * @param localizacaoPonto1 - Localizacao do primeiro ponto
     * @param localizacaoPonto2 - Localizacao do segundo ponto
     */

    @Override
    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        if (estaCheio()) {
            for (int i = 0; i < VELOCIDADE; i++)
                executarAcao();

            descerAlunos();
            trocaDestino(definePosicao(localizacaoPonto1), definePosicao(localizacaoPonto2));
        }
    }

    /**
     * Remove todos os alunos do mamute se estivere no destino
     * <p>
     * Verifica se o mamute chegou no destino. Depois disso limpa a pilha de alunos do mamute.
     */

    private void descerAlunos() {
        if (chegouDestino())
            pilhaAlunos.clear();
    }

    /**
     * Recebe uma localização e retorna uma nova localização
     * <p>
     * Para o cálculo da posição do mamute, adiciona-se 1 à coordenada x e subtrai 1 da coordenada y da localização
     * do ponto de parada informado.
     * @param localizacao - Localizacao do ponto de parada
     * @return Localizacao - retorna a localização do mamute referente ao ponto de parada informado
     */

    private Localizacao definePosicao(Localizacao localizacao) {
        return new Localizacao(localizacao.getX() + 1, localizacao.getY() - 1);
    }

    /**
     * Adiciona um aluno no mamute.
     * @param aluno - Aluno que será adicionado na pilha de alunos do mamute
     */

    public void embarcarAluno(Aluno aluno) {
        pilhaAlunos.add(aluno);
    }

    /**
     * Verifica se o mamute está cheio
     * <p>
     * Verifica se o tamanho da pilha de alunos é igual a ao atributo capacidade.
     * 
     * @return boolean - true se estiver cheio, se não, retorna false.
     */

    public boolean estaCheio() {
        return pilhaAlunos.size() == CAPACIDADE;
    }

    /**
     * Define o tempo da próxima entrada no mamute
     * <p>
     * Calcula o tempo da próxima entrada levando em consideração o tempo de entrada do aluno e o tempo de
     * simulação.
     * @param tempoEntrada - inteiro que indica o tempo de entrada do aluno
     * @param tempoSimulacao - inteiro que indica o tempo atual da simulação
     */

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao) {
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
    }
}