package br.ufla.gac111.grupo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

public class PontoParada extends Item {
    private ArrayList<Aluno> filaAlunos;
    private final int capacidade;

    public PontoParada(Localizacao localizacao, int altura) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());
        filaAlunos = new ArrayList<Aluno>();
        capacidade = altura - 5;
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
        if(estaVazia())
            return false;
        return filaAlunos.get(filaAlunos.size()-1).getLocalizacaoAtual().getY() >= capacidade;
    }

    public Localizacao posicaoLivre() {
        if(!estaVazia()){
            Aluno ultimoAluno = filaAlunos.get(filaAlunos.size()-1);
            return new Localizacao(ultimoAluno.getLocalizacaoAtual().getX(),ultimoAluno.getLocalizacaoAtual().getY()+1);
        }
        return new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY());
    }

    public boolean estaAtualizada() {
        if (!estaVazia()) {
            Localizacao ultimoAluno = new Localizacao(posicaoLivre().getX(), posicaoLivre().getY() - 1);
            return filaAlunos.get(filaAlunos.size() - 1).getLocalizacaoAtual().equals(ultimoAluno);
        }
        return true;
    }

    public boolean atualizaFila(int i) {
        Aluno aluno = filaAlunos.get(i);
        //System.out.print("aluno " + i + ": ");
        //System.out.println(aluno.getLocalizacaoAtual());
        //System.out.println("posicao meta: ");
        //System.out.println(new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY()+i));
        Localizacao localizacao = new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY()+i);
        if (!aluno.getLocalizacaoAtual().equals(localizacao)) {
            aluno.executarAcao();
            System.out.println("diferente");
            return true;
        }
        return false;
    }

    public int tamanhoFila() {
        return filaAlunos.size();
    }

}
