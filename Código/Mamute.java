import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

public class Mamute extends ItemDinamico {

    private int tempoProximaParada;
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 5    ;
    private int tempo;
    private Deque<Aluno> pilhaAlunos;

    public Mamute(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual, localizacaoDestino);
        pilhaAlunos = new LinkedList<>();
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
        tempo = setTempoPercurso(localizacaoAtual, localizacaoDestino);
    }

   
    public boolean estaDisponivel(int tempoSimulacao) {
        if ((!estaCheio()) && (tempoProximaParada <= tempoSimulacao) && (tempoProximaEntrada <= tempoSimulacao)) {
            return true;
        }
        return false;
    }

    public void realizarPercurso(int tempoSimulacao, Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        tempoProximaParada = tempoSimulacao + tempo;
        pilhaAlunos.clear();

        if (getLocalizacaoAtual().equals(defineDestino(localizacaoPonto1)))
            setLocalizacaoDestino(defineDestino(localizacaoPonto2));
        else if (getLocalizacaoAtual().equals(defineDestino(localizacaoPonto2)))
            setLocalizacaoDestino(defineDestino(localizacaoPonto1));

        executarAcao();
    }

    private Localizacao defineDestino(Localizacao localizacao) {
        return new Localizacao(localizacao.getX() + 1, localizacao.getY() - 1);

    }

    public void embarcarAluno(Aluno aluno, int tempoSimulacao){
        pilhaAlunos.add(aluno);
    }

    public int getTempo() {
        return tempo;
    }

    public boolean estaCheio() {
        return pilhaAlunos.size() >= CAPACIDADE;
    }

    public int setTempoPercurso(Localizacao localizacao1, Localizacao localizacao2) {
        int tempo = Math.abs(localizacao2.getX() - localizacao1.getX())
                + Math.abs(localizacao2.getY() - localizacao1.getY());
        return (tempo < 0) ? tempo * (-1) : tempo;
    }

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao) {
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
        System.out.println(tempoProximaEntrada);
    }

    public boolean chegouDestino(){
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }

}