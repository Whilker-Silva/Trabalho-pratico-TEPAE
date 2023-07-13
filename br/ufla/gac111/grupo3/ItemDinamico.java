package br.ufla.gac111.grupo3;

/**
 * Representa todos os itens que tem movimento dentro
 * da simulação possuindo uma localizacao atual e uma localizacao de destino.
 * @author Pedro Henrique Pigozzi Gomes
 */

public abstract class ItemDinamico extends Item {
    private Localizacao localizacaoDestino;

    /**
     * Construtor de objetos do tipo ItemDinamico
     * <p>
     * Atribui uma localizacao para os atributos localizacaoAtual e para localizacaoDestino.
     * @param localizacaoAtual
     * @param localizacaoDestino
     */

    public ItemDinamico(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual);
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Retorna o atributo localizacaoDestino do objeto.
     * @return Localizacao - localizacaoDestino
     */

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Atribui uma nova Localizacao ao atributo localizacaoDestino.
     * @param localizacaoDestino Localizacao que vai ser atribuída a localizacaoDestino
     */

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Primeiramente, verifica se a a Localizacão de destino existe, caso a resposta seja true,
     * atualiza a localizacaoAtual passando como parâmetro proximaLocalizacao.
     */

    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

    /**
     * Compara localizacaoAtual e localizacaoDestino para verificar se o ItemDinamico já chegou em seu destino.
     * @return boolean - true se localizacaoAtual for igual a localizacaoDestino,
     *         caso contrario retorna false
     */

    public boolean chegouDestino() {
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }

    /**
     * Observa se a localizacaoAtual é igual ao pontoParada informado por parâmetro
     * @param pontoParada Localizacao que vai ser comparada com a localizacaoAtual
     * @return boolean - true caso a localizacaoAtual seja igual ao do pontoParada,
     *         false caso contrario
     */

    public boolean estaPosicionado(Localizacao pontoParada) {
        return getLocalizacaoAtual().equals(pontoParada);
    }

    /**
     * Troca a localizacaoDestino, que varia entre a Localizacão do ponto1 e do ponto2
     * <p>
     * Caso a localizacaoDestino seja igual a alguma das localizações passadas por parâmetro,
     * ele atribui o destino ao outro ponto parametrizado.
     * @param localizacaoPonto1 Localizacao do primeiro ponto
     * @param localizacaoPonto2 Localizacao do segundo ponto
     */

    public void trocaDestino(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2) {
        if (estaPosicionado(localizacaoPonto1))
            setLocalizacaoDestino(localizacaoPonto2);
        else if (estaPosicionado(localizacaoPonto2))
            setLocalizacaoDestino(localizacaoPonto1);
    }
}
