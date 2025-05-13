package es.upm.etsisi.fis.persistencia;

public record Ataque(
        int id,
        boolean impactoABarco,

        Casilla casillaAtacada,
        Jugador atacante
){

    // prueba commit

    private static int id_counter = 0;

    public Ataque(boolean impactoABarco, Casilla casillaAtacada, Jugador atacante) {
        this(id_counter++, impactoABarco, casillaAtacada, atacante);
    }

}
