package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Portaviones extends Barco {

    public static final int TAMANIO_PORTAAVIONES = 4;
    public static final int HABILIDADES_PORTAAVIONES = TAMANIO_PORTAAVIONES;

    public Portaviones() {
        super(TAMANIO_PORTAAVIONES, HABILIDADES_PORTAAVIONES, "portaaviones");
    }

    @Override
    public void usarHabilidadEspecial() {
        super.usarHabilidadEspecial();
        habilidadPortaaviones();
    }

    //@TODO: Implementar RF #18745
    private Ataque habilidadPortaaviones() {
        Jugador jugador = super.getTablero().getPropietario();
        Ataque ataqueRealizado = GameManager.getInstance().realizarAtaqueReglamentario(jugador);
        jugador.aniadirAtaque(ataqueRealizado);
        return ataqueRealizado;
    }

}
