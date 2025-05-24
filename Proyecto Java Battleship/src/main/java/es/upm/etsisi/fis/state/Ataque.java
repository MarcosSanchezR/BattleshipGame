package es.upm.etsisi.fis.state;

import java.util.Optional;

public record
Ataque(
        int id,
        Optional<Barco> barcoImpactado,

        Casilla casillaAtacada,
        Jugador atacante,
        Tablero tableroAtacado
) {

    private static int id_counter = 0;

    public Ataque(Optional<Barco> barcoImpactado, Casilla casillaAtacada, Jugador atacante, Tablero tableroAtacado) {
        this(id_counter++, barcoImpactado, casillaAtacada, atacante, tableroAtacado);
    }

}
