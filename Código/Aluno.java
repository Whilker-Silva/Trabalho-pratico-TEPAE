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
}
