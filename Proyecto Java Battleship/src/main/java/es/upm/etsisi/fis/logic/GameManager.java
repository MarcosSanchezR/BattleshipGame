package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.*;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements IGameManager {

    private final List<Partida> partidas;

    public GameManager() {
        this.partidas = new ArrayList<>();
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    @Override
    public Ataque realizarAtaque(int fila, int columna, Jugador jugadorAtacante) {
        Partida partida = jugadorAtacante.getCurrentGame();
        Jugador unJugador = partida.getJugador();
        Jugador otroJugador = partida.getMaquina();

        Tablero tableroObjetivo = (jugadorAtacante.equals(unJugador)) ? otroJugador.getTablero() :
                unJugador.getTablero();
        return tableroObjetivo.atacarCasilla(fila, columna);
    }
}
