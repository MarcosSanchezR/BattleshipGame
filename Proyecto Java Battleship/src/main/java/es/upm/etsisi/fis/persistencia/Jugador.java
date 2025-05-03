package es.upm.etsisi.fis.persistencia;

import java.util.List;

public class Jugador {

    private boolean turno;

    private Tablero tablero;
    private List<Ataque> ataquesRealizados;
    private Partida partida;

    public Jugador() {
        this.turno = false;
    }



}
