package br.ufla.gac111.grupo3;

import java.awt.Image;
/**
 * Representa todos o itens presentes na simulação
 * @author Whilker Henrique Dos Santos Silva
 */

public abstract class Item {
    // Atributos
    private Localizacao localizacaoAtual;
    private Image imagem;

    /**
     * Construtor de objetos do tipo Item
     * <p>
     * Atribui uma localizacao para os atributos localizacaoAtual.
     * @param localizacaoAtual
     */

    public Item(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Atribui uma imagem para o Item
     * @param imagem Image que será atribuida ao objeto
     */

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    /**
     * Retorna a imagem atribuída ao objeto da classe Item
     * @return Image - imagem do Item
     */

    public Image getImagem() {
        return imagem;
    }

    /**
     * Atribui à variável localizacaoAtual uma nova Localizacao
     * @param localizacaoAtual - Localizacao que vai ser atribuida a localizacaoAtual do objeto da classe Item
     */

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Retorna a localização atual do objeto
     * @return Localizacao - retorna a localizacaoAtual do Item
     */

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

}
