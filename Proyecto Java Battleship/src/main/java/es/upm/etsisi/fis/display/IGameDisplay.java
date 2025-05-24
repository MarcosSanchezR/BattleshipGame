package es.upm.etsisi.fis.display;

public interface IGameDisplay {

    String realizarAtaque();

    boolean getConfirmacionHabilidad();

    int pedirFila();

    int pedirColumna();

    void mostrarTablero();

    void mostrarTableroEnemigo();


}
