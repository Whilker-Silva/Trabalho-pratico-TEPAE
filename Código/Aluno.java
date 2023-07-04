import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Aluno extends ItemDinamico {
    private int tempoEntrada;

    public Aluno(Localizacao localizacaoAtual, Localizacao localizacaoDestino, int tempoEntrada) {
        super(localizacaoAtual, localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Pessoa.png")).getImage());
        this.tempoEntrada = tempoEntrada;
    }

    public Localizacao getProximalocalizao() {
        Localizacao destino = getLocalizacaoDestino();
        return (destino != null) ? getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino()) : null;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
    }

}
