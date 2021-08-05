package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.musica.ControladorMusica;
import edu.fiuba.algo3.vista.ventanas.VentanaComienzo;
import edu.fiuba.algo3.vista.ventanas.VentanaCantidadJugadores;
import edu.fiuba.algo3.vista.ventanas.VentanaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tablero extends Application {
    private final ControladorMusica musica = new ControladorMusica();
    @Override
    public void start (Stage stage) throws Exception{
        stage.setTitle("A.L.T.E.G.O.");

        VentanaComienzo ventanaComienzo = new VentanaComienzo();
        Scene escenaComienzo = new Scene(ventanaComienzo);

        stage.setScene(escenaComienzo);
        stage.show();
        musica.start();
    }
    public static void main (String[] args){
        launch(args);
    }
}