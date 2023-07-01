import java.awt.Image;
import javax.swing.ImageIcon;

public class Mamute extends Item{
    private int tempoProximaParada;
    private static final int CAPACIDADE = 10;
    private final int TEMPO = 15;

    public Mamute(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Mamute.png")).getImage());
    }

    public boolean estaDisponivel(){
        return false;
    }

    public void realizarPercurso(){

    }

    public int getTEMPO() {
        return TEMPO;
    }
    
}