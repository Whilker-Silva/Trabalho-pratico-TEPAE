package br.ufla.gac111.grupo3;

public class ItemDinamico extends Item {

    private Localizacao localizacaoDestino;

    public ItemDinamico(Localizacao localizacaoAtual, Localizacao localizacaoDestino) {
        super(localizacaoAtual);
        this.localizacaoDestino = localizacaoDestino;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

}