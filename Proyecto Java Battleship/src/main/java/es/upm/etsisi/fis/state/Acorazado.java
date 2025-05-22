package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.logic.GameManager;

public class Acorazado extends Barco {

    public static final int TAMANIO_ACORAZADO = 4;
    public static final int HABILIDADES_ACORAZADO = TAMANIO_ACORAZADO;

    public Acorazado() {
        super(TAMANIO_ACORAZADO, HABILIDADES_ACORAZADO);
    }

    @Override
    public boolean usarHabilidadEspecial() {
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if (puedeUsarHabilidad) {
            habilidadAcorazado();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18751
    private void habilidadAcorazado() {
        Jugador jugador = super.getTablero().getPropietario();
        Tablero tableroEnemigo = GameManager.getInstance().getTableroRival(jugador);
        GameDisplay gameDisplay = GameManager.getInstance().getGameDisplay();
        String coordenadas = gameDisplay.getCoordenadas();
        String[] partes = coordenadas.split(",");
        int filSeleccionada = Integer.parseInt(partes[0]);
        int colSeleccionada = Integer.parseInt(partes[1]);
        tableroEnemigo.atacarCasilla(filSeleccionada,colSeleccionada,jugador);
        tableroEnemigo.atacarCasilla(filSeleccionada+1,colSeleccionada,jugador);
        tableroEnemigo.atacarCasilla(filSeleccionada-1,colSeleccionada,jugador);
        tableroEnemigo.atacarCasilla(filSeleccionada,colSeleccionada+1,jugador);
        tableroEnemigo.atacarCasilla(filSeleccionada,colSeleccionada-1,jugador);

    }

}
