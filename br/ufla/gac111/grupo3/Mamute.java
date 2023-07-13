package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Responsável pelo comportamento do mamute, onde o mesmo se movimenta entre
 * dois pontos
 * e transporta alunos.
 */
public class Mamute extends ItemDinamico implements MovimentacaoPercurso {
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 10;
    private final int velocidade = 2;
    private Deque<Aluno> pilhaAlunos;

    /**
     * Inicializa um objeto da classe mamute.
     * Inicializa a variável pilha de alunos.
     * Define a imagem do mamute através do método setImagem.
     * 
     * @param localizacaoAtual
     * @param localizacaoDestino
     */

    public Mamute(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual, localizacaoDestino);
        pilhaAlunos = new LinkedList<>();
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
    }

    /**
     * Método que verifica se o mamute está disponível para receber alunos.
     * Verifica se o mamute não está cheio e se o tempo da próxima entrada é menor
     * ou igual ao tempo de simulação.
     * Verifica também se o mmamute está posicionado no ponto de parada.
     * 
     * @param tempoSimulacao
     * @param pontoParada
     * @return boolean - true se todas as verificaões forem verdadeiras, se não,
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
     * Método que realiza o percurso do mamute entre dois pontos.
     * Se o mamute etiver cheio, executa um loop que chama o método executarAcao().
     * Após o loop, o método descerAluno() é chamado.
     * Em seguida, chama-se trocaDestino() para alterar a localização, ou seja, o
     * mamute ir para o outro ponto.
     * 
     * @param localizacaoPonto1
     * @param localizacaoPonto2
     */

    @Override
    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        if (estaCheio()) {
            for (int i = 0; i < velocidade; i++)
                executarAcao();

            descerAlunos();
            trocaDestino(definePosicao(localizacaoPonto1), definePosicao(localizacaoPonto2));
        }
    }

    /**
     * Método que remove todos os alunos do mamute se estivere no destino.
     * Verifica se o mamute cchegou no destino chamando o método chegouDestino().
     * Se chegou, limpa a pilha de alunos do mamute.
     */

    private void descerAlunos() {
        if (chegouDestino())
            pilhaAlunos.clear();
    }

    /**
     * Recebe uma localização e retorna uma nova localização.
     * Addiciona 1 à coordenada x e subtrai 1 da coordenada y.
     * 
     * @param localizacao
     * @return Localizacao - retorna uma nova localização.
     */

    private Localizacao definePosicao(Localizacao localizacao) {
        return new Localizacao(localizacao.getX() + 1, localizacao.getY() - 1);
    }

    /**
     * Método que adiciona aluno no mamute.
     * Recebe um objeto da classe Aluno como parâmetro e adiciona esse aluno na
     * pilha de alunoas do mamute.
     * 
     * @param aluno
     */

    public void embarcarAluno(Aluno aluno) {
        pilhaAlunos.add(aluno);
    }

    /**
     * Método que verifica se o mamute está cheio.
     * Verifica se o tamanho da pilha de alunos é igual a capacidade máxima de 10.
     * 
     * @return boolean - true se estiver cheio, se não, retorna false.
     */

    public boolean estaCheio() {
        return pilhaAlunos.size() == CAPACIDADE;
    }

    /**
     * Método que define o tempo da próxima entrada no mamute.
     * Calcula o tempo da próxima entrada somando o tempo de entrada ao tempo de
     * simulação.
     * O resultado da soma é atribuido à variável.
     * 
     * @param tempoEntrada
     * @param tempoSimulacao
     */

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao) {
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
    }
}