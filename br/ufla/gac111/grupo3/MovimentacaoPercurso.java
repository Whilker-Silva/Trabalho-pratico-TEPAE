package br.ufla.gac111.grupo3;

/**
 * Interface de objetos dinâmicos que realizam percurso de ida e volta.
 * @author Pedro Henrique Pigozzi Gomes
 */
public interface MovimentacaoPercurso {

    /**
     * Ação de realizar um percurso entre dois pontos
     * @param localizacaoPonto1
     * @param localizacaoPonto2
     */

    public abstract void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2);
}
