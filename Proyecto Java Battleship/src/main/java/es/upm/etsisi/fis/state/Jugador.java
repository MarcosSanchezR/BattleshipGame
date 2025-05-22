package es.upm.etsisi.fis.state;

import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {

    private boolean turno;

    private Tablero tablero;
    private List<Ataque> ataquesRealizados;
    private List<Partida> partidasJugadas;

    private Partida currentGame;

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

    public Partida getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Partida currentGame) {
        this.currentGame = currentGame;
    }

    public abstract String getCoordenadasAtaque();

    public void aniadirPartida(Partida partida) {
        partidasJugadas.add(partida);
    }
}
