import java.awt.Image;

/**
 * Item é uma classe abstrata que reprensenta todos o itens presentes na
 * simulação
 */

public abstract class Item {
    // Atributos
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Item(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }
}
