package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.logic.GameManager;

public class Portaviones extends Barco {

    public static final int TAMANIO_PORTAAVIONES = 4;
    public static final int HABILIDADES_PORTAAVIONES = TAMANIO_PORTAAVIONES;

    public Portaviones() {
        super(TAMANIO_PORTAAVIONES, HABILIDADES_PORTAAVIONES);
    }

    @Override
    public boolean usarHabilidadEspecial() {
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if (puedeUsarHabilidad) {
            habilidadPortaaviones();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18745
    private void habilidadPortaaviones() {
        Jugador jugador = super.getTablero().getPropietario();
        GameDisplay display = GameManager.getInstance().getGameDisplay();
        Tablero tableroEnemigo = GameManager.getInstance().getTableroRival(jugador);
        String coordenadas = display.getCoordenadas();
        String[] partes = coordenadas.split(",");
        int fila = Integer.parseInt(partes[0].trim());
        int columna = Integer.parseInt(partes[1].trim());
        tableroEnemigo.atacarCasilla(fila, columna, jugador);
    }

}
