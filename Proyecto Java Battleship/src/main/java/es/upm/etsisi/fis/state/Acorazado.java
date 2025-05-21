package es.upm.etsisi.fis.state;

public class Acorazado extends Barco{

    public static final int TAMANIO_ACORAZADO = 4;
    public static final int HABILIDADES_ACORAZADO = TAMANIO_ACORAZADO;

    public Acorazado() {
        super(TAMANIO_ACORAZADO, HABILIDADES_ACORAZADO);
    }

    @Override
    public boolean usarHabilidadEspecial(Tablero tableroEnemigo){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial(tableroEnemigo);
        if(puedeUsarHabilidad){
            habilidadAcorazado(tableroEnemigo);
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18751
    private void habilidadAcorazado(Tablero tableroEnemigo){

    }

}
