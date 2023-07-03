import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

public class Mamute extends Item{
    private int tempoProximaParada;
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 1;
    private final int TEMPO = 15;
    private Deque<Aluno> pilhaAlunos;

    public Mamute(Localizacao localizacao){
        super(localizacao);
        pilhaAlunos = new LinkedList<>();
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
    }

    public boolean estaDisponivel(int tempoSimulacao){
        if((!estaCheio()) && (tempoProximaParada <= tempoSimulacao) && (tempoProximaEntrada <= tempoSimulacao)){
            return true;
        }
        return false;
    }

    public void realizarPercurso(int tempoSimulacao, Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        tempoProximaParada = tempoSimulacao + TEMPO;
        pilhaAlunos.clear();
        
        if(getLocalizacaoAtual().equals(defineDestino(localizacaoPonto1))) 
            setLocalizacaoDestino(defineDestino(localizacaoPonto2));
         else if(getLocalizacaoAtual().equals(defineDestino(localizacaoPonto2)))
            setLocalizacaoDestino(defineDestino(localizacaoPonto1));
        
        executarAcao(tempoSimulacao);
    }

    private Localizacao defineDestino(Localizacao localizacao){
        Localizacao destino = new Localizacao(localizacao.getX()+1, localizacao.getY()-1);
        return destino;
    }

    public void adicionarAluno(Aluno aluno, int tempoSimulacao){
        pilhaAlunos.add(aluno);
        tempoProximaEntrada = tempoSimulacao + aluno.getTempoEntrada();
    }

    public int getTEMPO() {
        return TEMPO;
    }
    
    public boolean estaCheio(){
        return pilhaAlunos.size() >= CAPACIDADE;
    }

    

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao){
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
    }

}