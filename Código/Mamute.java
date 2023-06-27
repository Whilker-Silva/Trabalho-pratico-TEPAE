import java.util.ArrayList;

public class Mamute {
    private ArrayList<EntradaMamute> listaPortas;
    private int tempoProximaParada;

    public Mamute(){
        listaPortas = new ArrayList<>();
        criarEntradas();
    }

    private void criarEntradas(){
        listaPortas.add(new EntradaMamute());
        listaPortas.add(new EntradaMamute());
        listaPortas.add(new EntradaMamuteEspecial());
    }

    public boolean estaDisponivel(){
        return false;
    }

    public void realizarPercurso(){

    }
}
