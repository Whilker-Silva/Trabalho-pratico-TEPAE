package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Classe responsável por aparatar o mapa de simulação
 * adicionando imagens no mapa de simulação.
 */
public class Decoracao extends Item {

    /**
     * Construtor de objetos do tipo Decoracao
     * <p>
     * A imagem é carregada ImageIcon e o método getResource() para carregar a
     * imagem
     * Método getImage() utilizado para obter a imagem e defini-la na decoração.
     * 
     * @param localizacao
     * @param imagem
     */

    public Decoracao(Localizacao localizacao, String imagem) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource(imagem)).getImage());
    }
}
