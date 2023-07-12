package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

public class Cachorro extends ItemDinamico implements MovimentacaoPercurso {
    
    public Cachorro(Localizacao localizacao, Localizacao localizacaoDestino){
        super(localizacao,localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Cachorro.png")).getImage());
    }

    @Override
    public void realizarPercurso(Localizacao localizacaoPonto1, Localizacao localizacaoPonto2){
        executarAcao();
        trocaDestino(localizacaoPonto1, localizacaoPonto2);
    }

} 
