package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

public class Cachorro extends ItemDinamico {
    
    public Cachorro(Localizacao localizacao, Localizacao localizacaoDestino){
        super(localizacao,localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/cachorro2.jpg")).getImage());
    }

    public  void andar(){
        


        if(getLocalizacaoAtual().getY() == 3){ //anda para a direita
            setLocalizacaoDestino(new Localizacao(getLocalizacaoAtual().getX() + 1, getLocalizacaoAtual().getY()));
            executarAcao();
            
        }
        else if (getLocalizacaoAtual().getY() == 15){ // anda para a esquerda
            setLocalizacaoDestino(new Localizacao(getLocalizacaoAtual().getX() - 1, getLocalizacaoAtual().getY()));
            executarAcao();
        }
    }

} 
