package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Barco;

public interface IGameDisplay {

    String realizarAtaque();

    boolean getConfirmacionHabilidad(Barco barco);

    int pedirFila();

    int pedirColumna();

    void mostrarTablero();

    void mostrarTableroEnemigo();


}
