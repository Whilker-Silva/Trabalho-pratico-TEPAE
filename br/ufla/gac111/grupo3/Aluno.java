package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Representa os alunos da simulação.
 * <p>
 * Na simulacão, aluno é considerado um item que se move, dessa forma a classe
 * Aluno herda de itemDinamico, que herda de item.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author Whilker Henrique Dos Santos Silva
 */

public class Aluno extends ItemDinamico {
    
    //Atributos
    private int tempoEntrada;
    private boolean embarcou;

    /**
     * Construtor de objetos do tipo Aluno
     * <p>
     * Atribui uma localizacao para os atributos localizacaoAtual e para localizacaoDestino.
     * Além disso define o tempo de entrada do aluno.
     * @param localizacaoAtual
     * @param localizacaoDestino
     * @param tempoEntrada
     */

    public Aluno(Localizacao localizacaoAtual, Localizacao localizacaoDestino, int tempoEntrada) {
        super(localizacaoAtual, localizacaoDestino);
        setImagem(new ImageIcon(getClass().getResource("Imagens/Pessoa2.png")).getImage());
        this.tempoEntrada = tempoEntrada;
    }

    /**
     * Retorna o tempo que o aluno irá gastar para embarcar no mamute.
     * @return int - tempoChegada
     */

    public int getTempoEntrada() {
        return tempoEntrada;
    }

    /**
     * Altera o estado inicial do atributo embarcou (FALSE) para (TRUE).
     */

    public void setEmbarcou() {
        embarcou = true;
    }

    /**
     * Retorna se o aluno já embarcou no mamute.
     * @return boolean - embarcou
     */
    
    public boolean getEmbarcou() {
        return embarcou;
    }
}