package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Jugador;

import es.upm.etsisi.fis.state.Barco;
import es.upm.etsisi.fis.state.Partida;

public interface IGameDisplay {

    String getCoordenadas();

    boolean getConfirmacionHabilidad(Barco barco);

    void mostrarMiTablero(Partida partida);

    void mostrarTableroEnemigo(Partida partida);

    void mostrarPuntuacion(Jugador jugador);

    int getFila();
}
