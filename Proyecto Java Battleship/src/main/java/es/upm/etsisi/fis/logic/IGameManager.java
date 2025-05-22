package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.Ataque;
import es.upm.etsisi.fis.state.Jugador;
import es.upm.etsisi.fis.state.JugadorHumano;

public interface IGameManager {

    void jugar(JugadorHumano jugadorHumano);

    Ataque realizarAtaqueReglamentario(Jugador jugadorAtacante);

    int pedirFila();

}
