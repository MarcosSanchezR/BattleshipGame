package es.upm.etsisi.fis.state;

import java.util.List;

public class Submarino extends Barco {

    private static final int TAMANIO_SUBMARINO = 3;
    private static final int HABILIDADES_SUBMARINO = 1;

    public Submarino() {
        super(TAMANIO_SUBMARINO, HABILIDADES_SUBMARINO, "submarino");
    }

    @Override
    public void usarHabilidadEspecial() {
        super.usarHabilidadEspecial();
        habilidadSubmarino();
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
