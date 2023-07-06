import javax.swing.ImageIcon;
// .....
/**
 * Representa os veiculos da simulacao.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Aluno extends ItemDinamico {
    private int tempoEntrada;
    private boolean embarcou;
    private Localizacao localizacaoAtual;

    public Aluno(Localizacao localizacaoAtual, Localizacao localizacaoDestino, int tempoEntrada) {
        super(localizacaoAtual, localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Pessoa.png")).getImage());
        this.tempoEntrada = tempoEntrada;
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setPosiacoFila(Localizacao localizacaoAtual) {
        // fazer o set posicaoFila
    }

    public Localizacao getProximalocalizao() {
        Localizacao destino = getLocalizacaoDestino();
        return (destino != null) ? getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino()) : null;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
    }

    public boolean chegouDestino() {
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }

    public void setEmbarcou() {
        embarcou = true;
    }

    public boolean getEmbarcou() {
        return embarcou;
    }

}
