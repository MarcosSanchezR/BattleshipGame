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
    public Ataque atacarCasilla(int fila, int columna, Jugador atacante) {
        if (fila < 0 || fila >= DIMENSION_TABLERO || columna < 0 || columna >= DIMENSION_TABLERO) {
            throw new IllegalArgumentException("Coordenadas fuera del rango del tablero.");
        }

        Casilla casilla = casillas[fila][columna];

        if (casilla.isImpactada()) {
            System.out.println("La casilla ya fue atacada.");
            return new Ataque(false, casilla, atacante);
        }

        casilla.marcarComoImpactada();

        boolean impactoABarco = false;
        for (Barco barco : barcosPropios) {
            if (barco.getCasillasOcupadas().contains(casilla)) {
                impactoABarco = true;
                barco.actualizarEstado(); // Se actualiza el estado de todos los barcos
            }
        }

        return new Ataque(impactoABarco, casilla, atacante);
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
        boolean tieneBarco = false;
        for (Barco barco : barcosPropios) {
            if (barco.getCasillasOcupadas().contains(casilla)) {
                tieneBarco = true;
            }
        }

        return tieneBarco;
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
