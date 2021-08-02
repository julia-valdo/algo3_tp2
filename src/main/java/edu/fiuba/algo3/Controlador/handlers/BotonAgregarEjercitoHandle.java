package edu.fiuba.algo3.Controlador.handlers;

import edu.fiuba.algo3.modelo.Batalla.Pais;
import edu.fiuba.algo3.modelo.JuegoYJugador.Jugador;
import edu.fiuba.algo3.vista.Elementos.TextoNotificable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class BotonAgregarEjercitoHandle implements HandlerDePais {

    private Jugador jugador;
    private Pais pais;
    private TextoNotificable textoDeError;

    public BotonAgregarEjercitoHandle(Jugador jugador, TextoNotificable errorAReportar){
        this.textoDeError = errorAReportar;
        this.jugador = jugador;
    }

    @Override
    public void asociarPais(Pais unPais) {
        this.pais = unPais;
    }

    @Override
    public HandlerDePais getCopy() {
        return new BotonAgregarEjercitoHandle(this.jugador, this.textoDeError);
    }

    @Override
    public void desarmarHandler() {
        this.jugador = null;
    }

    @Override
    public void setJugadorEnTurno(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.prepararGrupoDeError(mouseEvent);
        try {
            this.jugador.agregarFichasA(1, this.pais);
            this.desarmarTextoDeError();
        }catch (RuntimeException exception){
            this.generarTextoDeError(exception);
        }
    }

    private void desarmarTextoDeError() {
        this.textoDeError.setText("");
    }

    private void generarTextoDeError(RuntimeException exception){

        if(NullPointerException.class == exception.getClass()){
            this.textoDeError.setText("Ese pais no es tuyo: " + this.pais.getNombreDelPais());
            System.out.println("Ese pais no es tuyo: " + this.pais.getNombreDelPais());
        }
        else{
            this.textoDeError.setText("No tenes mas fichas!");
            System.out.println("No tenes mas fichas!");
        }
    }

    private void prepararGrupoDeError(MouseEvent evento){
        Group grupoDeEscena  = (Group) ((Node) evento.getSource()).getScene().getRoot();
        if(this.textoDeError.noEstaAgregadoA(grupoDeEscena)){
            this.textoDeError.agregarAGrupo(grupoDeEscena);
        }
    }

}
