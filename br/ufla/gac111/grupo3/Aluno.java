package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Aluno extends ItemDinamico {
    private int tempoEntrada;
    private boolean embarcou;

    public Aluno(Localizacao localizacaoAtual, Localizacao localizacaoDestino, int tempoEntrada) {
        super(localizacaoAtual, localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Pessoa.png")).getImage());
        this.tempoEntrada = tempoEntrada;
    }

    public Localizacao getProximalocalizao() {
        Localizacao destino = getLocalizacaoDestino();
        return (destino != null) ? getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino()) : null;
    }

    public int getTempoEntrada() {
        return tempoEntrada;
    }

    public boolean chegouDestino(){
        return getLocalizacaoAtual().equals(getLocalizacaoDestino());
    }

    public void setEmbarcou(){
        embarcou = true;
    }

    public boolean getEmbarcou(){
        return embarcou;
    }

}