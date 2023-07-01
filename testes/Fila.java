package testes;

import java.util.LinkedList;
import java.util.Queue;

public class Fila {
    private Queue<Aluno> fila;
    
    public Fila(){
        fila = new LinkedList<>();
    }

    public void adicionarAluno(Aluno aluno){
        fila.add(aluno);
    }

    public void lerFila(){
        for (Aluno a : fila) {
            System.out.println(a);
        }
    }

}
