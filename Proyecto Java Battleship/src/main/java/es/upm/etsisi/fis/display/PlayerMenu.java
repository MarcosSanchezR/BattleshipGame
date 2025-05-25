package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IPlayerManager;

public interface PlayerMenu {

    IPlayerManager getPlayerManager();

    void mostrarBajaUsuario();

    void mostrarCerrarSesion();

    void mostrarAltaUsuario();

    void mostrarIniciarSesion();

}
