package br.ufla.gac111.grupo3;


/**
 * ItemDinamico é classe que representa todos os itens que tem movimento dentro da simulação
 * possuindo uma localizacao atual e uma localizacao de destino 
 */
public abstract class ItemDinamico extends Item {
    private Localizacao localizacaoDestino;


    //Atribui uma localizacao para os atributos localizacaoAtual e para localizacaoDestino
    public ItemDinamico(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual);
        this.localizacaoDestino = localizacaoDestino;
    }



    /**
     * Metodo que retorna o atributo localizacaoDestino do objeto
     * @return Localizacao - localizacaoDestino 
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }



    /**
     * Metodo que atribui uma nova Localizacao ao atributo localizacaoDestino
     * @param localizacaoDestino Localizacao que vai ser atribuida a localizacaoDestino
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }


    /**
     * Metodo que primeiramente checa se a a Localizacao destino existe, caso a resposta seja true,
     * atualiza a localizacaoAtual passando como parametro proximaLocalizacao 
     */
    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }



    /**
     * Metodo que compara localizacaoAtual e localizacaoDestino
     * com intuito de ver se o ItemDinamico já chegou em seu destino
     * @return boolean - true se localizacaoAtual for igual a localizacaoDestino,
     * caso contrario retorna false
     */
    public boolean chegouDestino(){
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }
    


    /**
     * Metodo que observa se a localizacaoAtual é igual ao pontoParada
     * @param pontoParada Localizacao que vai ser comparada com a localizacaoAtual para checar se o ItemDinamico chegou ao seu destino
     * @return boolean - true caso a localizacaoAtual seja igual ao do pontoParada, false caso contrario
     */
    public boolean estaPosicionado(Localizacao pontoParada){
        return getLocalizacaoAtual().equals(pontoParada);
    }



    /**
     * Metodo que troca a Localizacao destino que varia entre a Localizacao do ponto1 e do ponto2, vendo antes se o
     * ItemDinamico está em alguma dessas lcalizações, caso esteja, ele atribui o destino ao outro ponto, caso contrario,
     * se mantem a mesma localizacaoDestino 
     * @param localizacaoPonto1 Localizacao que vai ser comparada ou atribuida a localizacaoDestino dependendo do caso
     * @param localizacaoPonto2 Localizacao que vai ser comparada ou atribuida a localizacaoDestino dependendo do caso
     */
    public void trocaDestino(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        if(estaPosicionado(localizacaoPonto1))
            setLocalizacaoDestino(localizacaoPonto2);
        else if(estaPosicionado(localizacaoPonto2))
            setLocalizacaoDestino(localizacaoPonto1);
    }


}
