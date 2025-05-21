package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.Ataque;
import es.upm.etsisi.fis.persistencia.Jugador;

public interface IGameManager {

    Ataque realizarAtaque(Jugador jugadorAtacante);

    int pedirFila();

}
