package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IPlayerManager;

public interface PlayerMenu {

    void mostrarAltaUsuario();

    void mostrarIniciarSesion();

    void mostrarCerrarSesion();

    void mostrarBajaUsuario();

    IPlayerManager getPlayerManager();
}
