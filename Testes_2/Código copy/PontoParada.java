import java.util.LinkedList;
import java.util.Queue;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PontoParada extends Item{
    private Queue<Aluno> filaAlunos;

    public PontoParada(Localizacao localizacao){
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/PontoOnibus.png")).getImage());

        filaAlunos = new LinkedList<Aluno>();
    }

    public void adicionarAluno(Aluno a){

    }

    public Aluno removeAluno(){
        return null;
        //VEr se pode quebrar o encapsulamento
    }

    private void montarFila(Aluno aluno){
        filaAlunos.add(aluno);
    }

    public boolean verificaFila(){
        return true;
    }
}

