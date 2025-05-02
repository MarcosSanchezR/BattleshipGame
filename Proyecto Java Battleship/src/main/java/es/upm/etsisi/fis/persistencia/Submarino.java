package es.upm.etsisi.fis.logica;

public class Submarino extends Barco {

    public static final int TAMANIO_SUBMARINO = 3;
    public static final int HABILIDADES_SUBMARINO = 1;

    public Submarino(){
        super(TAMANIO_SUBMARINO, HABILIDADES_SUBMARINO);
    }

    @Override
    public boolean usarHabilidadEspecial(){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if(puedeUsarHabilidad){
            habilidadSubmarino();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18739
    private void habilidadSubmarino(){

    }

}
