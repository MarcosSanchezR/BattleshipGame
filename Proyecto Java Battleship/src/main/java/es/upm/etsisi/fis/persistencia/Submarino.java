package es.upm.etsisi.fis.persistencia;

import java.util.List;

public class Submarino extends Barco {

    public static final int TAMANIO_SUBMARINO = 3;
    public static final int HABILIDADES_SUBMARINO = 1;

    public Submarino(){
        super(TAMANIO_SUBMARINO, HABILIDADES_SUBMARINO);
    }

    @Override
    public boolean usarHabilidadEspecial(Tablero tableroEnemigo){
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial(tableroEnemigo);
        if(puedeUsarHabilidad){
            habilidadSubmarino();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18739
    private void habilidadSubmarino(){
        List<Casilla> lista= super.getCasillasOcupadas();
        for (Casilla casilla : lista){
            if (casilla.isImpactada()){
                casilla.setImpactada(false);
            }
        }
    }

}
