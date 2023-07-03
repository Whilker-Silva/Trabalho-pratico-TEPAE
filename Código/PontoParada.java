import java.util.LinkedList;
import java.util.Queue;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PontoParada extends Item{
    private Queue<Aluno> filaAlunos;

    public PontoParada(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());
        filaAlunos = new LinkedList<Aluno>();
    }

    public Aluno removerAluno(){
        Aluno removido = filaAlunos.poll();
        return removido;
    }

    public void montarFila(Aluno aluno){
        filaAlunos.add(aluno);
    }

    public boolean estaVazia(){
        return filaAlunos.isEmpty();
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

    public Aluno getPrimeiroFila(){
        return filaAlunos.peek();
    }

    public boolean posicaoEntrada(){
        Localizacao entrada = new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY());
        if(filaAlunos.peek().getLocalizacaoAtual().equals(entrada))
            return true;
        return false;
    }
}

