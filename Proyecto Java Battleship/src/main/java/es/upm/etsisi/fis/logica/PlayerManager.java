package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.Jugador;
import es.upm.etsisi.fis.persistencia.JugadorHumano;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements IPlayerManager {

    private final List<Jugador> jugadores;
    private JugadorHumano loggedUser;

    public PlayerManager() {
        this.jugadores = new ArrayList<>();
    }


    @Override
    public JugadorHumano getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(JugadorHumano loggedUser) {
        this.loggedUser = loggedUser;
    }
}
