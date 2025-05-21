package es.upm.etsisi.fis.persistencia;

import es.upm.etsisi.fis.logica.GameManager;

public class Portaviones extends Barco{

    public static final int TAMANIO_PORTAAVIONES = 4;
    public static final int HABILIDADES_PORTAAVIONES = TAMANIO_PORTAAVIONES;

    public Portaviones() {
        super(TAMANIO_PORTAAVIONES, HABILIDADES_PORTAAVIONES);
    }

    @Override
    public boolean usarHabilidadEspecial(){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if(puedeUsarHabilidad){
            habilidadPortaaviones();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18745
    private void habilidadPortaaviones(){
        GameManager.getInstance().getGameDisplay().realizarAtaque();
    }

}
