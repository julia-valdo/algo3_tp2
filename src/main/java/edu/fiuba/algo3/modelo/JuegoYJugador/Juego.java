package edu.fiuba.algo3.modelo.JuegoYJugador;

import edu.fiuba.algo3.Controlador.SeleccionJugador;
import edu.fiuba.algo3.modelo.Batalla.Pais;
import edu.fiuba.algo3.modelo.Cartas.Carta;
import edu.fiuba.algo3.modelo.FlujoDeJuego.FaseDeRonda;
import edu.fiuba.algo3.modelo.FlujoDeJuego.FasePrimeraColocacion;
import edu.fiuba.algo3.modelo.Objetivos.Continente;
import edu.fiuba.algo3.modelo.Parser.Parser;
import edu.fiuba.algo3.vista.Elementos.Ficha;

import java.util.*;

public class Juego {
    private Integer turnoActual;
    private HashMap<Integer, Jugador> turnoJugadores;
    private FaseDeRonda fase;
    private Parser parser;
    private InventarioDeJuego inventario;

    public Juego(int cantidadDeJugadores){
        parser = new Parser();
        this.turnoActual = 1;
        this.turnoJugadores = new HashMap<>();
        this.fase = new FasePrimeraColocacion();
        this.crearJugadores(cantidadDeJugadores);
    }

    public void iniciarJuego(){
        parser.parsearArchivo("Teg - Cartas.json");
        parser.parsearArchivo("Teg - Fronteras.json");
        parser.parsearArchivo("Teg - Objetivos.json");
        parser.construirObjetos();
        this.repartirPaises();
        this.generarInventario();
    }


    private void crearJugadores(int cantidadDeJugadores){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        for(int i = 0; i < cantidadDeJugadores; i++) jugadores.add(new Jugador(i));
        this.establecerTurnos(jugadores);
    }

    private void establecerTurnos(ArrayList<Jugador> jugadores) {
        int counter = 1;
        Collections.shuffle(jugadores);
        for (Jugador jugador : jugadores) {
            this.turnoJugadores.put(counter, jugador);
            counter += 1;
        }
    }

    public ArrayList<Integer> obtenerOrder() {
        ArrayList<Integer> numerosDeJugadores = new ArrayList<>();
        Collection<Jugador> jugadores = this.turnoJugadores.values();
        for(Jugador jugador : jugadores){
            numerosDeJugadores.add(jugador.getNumeroJugador());
        }

        return numerosDeJugadores;

    }

    public Jugador obtenerSiguiente() {
        Jugador siguiente =  this.turnoJugadores.get(this.turnoActual);
        this.fase.aplicarAccionesDeFase(siguiente, this.inventario);
        this.avanzarTurno();
        return siguiente;
    }

    public void seleccionDeJugador(Jugador jugador, SeleccionJugador seleccion){
        this.fase.accionJugador(jugador, new InventarioDeJuego(new ArrayList<>(), new ArrayList<>()), seleccion);
        if(this.esElUltimoJugador(jugador)){
            this.fase = this.fase.cambiarFase();
        }
    }

    private void avanzarTurno() {
        if(this.esElUltimoJugador(this.turnoJugadores.get(this.turnoActual))){
            this.turnoActual = 1;
        }
        else {
            this.turnoActual++;
        }
    }


    private void repartirPaises(){
        List<Pais> paises = new ArrayList<>(this.parser.getPaises().values());
        Collections.shuffle(paises);
        for(Pais pais: paises){
         Jugador actual = this.obtenerSiguiente();
         actual.agregarFichas(1);
         actual.ocupa(pais);
        }
    }

    private boolean esElUltimoJugador(Jugador jugador){
        return jugador == this.turnoJugadores.get(this.turnoJugadores.size());
    }

    private void generarInventario() {
        ArrayList<Continente> continentes = new ArrayList<>(this.parser.getContinentes().values());
        ArrayList<Carta> cartas  = this.parser.getCartas();
        Collections.shuffle(cartas);
        this.inventario = new InventarioDeJuego(cartas, continentes);
    }

    public void setNombreJugadorNumero(int numero, String nombre) {
        this.turnoJugadores.get(numero).setNombre(nombre);
    }

    public void setearFichas(ArrayList<Ficha> fichas) {
        List<Pais> paises = new ArrayList<>(this.parser.getPaises().values());
        for(int i = 0; i < paises.size(); i++){
            paises.get(i).setFicha(fichas.get(i));
        }
    }
}
