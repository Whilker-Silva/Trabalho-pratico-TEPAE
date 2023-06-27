public class EntradaMamuteEspecial extends EntradaMamute {
    
    @Override
    public void entrarAluno() {
        if(estaDisponivel()){
            if(verificaFila()){
            //remove fila especial
            } else{
                super.entrarAluno();
            }
        }
    }

    @Override
    public boolean verificaFila() {
        //verifica fila especial

        return true;
    }
}
