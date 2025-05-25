package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Acorazado extends Barco {

    private static final int TAMANIO_ACORAZADO = 4;
    private static final int HABILIDADES_ACORAZADO = TAMANIO_ACORAZADO;

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
        jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila + 1, columna, jugador));
        jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila, columna + 1, jugador));
        jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila - 1, columna, jugador));
        jugador.aniadirAtaque(tableroEnemigo.atacarCasilla(fila, columna - 1, jugador));
    }

}
