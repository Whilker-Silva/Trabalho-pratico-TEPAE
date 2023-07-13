package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;


/**
 * Cachorro é uma classe responsavel pela criação e movimentaçao do objeto Cachorro
 */
public class Cachorro extends ItemDinamico implements MovimentacaoPercurso {
    


    /**
     * Construtor de objetos do tipo Cachorro
     * <p>
     * Recebe por parametro duas variaveis do tipo Localizacao e as atribui as variáveis 
     * localizacaoAtual e a localizacaoDestino.
     * @param localizacao Localizacao que será atribuida a variável localizacaoAtual
     * @param localizacaoDestino Localizacao que será atribuida a variável localizacaoDestino
     */
    public Cachorro(Localizacao localizacao, Localizacao localizacaoDestino){
        super(localizacao,localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Cachorro.png")).getImage());
    }



    /**
     * Recebe por parametros duas variaveis do tipo Localizacao
     * e realiza a ação de movimentar o Objeto pelo mapa,
     * logo depois realiza a função trocarDestino que, caso o Cachorro tenha chegado em seu destino,
     * ele troca o destino do ponto1 para o ponto2
     */
    
    @Override
    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        executarAcao();
        trocaDestino(localizacaoPonto1, localizacaoPonto2);
    }

} 
