package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

public class Patrullero extends Barco{

    public static final int TAMANIO_PATRULLERO = 2;
    public static final int HABILIDADES_PATRULLERO = 1;

    public Patrullero() {
        super(TAMANIO_PATRULLERO, HABILIDADES_PATRULLERO);
    }

    @Override
    public boolean usarHabilidadEspecial(){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if(puedeUsarHabilidad){
            habilidadPatrullero();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18752
    private void habilidadPatrullero(){
        int fila = GameManager.getInstance().pedirFila();
        Jugador jugador = super.getTablero().getPropietario();
        Tablero tableroEnemigo = GameManager.getInstance().getTableroRival(jugador);
        tableroEnemigo.getFila(fila);
    }

}
