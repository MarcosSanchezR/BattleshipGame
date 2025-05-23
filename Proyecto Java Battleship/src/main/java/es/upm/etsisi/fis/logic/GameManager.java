package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.state.*;

import java.util.ArrayList;
import java.util.List;

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

        jugarPartida(partida);
    }

    private void jugarPartida(Partida partida) {
        Tablero tablero = partida.getTableroJugador();
        Tablero otroTablero = partida.getTableroMaquina();
        Jugador jugador = tablero.getPropietario();
        Jugador otroJugador = otroTablero.getPropietario();
        boolean partidaAcabada = false;
        do {
            Jugador leToca = partida.getJugadorConTurno();
            Ataque ataque = realizarAtaqueReglamentario(leToca);

        } while (!partidaAcabada);
    }


    private Maquina crearMaquina() {
        return new Maquina();
    }


    @Override
    public Ataque realizarAtaqueReglamentario(Jugador jugadorAtacante) {
        Tablero tableroObjetivo = getTableroRival(jugadorAtacante);
        int[] coordenadas = jugadorAtacante.getCoordenadasAtaque();
        int fila = coordenadas[0] - 1;
        int columna = coordenadas[1] - 1;
        Ataque ataqueRealizado = tableroObjetivo.atacarCasilla(fila, columna, jugadorAtacante);

        //@TODO: Comprobar si se acaba la partida

        return ataqueRealizado;
    }

    public Tablero getTableroRival(Jugador jugadorAtacante) {
        Partida partida = jugadorAtacante.getCurrentGame();
        Jugador unJugador = partida.getJugador();
        Jugador otroJugador = partida.getMaquina();

        return (jugadorAtacante.equals(unJugador)) ? otroJugador.getTablero() : unJugador.getTablero();
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
}
