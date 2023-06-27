import java.util.Deque;
import java.util.LinkedList;

public class EntradaMamute {
    private Deque<Aluno> pilhaAluno;
    private int tempoProximaEntrada;
    private static final int CAPACIDADE = 70;

    public EntradaMamute(){
        pilhaAluno = new LinkedList<Aluno>();
    }

    public boolean estaDisponivel(){
        return false;
    } 

    public boolean verificaFila(){
        //verifica fila normal
        //se != null

        return true;
    }

    public void entrarAluno(Aluno a){
        if(estaDisponivel() && verificaFila()){
            pilhaAluno.add(a);
            //executarAcao();
        }
    }

    //public void executarAcao(){
    //    pilhaAluno.add(a);
    //}
}