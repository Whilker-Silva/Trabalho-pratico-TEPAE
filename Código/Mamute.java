import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

public class Mamute extends ItemDinamico {
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 5    ;
    private final int velocidade = 2;
    private Deque<Aluno> pilhaAlunos;

    public Mamute(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual, localizacaoDestino);
        pilhaAlunos = new LinkedList<>();
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
    }

   
    public boolean estaDisponivel(int tempoSimulacao, PontoParada pontoParada) {
        if ((!estaCheio()) && (tempoProximaEntrada <= tempoSimulacao) && estaPosicionado(definePosicao(pontoParada.getLocalizacaoAtual()))) {
            return true;
        }
        return false;
    }

    public void realizarPercurso(int tempoSimulacao, Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        
        for (int i = 0; i < velocidade; i++) 
            executarAcao();
        
        if(chegouDestino())
            pilhaAlunos.clear();

        if (estaPosicionado(definePosicao(localizacaoPonto1)))
            setLocalizacaoDestino(definePosicao(localizacaoPonto2));
        else if (estaPosicionado(definePosicao(localizacaoPonto2)))
            setLocalizacaoDestino(definePosicao(localizacaoPonto1));
    }

    private Localizacao definePosicao(Localizacao localizacao) {
        return new Localizacao(localizacao.getX() + 1, localizacao.getY() - 1);
    }

    public void embarcarAluno(Aluno aluno, int tempoSimulacao){
        pilhaAlunos.add(aluno);
    }

    public boolean estaPosicionado(Localizacao pontoParada){
        return getLocalizacaoAtual().equals(pontoParada);
    }

    public boolean estaCheio() {
        return pilhaAlunos.size() == CAPACIDADE;
    }

    public int setTempoPercurso(Localizacao localizacao1, Localizacao localizacao2) {
        int tempo = Math.abs(localizacao2.getX() - localizacao1.getX())
                + Math.abs(localizacao2.getY() - localizacao1.getY());
        return (tempo < 0) ? tempo * (-1) : tempo;
    }

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao) {
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
    }

    public boolean chegouDestino(){
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }

}