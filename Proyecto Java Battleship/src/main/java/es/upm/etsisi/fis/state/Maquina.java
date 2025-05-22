package es.upm.etsisi.fis.state;

import com.sun.security.auth.module.UnixSystem;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class Maquina extends Jugador{

    private final String id;

    public Maquina() {
        this.id = "Maquina_FACIL_" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    @Override
    public String getCoordenadasAtaque() {
        Random rand = new Random();

        // Devuelve [1,11) == [1,10]
        int fila = rand.nextInt(1, 11);
        int columna = rand.nextInt(1, 11);
        return fila + "," + columna;
    }
}
