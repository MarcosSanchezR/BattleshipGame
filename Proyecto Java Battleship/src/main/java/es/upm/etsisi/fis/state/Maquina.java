package es.upm.etsisi.fis.state;

import com.sun.security.auth.module.UnixSystem;

import java.util.Timer;

public class Maquina extends Jugador{

    private final String id;

    public Maquina() {
        this.id = "Maquina_FACIL_" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

}
