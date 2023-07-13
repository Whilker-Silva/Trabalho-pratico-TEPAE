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

    public int getPrimeiroTempoEntrada() {
        return getPrimeiroAluno().getTempoEntrada();
    }

    public void embarcarAluno() {
        getPrimeiroAluno().setEmbarcou();
    }

    public void adicionarAluno(Aluno aluno) {
        filaAlunos.add(aluno);
    }

    public boolean estaVazia() {
        return filaAlunos.isEmpty();
    }

    public boolean estaCheia() {
        if (estaVazia())
            return false;
        return filaAlunos.get(filaAlunos.size() - 1).getLocalizacaoAtual().getY() >= CAPACIDADE;
    }

    public Localizacao posicaoLivre() {
        if (!estaVazia()) {
            Aluno ultimoAluno = filaAlunos.get(filaAlunos.size() - 1);
            return new Localizacao(ultimoAluno.getLocalizacaoAtual().getX(),
                    ultimoAluno.getLocalizacaoAtual().getY() + 1);
        }
        return new Localizacao(getLocalizacaoAtual().getX() + 1, getLocalizacaoAtual().getY());
    }

    public boolean atualizaFila(int i) {
        Aluno aluno = filaAlunos.get(i);
        Localizacao localizacao = new Localizacao(getLocalizacaoAtual().getX() + 1, getLocalizacaoAtual().getY() + i);
        if (!aluno.getLocalizacaoAtual().equals(localizacao)) {
            aluno.executarAcao();
            return true;
        }
        return false;
    }

    public int tamanhoFila() {
        return filaAlunos.size();
    }

    public List<Aluno> getFila() {
        return Collections.unmodifiableList(filaAlunos);
    }
}
