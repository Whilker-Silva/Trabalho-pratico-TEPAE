package testes_movimentacao_fila;

public class Simulacao {
    
    public Simulacao(){
        
    }

    public void iniciarSimulacao(){
        Fila fila = new Fila();
        Aluno a1 = new Aluno("Pedro");
        Aluno a2 = new Aluno("Victor");
        Aluno a3 = new Aluno("Matheus");
        fila.adicionarAluno(a1);
        fila.adicionarAluno(a2);
        fila.adicionarAluno(a3);
    }
}
