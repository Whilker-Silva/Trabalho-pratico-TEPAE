package br.ufla.gac111.grupo3;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * <p>
 * A criação do mapa é realizada instanciando uma matriz de Itens cujas
 * dimensões são informadas por paramentro.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Mapa {

    // Atributos
    private Item[][] itens;
    private int largura;
    private int altura;

    /**
     * Construtor de obejetos do tipo mapa
     * <p>
     * Atribui uma largura e altura para os atributos da classe.
     * @param largura - inteiro que representa a largura do mapa
     * @param altura - inteiro que representa a altura do mapa
     */
    
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
    }

    /**
     * Adiicona um item ao mapa na localização atual do item.
     * @param i - item que se deseja adicionar no mapa
     */

    public void adicionarItem(Item i) {
        itens[i.getLocalizacaoAtual().getX()][i.getLocalizacaoAtual().getY()] = i;
    }

    /**
     * Remove um item do mapa na localização atual do item.
     * @param i - item que se deseja remover no mapa
     */

    public void removerItem(Item i) {
        itens[i.getLocalizacaoAtual().getX()][i.getLocalizacaoAtual().getY()] = null;
    }

    /**
     * Remove um item no mapa e o adiciona novamente em sua nova localização, para atualizá-lo.
     * @param i
     */

    public void atualizarMapa(Item i) {
        removerItem(i);
        adicionarItem(i);
    }

    /**
     * Retorna o item presente nas coordenadas informadas por parâmetro.
     * @param x - representa a abscissa da localização
     * @param y - representa a ordenada da localização
     * @return Item - item contino na coordenada 
     */

    public Item getItem(int x, int y) {
        return itens[x][y];
    }

    /**
     * Retorna a largura do mapa.
     * @return int - largura do mapa
     */

    public int getLargura() {
        return largura;
    }

    /**
     * Retorna a altura do mapa.
     * @return int - altura do mapa
     */

    public int getAltura() {
        return altura;
    }

}
