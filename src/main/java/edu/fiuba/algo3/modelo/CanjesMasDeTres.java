package edu.fiuba.algo3.modelo;

public class CanjesMasDeTres implements Canjes {
    private int fichasPorCanje;

    public CanjesMasDeTres(){
        this.fichasPorCanje = 10;
    }

    @Override
    public int realizarCanje() {
        return this.fichasPorCanje;
    }

    @Override
    public Canjes obtenerProximoCanje() {
        this.fichasPorCanje += 5;
        return this;
    }
}
