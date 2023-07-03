import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;



public class Mamute extends Item{
    private int tempoProximaParada;
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 5;
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

        executarAcao(tempoSimulacao);
            
        if(getLocalizacaoAtual().equals(localizacaoPonto1)) 
            alteraDestino(localizacaoPonto2);
        else if(getLocalizacaoAtual().equals(localizacaoPonto2)) 
            alteraDestino(localizacaoPonto1);
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

    private void alteraDestino(Localizacao localizacao){
        Localizacao destino = new Localizacao(localizacao.getX()+1, localizacao.getY()-1);
        setLocalizacaoDestino(destino);
    }

}