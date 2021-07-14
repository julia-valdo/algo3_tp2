package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaisTest {
    @Test
    public void creoUnPaisYVerificoSuNombre(){
        Pais argentina = new Pais("Argentina");

        assertEquals("Argentina",argentina.getNombreDelPais());
    }

    @Test
    public void creoUnPaisYAgregoEjercitos(){
        Pais argentina = new Pais("Argentina");
        argentina.agregarEjercito(5);

        assertEquals(5,argentina.getCantidadDeEjercitos());
    }

    @Test
    public void creoDosPaisesLimitrofesYVerificoQueLoSean(){
        Pais argentina = new Pais("Argentina");
        Pais chile = new Pais("Chile");
        argentina.agregarPaisesConectados(chile);
        chile.agregarPaisesConectados(argentina);

        assertTrue(argentina.esLimitrofe(chile));
    }

    @Test
    public void creoDosPaisesQuePertenezcanAlMismoJugadorYVerificoQueLoSean(){
        Jugador jugador1 = new Jugador();
        Pais argentina = new Pais("Argentina");
        Pais chile = new Pais("Chile");
        jugador1.ocupa(argentina);
        jugador1.ocupa(chile);

        assertTrue(argentina.esDelMismoEquipo(chile));
    }
}