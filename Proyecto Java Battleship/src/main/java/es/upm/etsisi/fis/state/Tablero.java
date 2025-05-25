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

    public Ataque atacarCasilla(int fila, int columna, Jugador jugadorAtacante) {
        // Comprobación de coordenadas ya realizada al tomar las coordenadas; no puede comprobar el modelo
        Casilla casilla = casillas[fila][columna];

        if (casilla.isImpactada()) {
            System.out.println("La casilla ya fue atacada.");
            return null;
        } else {
            casilla.marcarComoImpactada();
            if(!casilla.isRevelada()){
                casilla.setRevelada(true);
            }
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
            return new Ataque(barcoImpactado, casilla, jugadorAtacante, this);
        }
    }

}