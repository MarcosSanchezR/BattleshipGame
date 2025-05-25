package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Jugador;

public interface IGameDisplay {

    String realizarAtaque();

    boolean getConfirmacionHabilidad();

    int pedirFila();

    int pedirColumna();

    void mostrarTablero();

    void mostrarTableroEnemigo();

    void mostrarPuntuacion(Jugador jugador);

}
