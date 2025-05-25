package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Barco;

public interface IGameDisplay {

    String getCoordenadas();

    boolean getConfirmacionHabilidad(Barco barco);

    void mostrarMiTablero();

    void mostrarTableroEnemigo();

    String realizarAtaque();

    int pedirFila();

    int pedirColumna();


}
