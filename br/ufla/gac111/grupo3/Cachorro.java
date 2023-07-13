package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Representa o cachorro da simulação.
 * <p>
 * Na simulacão, cachorro é considerado um item que se move, dessa forma a classe
 * cachorro herda de itemDinamico, que herda de item.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author Mateus Henrique Teixeira
 */

public class Cachorro extends ItemDinamico implements MovimentacaoPercurso {
    
    /**
     * Construtor de objetos do tipo Cachorro
     * <p>
     * Recebe por parametro duas variaveis do tipo Localizacao e as atribui as variáveis 
     * localizacaoAtual e a localizacaoDestino.
     * @param localizacao - Localizacao que será atribuida a variável localizacaoAtual
     * @param localizacaoDestino - Localizacao que será atribuida a variável localizacaoDestino
     */

    public Cachorro(Localizacao localizacao, Localizacao localizacaoDestino){
        super(localizacao,localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Cachorro.png")).getImage());
    }

    /**
     * Realiza a ação de movimentar o cachorro pelo mapa, do ponto1 ao ponto2, intermitentemente.
     * @param localizacaoPonto1 - Localizacao do primeiro ponto
     * @param localizacaoPonto2 - Localizacao do segundo ponto
     */

    @Override
    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        executarAcao();
        trocaDestino(localizacaoPonto1, localizacaoPonto2);
    }

} 
