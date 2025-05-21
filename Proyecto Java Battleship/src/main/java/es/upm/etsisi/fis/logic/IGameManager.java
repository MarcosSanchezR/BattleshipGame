package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.Ataque;
import es.upm.etsisi.fis.state.Jugador;

public interface IGameManager {

    Ataque realizarAtaque(int fila, int columna, Jugador jugadorAtacante);

}
