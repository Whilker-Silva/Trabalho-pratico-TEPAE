package br.ufla.gac111.grupo3;

/**
 * Representa o mapa da com todos os itens que participam da simulacao.
 * <p>
 * A criação do mapa é realizada instanciando uma matriz de Itens cujas
 * dimensões são informadas por paramentro
 * <p>
 * As posições da matriz são inciadas por padrão com null, dessa forma para
 * adicionar um item no mapa, basta adicionar um obejto na posição desejada na
 * matriz
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author Whilker Henrique Dos Santos Silva
 */
public class Mapa {

    // Atributos
    private Item[][] itens;
    private int largura;
    private int altura;

    /**
     * Construtor de obejetos do tipo mapa
     * 
     * @param largura
     * @param altura
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
    }

    /**
     * Método para adionar um obejto do tipo Item no Mapa
     * 
     * @param i - Item que deseja adiconar
     */
    public void adicionarItem(Item i) {
        itens[i.getLocalizacaoAtual().getX()][i.getLocalizacaoAtual().getY()] = i;
    }

    /**
     * Método para remover um obejto do tipo Item no Mapa
     * 
     * @param i - item que deseja remover
     */
    public void removerItem(Item i) {
        itens[i.getLocalizacaoAtual().getX()][i.getLocalizacaoAtual().getY()] = null;
    }

    /**
     * Método responsavel por atualizar os itens do Mapa
     * 
     * @param i
     */
    public void atualizarMapa(Item i) {
        removerItem(i);
        adicionarItem(i);
    }

    /**
     * @param x - Posição em ralção ao eixo y
     * @param y - Posição em ralção ao eixo x
     * @return Item da posição informada por paramentro
     */
    public Item getItem(int x, int y) {
        return itens[x][y];
    }

    /**
     * @return Largura do mapa: int
     */
    public int getLargura() {
        return largura;
    }

    /**
     * @return Altura do mapa: int
     */
    public int getAltura() {
        return altura;
    }

}
