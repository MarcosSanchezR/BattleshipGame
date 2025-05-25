package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Jugador;

import es.upm.etsisi.fis.state.Barco;

public interface IGameDisplay {

    String getCoordenadas();

    boolean getConfirmacionHabilidad(Barco barco);

    void mostrarMiTablero();

    void mostrarTableroEnemigo();

    void mostrarPuntuacion(Jugador jugador);

    int getFila();
}
