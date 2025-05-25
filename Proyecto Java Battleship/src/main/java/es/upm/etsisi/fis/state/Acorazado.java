package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Acorazado extends Barco {

    private static final int TAMANIO_ACORAZADO = 3;
    private static final int HABILIDADES_ACORAZADO = 2;

    public Acorazado() {
        super(TAMANIO_ACORAZADO, HABILIDADES_ACORAZADO, "acorazado");
    }

    @Override
    public void usarHabilidadEspecial() {
        super.usarHabilidadEspecial();
        habilidadAcorazado();
    }


    private void habilidadAcorazado() {
        Jugador jugador = super.getTablero().getPropietario();
        Tablero tableroObjetivo = GameManager.getInstance().getTableroRival(jugador);
        Ataque ataqueRealizado = GameManager.getInstance().realizarAtaqueReglamentario(jugador, tableroObjetivo);
        Casilla atacada = ataqueRealizado.casillaAtacada();
        int fila = atacada.getFila();
        int columna = atacada.getColumna();
        Tablero tableroEnemigo = ataqueRealizado.tableroAtacado();
        if (fila+1 < 10) {
            jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila + 1, columna, jugador));
        }
        if (columna+1 < 10) {
            jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila, columna + 1, jugador));
        }
        if (fila-1 > -1) {
            jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila - 1, columna, jugador));
        }
        if (columna-1 > -1) {
            jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila, columna - 1, jugador));
        }
    }

}
