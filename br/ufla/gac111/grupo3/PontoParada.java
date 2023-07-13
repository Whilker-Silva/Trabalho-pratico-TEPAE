package br.ufla.gac111.grupo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Ponto de parada é um item que não se move e
 * representa os pontos de embraque e desembarque do mamute
 * <p>
 * Ponto de parada se diferencia dos demais itens pois nele há uma lista de
 * alunos na qual representa a fila
 */

public class PontoParada extends Item {

    // Atributos
    private ArrayList<Aluno> filaAlunos;
    private final int CAPACIDADE = 15;

    /**
     * Método construtor.
     * Recebe o construtor da classe Item.
     * Inicializa filaAlunos
     * 
     * @param localizacao
     */

    public PontoParada(Localizacao localizacao) {
        super(localizacao);
        filaAlunos = new ArrayList<Aluno>();

        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus2.png")).getImage());
    }

    /**
     * Remove o primeiro aluno da fila de alunos.
     * O primeiro aluno é removido usando o método remove(0) do ArrayList.
     * 
     * @return aluno removido.
     */

    public Aluno removerAluno() {
        Aluno removido = filaAlunos.remove(0);
        return removido;
    }

    /**
     * Método que verifica se o primeiro da fila embarcou.
     * 
     * @return boolean - true se o primeiro da fila embarcou, se não, retorna false.
     */

    public boolean alunoEmbarcado() {
        return getPrimeiroAluno().getEmbarcou();
    }

    public Aluno getPrimeiroAluno() {
        return filaAlunos.get(0);
    }

    /**
     * Método que retorna o tempo de entrada do primeiro aluno da fila de alunos.
     * 
     * @return tempo de entrada.
     */

    public int getPrimeiroTempoEntrada() {
        return getPrimeiroAluno().getTempoEntrada();
    }

    /**
     * Método que marca o primeiro aluno como embarcado.
     * Chama o método setEmbarcou().
     */

    public void embarcarAluno() {
        getPrimeiroAluno().setEmbarcou();
    }

    /**
     * Método que adiciona alunos na fila.
     * 
     * @param aluno
     */

    public void adicionarAluno(Aluno aluno) {
        filaAlunos.add(aluno);
    }

    /**
     * Método que verifica se o ArrayList está vazio.
     * 
     * @return boolean - true se esstiver vazio, se não, retorna false.
     */

    public boolean estaVazia() {
        return filaAlunos.isEmpty();
    }

    /**
     * 
     * @return
     */

    public boolean estaCheia() {
        if (estaVazia())
            return false;
        return filaAlunos.get(filaAlunos.size() - 1).getLocalizacaoAtual().getY() >= CAPACIDADE;
    }

    /**
     * 
     * @return
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
     * Método que atualiza a posição de um aluno na fila.
     * Recebe o aluno da fila e cria uma nova localização com as coordenadas x e y.
     * Verifica se a localização atual do aluno é diferente da nova localização.
     * 
     * @param i
     * @return boolean - true se a localização atual do aluno for diferente da nova
     *         localização, chamando assim o método executarAcao(). Se não, retorna
     *         false.
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
     * Método para verificar o número de alunos presentes no ArrayList.
     * 
     * @return tamanho da fila de alunos.
     */

    public int tamanhoFila() {
        return filaAlunos.size();
    }

    /**
     * Método que retorna uma cópia da fila de alunos presente no ArrayList.
     * A fila de alunos não pode ser modificada fora da classe.
     * 
     * @return cópia da fila de alunos.
     */

    public List<Aluno> getFila() {
        return Collections.unmodifiableList(filaAlunos);
    }
}
