package testes_movimentacao_fila;
import java.util.ArrayList;
public class Fila {
    private ArrayList<Aluno> fila;



    public Fila(){
        fila = new ArrayList<>();
    }


    public void adicionarAluno(Aluno a){
        fila.add(a);
    }

}
