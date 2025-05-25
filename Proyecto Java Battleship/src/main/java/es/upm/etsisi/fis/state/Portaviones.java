package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Portaviones extends Barco {

    private static final int TAMANIO_PORTAAVIONES = 4;
    private static final int HABILIDADES_PORTAAVIONES = TAMANIO_PORTAAVIONES;

    public Portaviones() {
        super(TAMANIO_PORTAAVIONES, HABILIDADES_PORTAAVIONES, "portaaviones");
    }

    @Override
    public void usarHabilidadEspecial() {
        super.usarHabilidadEspecial();
        habilidadPortaaviones();
    }

    private void habilidadPortaaviones() {
        Jugador jugador = super.getTablero().getPropietario();
        Tablero tableroObjetivo = GameManager.getInstance().getTableroRival(jugador);
        Ataque ataqueRealizado = GameManager.getInstance().realizarAtaqueReglamentario(jugador, tableroObjetivo);
        jugador.aniadirAtaque(ataqueRealizado);
    }

}
