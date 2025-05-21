package es.upm.etsisi.fis.state;

public class Portaviones extends Barco{

    public static final int TAMANIO_PORTAAVIONES = 4;
    public static final int HABILIDADES_PORTAAVIONES = TAMANIO_PORTAAVIONES;

    public Portaviones() {
        super(TAMANIO_PORTAAVIONES, HABILIDADES_PORTAAVIONES);
    }

    @Override
    public boolean usarHabilidadEspecial(Tablero tableroEnemigo){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial(tableroEnemigo);
        if(puedeUsarHabilidad){
            habilidadPortaaviones(tableroEnemigo);
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18745
    private void habilidadPortaaviones(Tablero tableroEnemigo){

    }

}
