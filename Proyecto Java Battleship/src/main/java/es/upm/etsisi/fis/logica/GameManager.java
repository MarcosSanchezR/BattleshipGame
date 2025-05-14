package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.*;

import java.util.List;

public class GameManager implements IGameManager {

    private List<Partida> partidas;

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
//eeeeeeeeeeee