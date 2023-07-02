import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Aluno extends Item{
    private int tempoChegada;
    private int tempoEntrada;

    public Aluno(Localizacao localizacao, int tempoChegada, int tempoEntrada){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Pessoa.png")).getImage());
        this.tempoChegada = tempoChegada;
        this.tempoEntrada = tempoEntrada;
    }

    public String toString(){
        return "\nTempo Chegada:" + tempoChegada + " Tempo Entrada:" + tempoEntrada;
    }

    public Localizacao getProximalocalizao(){
        Localizacao destino = getLocalizacaoDestino();
            return (destino != null) ? getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino()) : null;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
    }
    
}
