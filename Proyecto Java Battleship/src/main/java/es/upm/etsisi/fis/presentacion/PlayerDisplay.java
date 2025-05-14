package es.upm.etsisi.fis.presentacion;

import es.upm.etsisi.fis.logica.IPlayerManager;
import es.upm.etsisi.fis.logica.PlayerManager;

public class PlayerDisplay implements IPlayerDisplay {

    private final IPlayerManager playerManager;

    public PlayerDisplay(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public IPlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void mostrarAltaUsuario() {

    }

    @Override
    public void mostrarBajaUsuario() {

    }

    @Override
    public void mostrarIniciarSesion() {

    }

    @Override
    public void mostrarCerrarSesion() {

    }
}
