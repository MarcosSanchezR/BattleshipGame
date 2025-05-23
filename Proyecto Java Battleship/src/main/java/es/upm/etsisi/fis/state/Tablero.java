package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    //@TODO: Implementar borrado en cascada con casillas
    public static final int DIMENSION_TABLERO = 10;

    private final Jugador propietario;

    private final Casilla[][] casillas;
    private final List<Barco> barcosPropios = new ArrayList<>();

    public Tablero(Jugador propietario) {
        this.propietario = propietario;
        casillas = inicializarCasillas();
    }

    private Casilla[][] inicializarCasillas() {
        Casilla[][] result = new Casilla[DIMENSION_TABLERO][DIMENSION_TABLERO];
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = new Casilla(i, j);
                result[i][j] = casilla;
            }
        }
        return result;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public List<Barco> getBarcosPropios() {
        return barcosPropios;
    }

    public Casilla[] getFila(int i) {
        return casillas[i];
    }

    //@TODO: Implementar RF #18769
    public Ataque atacarCasilla(int fila, int columna, Jugador jugadorAtacante) {
        // Comprobación de coordenadas ya realizada al tomar las coordenadas; no puede comprobar el modelo
        Casilla casilla = casillas[fila][columna];
        Tablero tableroAtacado = GameManager.getInstance().getTableroRival(jugadorAtacante);

        //@TODO: Lógica mal hecha; la casilla no se puede atacar si ya está impactada (el submarino la marca como no
        // impactada de nuevo)
        if (casilla.isImpactada()) {
            System.out.println("La casilla ya fue atacada.");
            return new Ataque(false, casilla, jugadorAtacante, tableroAtacado);
        }

        casilla.marcarComoImpactada();

        boolean impactoABarco = false;
        for (Barco barco : barcosPropios) {
            if (barco.getCasillasOcupadas().contains(casilla)) {
                impactoABarco = true;
                barco.actualizarEstado(); // Se actualiza el estado de todos los barcos
            }
        }

        return new Ataque(impactoABarco, casilla, jugadorAtacante, tableroAtacado);
    }

    private void mostrarTableroRival() {
        System.out.println("Tablero del Rival:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean ocupado = false;

                // Comprobar si la casilla está ocupada por algún barco enemigo
                for (Barco barco : barcosPropios) { // Aquí barcosPropios serían los barcos del rival en este tablero
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;

                    }
                }
            if(casilla.isRevelada()){
                if(casilla.tieneBarco()){
                System.out.print(" 🚢 "); // Barco revelado
            }else {
                    System.out.print(" 🌊 "); //Agua revelada
                }
            }
                if (casilla.isImpactada()) {
                    if (ocupado) {
                        System.out.print(" 💥 ");  // Barco impactado
                    } else {
                        System.out.print(" X ");    // Agua impactada (fuego fallido)
                    }
                } else {
                    System.out.print(" ☁️ ");       // Agua sin impacto (niebla de guerra)
                }
            }
            System.out.println();
        }
    }

    private void mostrarMiTablero() {
        System.out.println("Mi Tablero:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean ocupado = false;

                // Recorremos los barcos para ver si la casilla está ocupada
                for (Barco barco : barcosPropios) {
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;

                    }
                }

                if (ocupado) {
                    if (casilla.isImpactada()) {
                        System.out.print(" 💥 ");  // Barco impactado
                    } else {
                        System.out.print(" 🚢 ");  // Barco intacto
                    }
                } else {
                    if (casilla.isImpactada()) {
                        System.out.print(" X ");    // Agua impactada (fuego fallido)
                    } else {
                        System.out.print(" 🌊 ");   // Agua sin impacto
                    }
                }
            }
            System.out.println();
        }
    }
}