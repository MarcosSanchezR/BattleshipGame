package es.upm.etsisi.fis.state;

import java.util.List;

public class Submarino extends Barco {

    public static final int TAMANIO_SUBMARINO = 3;
    public static final int HABILIDADES_SUBMARINO = 1;

    public Submarino() {
        super(TAMANIO_SUBMARINO, HABILIDADES_SUBMARINO);
    }

    @Override
    public boolean usarHabilidadEspecial() {
        boolean puedeUsarHabilidad = super.usarHabilidadEspecial();
        if (puedeUsarHabilidad) {
            habilidadSubmarino();
        }
        return puedeUsarHabilidad;
    }

    //@TODO: Implementar RF #18739
    private void habilidadSubmarino() {
        List<Casilla> lista = super.getCasillasOcupadas();
        for (Casilla casilla : lista) {
            if (casilla.isImpactada()) {
                casilla.setImpactada(false);
                casilla.setRevelada(true);  // se quita el impacto pero ya se queda revelada y sabes donde esta
            }
        }
    }

}
