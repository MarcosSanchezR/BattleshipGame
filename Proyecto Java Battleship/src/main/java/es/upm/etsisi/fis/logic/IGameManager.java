package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.Ataque;
import es.upm.etsisi.fis.state.Jugador;
import es.upm.etsisi.fis.state.JugadorHumano;
import es.upm.etsisi.fis.state.Tablero;

public interface IGameManager {

    void crearPartida(JugadorHumano jugadorHumano);

    Ataque realizarAtaqueReglamentario(Jugador jugadorAtacante, Tablero tableroObjetivo);

    int pedirFila();

}
