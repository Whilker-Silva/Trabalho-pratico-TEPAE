package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Responsável por aparatar o mapa de simulação
 * adicionando imagens no mapa de simulação.
 * @author Victor Hugo Daia Lorenzato
 */
public class Decoracao extends Item {

     /**
     * Construtor de objetos do tipo Decoracao
     * <p>
     * Atribui uma localização para o atributo localizacaoAtual.
     * Além disso, inicializa a fila de alunos e define a imagem do ponto de parada através do método setImagem.
     * @param localizacao - Localizacao que será atribuída à localizacaoAtual
     * @param imagem - Imagem que será atribuída a imagem do objeto
     */

    public Decoracao(Localizacao localizacao, String imagem) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource(imagem)).getImage());
    }
}
