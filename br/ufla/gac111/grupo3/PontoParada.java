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
    private final int capacidade;

    public PontoParada(Localizacao localizacao, int altura) {

        super(localizacao);
        filaAlunos = new ArrayList<Aluno>();
        capacidade = altura - 5;

        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());
    }

    public Aluno removerAluno() {
        Aluno removido = filaAlunos.remove(0);
        return removido;
    }

    public boolean alunoEmbarcado() {
        if (filaAlunos.isEmpty())
            return false;
        return getPrimeiroAluno().getEmbarcou();
    }

    public Aluno getPrimeiroAluno() {
        return filaAlunos.get(0);
    }

    public void embarcarAluno() {
        if (!filaAlunos.isEmpty())
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
        return filaAlunos.get(filaAlunos.size() - 1).getLocalizacaoAtual().getY() >= capacidade;
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
        System.out.print((i+1) + ": ");
        System.out.println(aluno.getLocalizacaoAtual());
        // System.out.print("aluno " + i + ": ");
        // System.out.println(aluno.getLocalizacaoAtual());
        // System.out.println("posicao meta: ");
        // System.out.println(new Localizacao(getLocalizacaoAtual().getX()+1,
        // getLocalizacaoAtual().getY()+i));
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

}
