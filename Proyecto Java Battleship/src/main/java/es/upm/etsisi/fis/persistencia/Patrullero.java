package es.upm.etsisi.fis.persistencia;

public class Patrullero extends Barco{

    public static final int TAMANIO_PATRULLERO = 2;
    public static final int HABILIDADES_PATRULLERO = 1;

    public Patrullero() {
        super(TAMANIO_PATRULLERO, HABILIDADES_PATRULLERO);
    }

    @Override
    public boolean usarHabilidadEspecial(Tablero tableroEnemigo){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial(tableroEnemigo);
        if(puedeUsarHabilidad){
            habilidadPatrullero(tableroEnemigo);
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18752
    private void habilidadPatrullero(Tablero tableroEnemigo){

    }

}
