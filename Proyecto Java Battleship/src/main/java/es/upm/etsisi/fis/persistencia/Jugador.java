package es.upm.etsisi.fis.persistencia;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private boolean turno;

    private Tablero tablero;
    private List<Ataque> ataquesRealizados;
    private List<Partida> partidasJugadas;

    public Jugador() {
        this.turno = false;
        this.tablero = null;
        this.ataquesRealizados = new ArrayList<>();
        this.partidasJugadas = new ArrayList<>();
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public List<Ataque> getAtaquesRealizados() {
        return ataquesRealizados;
    }

    public void setAtaquesRealizados(List<Ataque> ataquesRealizados) {
        this.ataquesRealizados = ataquesRealizados;
    }

    public List<Partida> getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(List<Partida> partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public void aniadirPartida(Partida partida) {
        partidasJugadas.add(partida);
    }

}
