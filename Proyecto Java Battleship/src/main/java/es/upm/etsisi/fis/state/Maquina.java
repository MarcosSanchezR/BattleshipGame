package es.upm.etsisi.fis.state;

import java.util.Random;

public class Maquina extends Jugador{

    private final String id;

    public Maquina() {
        this.id = "Maquina_FACIL_" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    @Override
    public int[] getCoordenadasAtaque() {
        Random rand = new Random();

        // Devuelve [1,11) == [1,10]
        int fila = rand.nextInt(1, 11);
        int columna = rand.nextInt(1, 11);
        return new int[]{fila, columna};
    }
}
