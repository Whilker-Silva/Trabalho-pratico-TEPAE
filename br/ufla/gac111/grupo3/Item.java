package br.ufla.gac111.grupo3;

import java.awt.Image;
/**
 * Item é uma classe abstrata que reprensenta todos o itens presentes na
 * simulação
 * @author Pedro Henrique Pigozzi Gomes
 */
public abstract class Item {
    // Atributos
    private Localizacao localizacaoAtual;
    private Image imagem;

    // Atribui uma localizacao ao atributo localizacaoAtual
    public Item(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Metodo que atribui imagem
     * 
     * @param imagem Image que será atribuida ao objeto
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    /**
     * Metodo que retorna uma imagem
     * 
     * @return Image - imagem do Item
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * Metodo que Atribui a variavel localizacaoAtual nova Localizacao
     * 
     * @param localizacaoAtual - Localizacao que vai ser atribuida a localização do
     *                         objeto
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Metodo que retorna a Localizacao do objeto
     * 
     * @return Localizacao - retorna a localizacao do item no momento em que o
     *         metodo foi chamado
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

}
