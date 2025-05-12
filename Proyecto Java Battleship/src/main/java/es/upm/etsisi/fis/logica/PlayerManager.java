package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.Jugador;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements IPlayerManager {

    private final List<Jugador> jugadores;

    public PlayerManager() {
        this.jugadores = new ArrayList<>();
    }


}
