package es.upm.etsisi.fis.state;

public record Ataque(
        int id,
        boolean impactoABarco,

        Casilla casillaAtacada,
        Jugador atacante
) {

    // prueba commit
    // prueba numero 2

    private static int id_counter = 0;

    public Ataque(boolean impactoABarco, Casilla casillaAtacada, Jugador atacante) {
        this(id_counter++, impactoABarco, casillaAtacada, atacante);
    }

}
