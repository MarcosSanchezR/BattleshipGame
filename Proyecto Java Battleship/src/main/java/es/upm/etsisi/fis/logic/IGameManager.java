package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.*;

public interface IGameManager {

    Partida crearPartida(JugadorHumano jugadorHumano);

    void jugarPartida(Partida partida);

    Ataque realizarAtaqueReglamentario(Jugador jugadorAtacante, Tablero tableroObjetivo);

    int pedirFila();

}
