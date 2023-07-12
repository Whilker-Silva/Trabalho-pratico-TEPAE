package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;
import java.util.Deque;
import java.util.LinkedList;

public class Mamute extends ItemDinamico {
    private int tempoProximaEntrada;
    private final int CAPACIDADE = 10;
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

    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        if(estaCheio()){
            for (int i = 0; i < velocidade; i++) 
                executarAcao();
        
            descerAlunos();
            trocaDestino(localizacaoPonto1, localizacaoPonto2);
        }
    }

    private void descerAlunos(){
        if(chegouDestino())
            pilhaAlunos.clear();
    }

    private Localizacao definePosicao(Localizacao localizacao) {
        return new Localizacao(localizacao.getX() + 1, localizacao.getY() - 1);
    }

    private void trocaDestino(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        if(estaPosicionado(definePosicao(localizacaoPonto1)))
            setLocalizacaoDestino(definePosicao(localizacaoPonto2));
        else if(estaPosicionado(definePosicao(localizacaoPonto2)))
            setLocalizacaoDestino(definePosicao(localizacaoPonto1));
    }

    public void embarcarAluno(Aluno aluno){
        pilhaAlunos.add(aluno);
    }

    private boolean estaPosicionado(Localizacao pontoParada){
        return getLocalizacaoAtual().equals(pontoParada);
    }

    public boolean estaCheio() {
        return pilhaAlunos.size() == CAPACIDADE;
    }

    public void setTempoProximaEntrada(int tempoEntrada, int tempoSimulacao) {
        tempoProximaEntrada = tempoSimulacao + tempoEntrada;
    }
}