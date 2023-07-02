import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Item {
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;

    public Item(Localizacao localizacao){
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
    }

    public void setImagem(Image imagem){
        this.imagem = imagem;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    public void executarAcao(int tempoSimulacao){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    } 
}
