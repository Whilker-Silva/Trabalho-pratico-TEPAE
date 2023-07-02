import javax.swing.ImageIcon;

public class Predio extends Item{
    
    public Predio(Localizacao localizacao, String imagem){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource(imagem)).getImage());
    }
}
