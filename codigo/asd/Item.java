import java.awt.Image;

public abstract class Item {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Item(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
}
