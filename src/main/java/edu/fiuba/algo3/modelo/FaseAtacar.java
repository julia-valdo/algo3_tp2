package edu.fiuba.algo3.modelo;

public class FaseAtacar implements FaseDeRonda{
    @Override
    public void accionJugador(Jugador jugador, Banco banco){
        return;
    }
    @Override
    public FaseDeRonda cambiarFase(){
        return new FaseReagrupar();
    }
}
