package br.ufla.gac111.grupo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Represena os locais de embarque e desembarque de alunos
 */

public class PontoParada extends Item {

    // Atributos
    private ArrayList<Aluno> filaAlunos;
    private final int CAPACIDADE = 15;

    /**
     * Construtor de objetos do tipo PontoParada
     * <p>
     * Atribui uma localização para o atributo localizacaoAtual.
     * Além disso, inicializa a fila de alunos e define a imagem do ponto de parada através do método setImagem.
     * @param localizacao - Localizacao que será atribuída à localizacaoAtual
     */

    public PontoParada(Localizacao localizacao) {
        super(localizacao);
        filaAlunos = new ArrayList<Aluno>();

        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus2.png")).getImage());
    }

    /**
     * Remove o primeiro aluno da fila de alunos.
     * @return Aluno - aluno removido
     */

    public Aluno removerAluno() {
        Aluno removido = filaAlunos.remove(0);
        return removido;
    }

    /**
     * Verifica se o primeiro aluno da fila já embarcou.
     * @return boolean - true se o primeiro da fila embarcou, se não, retorna false.
     */

    public boolean alunoEmbarcado() {
        return getPrimeiroAluno().getEmbarcou();
    }

    /**
     * Retorna o primeiro aluno da fila.
     * @return Aluno - primeiro aluno
     */
    public Aluno getPrimeiroAluno() {
        return filaAlunos.get(0);
    }

    /**
     * Retorna o tempo de entrada do primeiro aluno da fila de alunos.
     * @return tempo de entrada.
     */

    public int getPrimeiroTempoEntrada() {
        return getPrimeiroAluno().getTempoEntrada();
    }

    /**
     * Muda o atributo de embarcado do primeiro aluno da fila.
     */

    public void embarcarAluno() {
        getPrimeiroAluno().setEmbarcou();
    }

    /**
     * Adiciona alunos na fila.
     * @param aluno - Aluno que se deseja adicionar na fila do ponto de parada
     */

    public void adicionarAluno(Aluno aluno) {
        filaAlunos.add(aluno);
    }

    /**
     * Verifica se a fila de alunos está vazia.
     * @return boolean - true se o Arraylist<Aluno> esstiver vazio, se não, retorna false
     */

    public boolean estaVazia() {
        return filaAlunos.isEmpty();
    }

    /**
     * Verifica se a fila de alunos está cheia, de acordo com a constante de capacidade máxima estabelecida.
     * @return boolean - true se o Arraylist<Aluno> esstiver cheio, se não, retorna false
     */

    public boolean estaCheia() {
        if (estaVazia())
            return false;
        return filaAlunos.get(filaAlunos.size() - 1).getLocalizacaoAtual().getY() >= CAPACIDADE;
    }

    /**
     * Verifica a posicação da fila de alunos que está disponivél
     * <p>
     * Caso a fila esteja vazia, o primeiro aluno será posicionado ao lado do ponto de parada.
     * <p>
     * Caso contrário, a posição do primeiro aluno será referente a posição do último aluno posicionado, de modo
     * a formar a fila.
     * @return Localizacao - a localização que o aluno será posicionado
     */

    public Localizacao posicaoLivre() {
        if (!estaVazia()) {
            Aluno ultimoAluno = filaAlunos.get(filaAlunos.size() - 1);
            return new Localizacao(ultimoAluno.getLocalizacaoAtual().getX(),
                    ultimoAluno.getLocalizacaoAtual().getY() + 1);
        }
        return new Localizacao(getLocalizacaoAtual().getX() + 1, getLocalizacaoAtual().getY());
    }

    /**
     * Atualiza a posição de um aluno na fila
     * <p>
     * Recebe o aluno da fila e cria uma nova localização com as coordenadas x e y, que representa a posição
     * adequada do aluno no ponto de parada. Se a posição do aluno for diferente da posição adequada, ele é movido.
     * @param i - inteiro que representa a posição do aluno
     * @return boolean - true se a localização atual do aluno for diferente da nova
     *         localização. Se não, retorna false.
     */

    public boolean atualizaFila(int i) {
        Aluno aluno = filaAlunos.get(i);
        Localizacao localizacao = new Localizacao(getLocalizacaoAtual().getX() + 1, getLocalizacaoAtual().getY() + i);
        if (!aluno.getLocalizacaoAtual().equals(localizacao)) {
            aluno.executarAcao();
            return true;
        }
        return false;
    }

    /**
     * Verifica o número de alunos presentes no ArrayList.
     * @return int - tamanho da fila de alunos.
     */

    public int tamanhoFila() {
        return filaAlunos.size();
    }

    /**
     * Retorna uma cópia da fila de alunos presente no ArrayList.
     * <p>
     * A fila de alunos não pode ser modificada fora da classe.
     * @return List - cópia da fila de alunos.
     */

    public List<Aluno> getFila() {
        return Collections.unmodifiableList(filaAlunos);
    }
}
