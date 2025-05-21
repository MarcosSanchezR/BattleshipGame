package es.upm.etsisi.fis.state;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    //@TODO: Implementar borrado en cascada con casillas
    public static final int DIMENSION_TABLERO = 10;

    private final Casilla[][] casillas;
    private final Jugador propietario;
    private final List<Barco> barcosPropios = new ArrayList<>();

    public Tablero(Jugador propietario) {
        this.propietario = propietario;
        casillas = inicializarCasillas();
    }

    private Casilla[][] inicializarCasillas(){
        Casilla[][] result = new Casilla[DIMENSION_TABLERO][DIMENSION_TABLERO];
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = new Casilla(i, j);
                result[i][j] = casilla;
            }
        }
        return result;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    //@TODO: Implementar RF #18769
    public Ataque atacarCasilla(int fila, int columna){
        return null;
    }
    private void mostrarTableroRival() {
        System.out.println("Tablero del Rival:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                if (casilla.isImpactada()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" 🌊 ");
                }
            }
            System.out.println();
        }
    }

private boolean casillaTieneBarco(Casilla casilla) {
    for (Barco barco : barcosPropios) {
        if (barco.getCasillasOcupadas().contains(casilla)) {
            return true;
        }
    }
    return false;
}

    private void mostrarMiTablero() {
        System.out.println("Mi Tablero:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean tieneBarco = casillaTieneBarco(casilla);

                if (tieneBarco && casilla.isImpactada()) {
                    System.out.print(" X "); // Barco impactado
                } else if (tieneBarco) {
                    System.out.print(" B "); // Barco sin impactar
                } else if (casilla.isImpactada()) {
                    System.out.print(" * "); // Agua impactada
                } else {
                    System.out.print(" \uD83C\uDF0A "); // Agua no impactada
                }
            }
            System.out.println();
        }
    }


}
