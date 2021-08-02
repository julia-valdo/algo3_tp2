package edu.fiuba.algo3.modelo.FlujoDeJuego;

import edu.fiuba.algo3.modelo.JuegoYJugador.InventarioDeJuego;
import edu.fiuba.algo3.modelo.JuegoYJugador.Jugador;
import edu.fiuba.algo3.Controlador.SeleccionJugador;
import edu.fiuba.algo3.vista.ventanas.VentanaMenu;

public class FaseReagrupar implements FaseDeRonda {
    @Override
    public void aplicarAccionesDeFase(Jugador jugador, InventarioDeJuego inventario) {

    }

    @Override
    public boolean accionJugador(Jugador jugador, InventarioDeJuego inventarioDeJuego, SeleccionJugador seleccion){
        try{
            jugador.moverFichasDeACon(seleccion.getPaisUno(), seleccion.getPaisDos(), seleccion.getCantidad());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public FaseDeRonda cambiarFase(){
        return new FaseColocarEjercitos();
    }

    @Override
    public VentanaMenu prepararMenu() {
        return null;
    }
}