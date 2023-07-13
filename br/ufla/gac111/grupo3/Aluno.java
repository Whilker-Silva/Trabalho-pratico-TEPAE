package br.ufla.gac111.grupo3;

import javax.swing.ImageIcon;

/**
 * Classe que representa os alunos da simulação.
 * <p>
 * Na simulacao aluno é considerado um item que se move, dessa forma a classe
 * Aluno herda de itemDinamico que herda de item.
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 * @author Whilker Henrique Dos Santos Silva
 */
public class Aluno extends ItemDinamico {
    private int tempoEntrada;
    private boolean embarcou;

    /**
     * Construtor de objetos do tipo Aluno
     * 
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
     * Método que retorna o tempo em que o aluno irá gastar para embarcar no mamute
     * 
     * @return tempoEntrada: int
     */
    public int getTempoEntrada() {
        return tempoEntrada;
    }

    /**
     * 
     * O atributo embarcou é inicializado por padrão como FALSE
     * <p>
     * Dessa forma este método é responsável por mudar o estado para TRUE
     */
    public void setEmbarcou() {
        embarcou = true;
    }

    /**
     * Retorna se o aluno já embarcou no mamute
     * 
     * @return embarcou: boolean
     */
    public boolean getEmbarcou() {
        return embarcou;
    }
}