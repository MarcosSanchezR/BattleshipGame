package es.upm.etsisi.fis.state;

public class Tablero {

    //@TODO: Implementar borrado en cascada con casillas
    public static final int DIMENSION_TABLERO = 10;

    private final Casilla[][] casillas;
    private final Jugador propietario;

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
                System.out.print(casilla.isImpactada() ? " X " : "\uD83C\uDF0A");
            }
            System.out.println();
        }
    }
//si la casilla no ha sido impactada 🌊 si ha sido impactada X , en mi tablero no he puesto como ver los barcos

    private void mostrarMiTablero() {
        System.out.println("Mi Tablero:");
        for (int i = 0; i < DIMENSION_TABLERO; i++) {
            for (int j = 0; j < DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                System.out.print(casilla.isImpactada() ? " X " : "\uD83C\uDF0A");
            }
            System.out.println();
        }
    }
}
