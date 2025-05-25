package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.logic.factory.*;
import es.upm.etsisi.fis.state.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements IGameManager {

    private static final GameManager instance = new GameManager();

    private final List<Partida> partidas;
    private GameDisplay gameDisplay;

    public GameManager() {
        this.partidas = new ArrayList<>();
    }

    public static GameManager getInstance() {
        return instance;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }

    public void setGameDisplay(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;
    }

    @Override
    public void crearPartida(JugadorHumano jugadorHumano) {
        Maquina maquina = crearMaquina();
        Partida partida = new Partida(jugadorHumano, maquina);

        partidas.add(partida);
        jugadorHumano.aniadirPartida(partida);
        maquina.aniadirPartida(partida);

        jugadorHumano.setCurrentGame(partida);
        maquina.setCurrentGame(partida);

        crearYColocarBarcos(jugadorHumano.getTablero(), maquina.getTablero());

        jugarPartida(partida);
    }

    private void crearYColocarBarcos(Tablero unTablero, Tablero otroTablero) {
        List<ShipFactory> creadoresBarco = List.of(
                new PortaavionesFactory(),
                new SubmarinoFactory(),
                new AcorazadoFactory(),
                new PatrulleroFactory()
        );
        for (ShipFactory factory : creadoresBarco) {
            Barco unBarco = factory.crearBarco();
            Barco otroBarco = factory.crearBarco();
            colocarBarco(unBarco, unTablero);
            colocarBarco(otroBarco, otroTablero);
        }
    }

    //@TODO: Implementar esto
    private void colocarBarco(Barco barco, Tablero tablero) {
        int size = barco.getTamanio();
        Random random = new Random();
        // Mirar API random.nextInt(int origin, int bound) y tener en cuenta el size del barco
    }

    private Maquina crearMaquina() {
        return new Maquina();
    }

    private void jugarPartida(Partida partida) {
        boolean partidaAcabada;
        Jugador leToca = partida.getJugadorConTurno();
        do {
            Ataque ataque = realizarAtaqueReglamentario(leToca, getTableroRival(leToca));
            Jugador victima = ataque.tableroAtacado().getPropietario();
            if(ataque.barcoImpactado().isPresent()){
                Barco barcoAtacado = ataque.barcoImpactado().get();
                if(barcoAtacado.tieneHabilidadesRestantes() && victima.confirmacionHabilidad(barcoAtacado)) {
                    barcoAtacado.usarHabilidadEspecial();
                }
            }
            partidaAcabada = comprobarFinPartida(partida);
            leToca = partida.cambiarTurnos();
        } while (!partidaAcabada);
        //@TODO: Lógica de puntuaciones AQUÍ
    }

    public Tablero getTableroRival(Jugador jugadorAtacante) {
        Partida partida = jugadorAtacante.getCurrentGame();
        Jugador unJugador = partida.getJugador();
        Jugador otroJugador = partida.getMaquina();

        return (jugadorAtacante.equals(unJugador)) ? otroJugador.getTablero() : unJugador.getTablero();
    }

    @Override
    public Ataque realizarAtaqueReglamentario(Jugador jugadorAtacante, Tablero tableroObjetivo) {
        int[] coordenadas = jugadorAtacante.getCoordenadasAtaque();
        int fila = coordenadas[0] - 1;
        int columna = coordenadas[1] - 1;

        return tableroObjetivo.atacarCasilla(fila, columna, jugadorAtacante);
    }

    private boolean comprobarFinPartida(Partida partida) {
        boolean usuarioHundido = comprobarTableroHundido(partida.getTableroJugador());
        boolean maquinaHundida = comprobarTableroHundido(partida.getTableroMaquina());

        return (usuarioHundido || maquinaHundida);
    }

    //@FIXME: Corregir este for anidado con un while/do-while
    private boolean comprobarTableroHundido(Tablero tablero){
        boolean hundido = true;
        int i = 0;

        while (i< tablero.getBarcosPropios().size() && hundido){
            Barco barco =  tablero.getBarcosPropios().get(i);
            int j = 0;

            while (j < barco.getCasillasOcupadas().size() && hundido){
                Casilla casilla = barco.getCasillasOcupadas().get(j);

                if (!casilla.isImpactada()){
                    hundido = false;
                }
                j++;
            }
            i++;
        }

        return hundido;
    }

    @Override
    public int pedirFila() {
        int fila;
        do {
            fila = gameDisplay.pedirFila();
        } while (coordenadaValida(fila));

        return fila;
    }

    public int[] pedirCoordenadas() {
        int fila;
        int columna;
        String input;
        do {
            input = gameDisplay.getCoordenadas();
            String[] filaYColumna = input.split(",");
            fila = Integer.parseInt(filaYColumna[0].trim());
            columna = Integer.parseInt(filaYColumna[1].trim());
        } while (!(coordenadaValida(columna) && coordenadaValida(fila)));

        return new int[]{fila, columna};
    }

    private boolean coordenadaValida(int coordenada) {
        return coordenada >= 1 && coordenada <= Tablero.DIMENSION_TABLERO;
    }

    public boolean pedirConfirmacionHabilidad(Barco barco) {
        return gameDisplay.getConfirmacionHabilidad(barco);
    }
}
