package es.upm.etsisi.fis.state;

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


    private void habilidadAcorazado() {
        Jugador jugador = super.getTablero().getPropietario();
        Ataque ataqueRealizado = GameManager.getInstance().realizarAtaqueReglamentario(jugador);
        Casilla atacada = ataqueRealizado.casillaAtacada();
        int fila = atacada.getFila();
        int columna = atacada.getColumna();
        Tablero tableroEnemigo = ataqueRealizado.tableroAtacado();
        tableroEnemigo.atacarCasilla(fila, columna, jugador);
        tableroEnemigo.atacarCasilla(fila + 1, columna, jugador);
        tableroEnemigo.atacarCasilla(fila, columna + 1, jugador);
        tableroEnemigo.atacarCasilla(fila - 1, columna, jugador);
        tableroEnemigo.atacarCasilla(fila, columna - 1, jugador);
    }

}
