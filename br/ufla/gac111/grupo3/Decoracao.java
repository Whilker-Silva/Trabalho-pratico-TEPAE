package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

public class Decoracao extends Item {
    public Decoracao(Localizacao localizacao, String imagem){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource(imagem)).getImage());
    }
}
