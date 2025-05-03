package es.upm.etsisi.fis.persistencia;

import java.util.Random;

public class Partida {

    private static int id_counter = 0;

    private int id;
    private int turnos;
    private double puntuacionJugador;
    private double puntuacionMaquina;

    private Jugador jugador;
    private Jugador maquina;
    private final Tablero tableroJugador;
    private final Tablero tableroMaquina;

    public Partida(Jugador jugador, Jugador maquina) {
        this.id = id_counter++;
        this.turnos = 0;
        this.puntuacionJugador = 0;
        this.puntuacionMaquina = 0;

        this.jugador = jugador;
        this.maquina = maquina;
        this.tableroJugador = new Tablero(jugador);
        this.tableroMaquina = new Tablero(maquina);

        jugador.aniadirPartida(this);
        jugador.setTablero(tableroJugador);

        maquina.aniadirPartida(this);
        maquina.setTablero(tableroMaquina);
    }

    private Jugador concederTurnoInicial(){
        maquina.setTurno(false);
        jugador.setTurno(true);
        return jugador;
    }

    private Jugador cambiarTurnos(){
        boolean turnoJugador = jugador.isTurno();
        jugador.setTurno(!turnoJugador);
        maquina.setTurno(turnoJugador);

        return (jugador.isTurno()) ? jugador : maquina;
    }
}
