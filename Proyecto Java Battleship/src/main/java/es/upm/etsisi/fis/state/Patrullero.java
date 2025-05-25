package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Patrullero extends Barco {

    private static final int TAMANIO_PATRULLERO = 2;
    private static final int HABILIDADES_PATRULLERO = 1;

    public Patrullero() {
        super(TAMANIO_PATRULLERO, HABILIDADES_PATRULLERO, "patrullero");
    }

    @Override
    public void usarHabilidadEspecial() {
        super.usarHabilidadEspecial();
        habilidadPatrullero();
    }

    private void habilidadPatrullero() {
        // Recibe [1,10] -> declara [0,9], dentro del rango.
        int fila = GameManager.getInstance().pedirFila() - 1;
        Jugador jugador = super.getTablero().getPropietario();
        Tablero tableroEnemigo = GameManager.getInstance().getTableroRival(jugador);

        Casilla[] casillasFila = tableroEnemigo.getFila(fila);

        for (Casilla casilla : casillasFila) {
            casilla.marcarRevelada();
        }
    }
}
