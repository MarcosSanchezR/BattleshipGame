package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.Barco;

public interface IGameDisplay {

    String getCoordenadas();

    boolean getConfirmacionHabilidad(Barco barco);

    void mostrarTablero();

    int getFila();

}
