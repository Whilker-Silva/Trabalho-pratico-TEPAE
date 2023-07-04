import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PontoParada extends Item{
    private ArrayList<Aluno> filaAlunos;

    public PontoParada(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());
        filaAlunos = new ArrayList<Aluno>();
    }

    public Aluno removerAluno(){
        Aluno removido = filaAlunos.remove(0);
        movimentarFila();
        return removido;
    }

    public void adicionarAluno(Aluno aluno){
        filaAlunos.add(aluno);
    }

    public boolean estaVazia(){
        return filaAlunos.isEmpty();
    }

    public boolean estaCheia(){
        return filaAlunos.size() >= 35;
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
/*
    public boolean posicaoEntrada(){
        Localizacao entrada = new Localizacao(getLocalizacaoAtual().getX()+1, getLocalizacaoAtual().getY());
        if(filaAlunos.peek().getLocalizacaoAtual().equals(entrada))
            return true;
        return false;
    }*/

    private void movimentarFila(){
        for (Aluno a : filaAlunos) {
            while(verificaProximaPosicao(a)){
                a.executarAcao();
                System.out.println(a.getLocalizacaoDestino());
            }
        }
    }

    public boolean estaAtualizada(){
        return filaAlunos.get(filaAlunos.size()-1).chegouDestino();
    }
}

