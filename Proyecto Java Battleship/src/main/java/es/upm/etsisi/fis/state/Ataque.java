package es.upm.etsisi.fis.state;

public record Ataque(
        int id,
        boolean impactoABarco,

        Casilla casillaAtacada,
        Jugador atacante,
        Tablero tableroAtacado
) {

    private static int id_counter = 0;

    public Ataque(boolean impactoABarco, Casilla casillaAtacada, Jugador atacante, Tablero tableroAtacado) {
        this(id_counter++, impactoABarco, casillaAtacada, atacante, tableroAtacado);
    }

}
