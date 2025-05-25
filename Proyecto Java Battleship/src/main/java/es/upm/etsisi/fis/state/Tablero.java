package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logic.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Ataque atacarCasilla(int fila, int columna, Jugador jugadorAtacante) {
        // Comprobación de coordenadas ya realizada al tomar las coordenadas; no puede comprobar el modelo
        Casilla casilla = casillas[fila][columna];
        Tablero tableroAtacado = GameManager.getInstance().getTableroRival(jugadorAtacante);

        //@FIXME: Lógica mal hecha; la casilla no se puede atacar si ya está impactada (el submarino la marca como no
        // impactada de nuevo)
        if (casilla.isImpactada()) {
            System.out.println("La casilla ya fue atacada.");
            return new Ataque(Optional.empty(), casilla, jugadorAtacante, tableroAtacado);
        }
        casilla.marcarComoImpactada();
        Optional<Barco> barcoImpactado = Optional.empty();
        int i = 0;

        while (i < barcosPropios.size()) {
            Barco barco = barcosPropios.get(i);
            if (barco.getCasillasOcupadas().contains(casilla)) {
                barcoImpactado = Optional.of(barco);
                barco.actualizarEstado(); // Se actualiza el estado del barco impactado
                i = barcosPropios.size(); // Finaliza el bucle sin usar break
            } else {
                i++;
            }
        }

        return new Ataque(barcoImpactado, casilla, jugadorAtacante, tableroAtacado);
    }

    //@FIXME: El modelo no puede imprimir. Mover a GameDisplay
    private void mostrarTableroRival() {
        System.out.println("Tablero del Rival:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean ocupado = false;

                int x = 0;
                while (x < barcosPropios.size() && !ocupado) {
                    Barco barco = barcosPropios.get(x);
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;
                    }
                    x++;
                }

                if(casilla.isRevelada()){
                if(casilla.getBarco().isPresent()){
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

                int k = 0;
                while (k < barcosPropios.size() && !ocupado) {
                    Barco barco = barcosPropios.get(k);
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;
                    }
                    k++;
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