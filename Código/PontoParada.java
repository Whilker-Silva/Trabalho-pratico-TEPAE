import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

public class PontoParada extends Item{
    private ArrayList<Aluno> filaAlunos;
    private final int CAPACIDADE = 35;

    public PontoParada(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());
        filaAlunos = new ArrayList<Aluno>();
    }

    public Aluno removerAluno(){
        Aluno removido = filaAlunos.remove(0);
        return removido;
    }


    public boolean alunoEmbarcado(){
        if(filaAlunos.isEmpty())
            return false;
        return getPrimeiroAluno().getEmbarcou();
    }

    public Aluno getPrimeiroAluno(){
        return filaAlunos.get(0);
    }

    public void embarcarAluno(){
        if(!filaAlunos.isEmpty())
            getPrimeiroAluno().setEmbarcou();
    }

    public void adicionarAluno(Aluno aluno){
        filaAlunos.add(aluno);
    }

    public boolean estaVazia(){
        return filaAlunos.isEmpty();
    }

    public boolean estaCheia(){
        return filaAlunos.size() >= CAPACIDADE;
    }

    public boolean verificaProximaPosicao(Aluno aluno){
        if(aluno.getProximalocalizao() != null){
            for (Aluno a : filaAlunos) {
                if(a.getLocalizacaoAtual().equals(aluno.getProximalocalizao())){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public Localizacao posicaoLivre(){
        return new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY()+filaAlunos.size());
    }

    public boolean estaAtualizada(){
        if(!filaAlunos.isEmpty()){
            Aluno primeiroFila = filaAlunos.get(0);
            if(primeiroFila.chegouDestino()){
                Localizacao ultimoAluno = new Localizacao(posicaoLivre().getX(), posicaoLivre().getY()-1); 
                return filaAlunos.get(filaAlunos.size()-1).getLocalizacaoAtual().equals(ultimoAluno);
            }
            return false;
        }
        return true;
    }

    public void atualizaFila(int i){
        filaAlunos.get(i).executarAcao();
    }

    public int tamanhoFila(){
        return filaAlunos.size();
    }

}

