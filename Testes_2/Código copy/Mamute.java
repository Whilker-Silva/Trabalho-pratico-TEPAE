import java.awt.Image;
import javax.swing.ImageIcon;

public class Mamute extends Item{
    private int tempoProximaParada;
    private static final int CAPACIDADE = 10;

    public Mamute(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute2.0-removebg-preview.png")).getImage());
    }

    public boolean estaDisponivel(){
        return false;
    }

    public void realizarPercurso(Localizacao destino){
        setLocalizacaoDestino(destino);
    }
}