package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.*;
import es.upm.etsisi.fis.presentacion.GameDisplay;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements IGameManager {

    private final List<Partida> partidas;
    private GameDisplay gameDisplay;

    public GameManager() {
        this.partidas = new ArrayList<>();
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    public void setGameDisplay(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    public Ataque realizarAtaque(int fila, int columna, Jugador jugadorAtacante) {
         Tablero tableroObjetivo= getTableroRival(jugadorAtacante);
        return tableroObjetivo.atacarCasilla(fila, columna);
    }

    public Tablero getTableroRival(Jugador jugadorAtacante){
        Partida partida = jugadorAtacante.getCurrentGame();
        Jugador unJugador = partida.getJugador();
        Jugador otroJugador = partida.getMaquina();

        return (jugadorAtacante.equals(unJugador)) ? otroJugador.getTablero() : unJugador.getTablero();
    }

    @Override
    public int pedirFila() {
        int fila = gameDisplay.pedirFila()-1;
        if (fila > 9 || fila <0){
            throw new IllegalArgumentException("La fila esta fuera de los limites del tablero: 10 >= "+fila+" >= 1");
        }
        return fila;
    }
}
