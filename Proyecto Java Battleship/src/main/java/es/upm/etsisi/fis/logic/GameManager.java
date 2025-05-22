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
    public void jugar(JugadorHumano jugadorHumano) {
        Maquina maquina = crearMaquina();
        Partida partida = new Partida(jugadorHumano, maquina);

        partidas.add(partida);
        jugadorHumano.aniadirPartida(partida);
        maquina.aniadirPartida(partida);

        jugadorHumano.setCurrentGame(partida);
        maquina.setCurrentGame(partida);

        jugar(partida);
    }

    private void jugar(Partida partida) {
        Tablero tablero = partida.getTableroJugador();
        Tablero otroTablero = partida.getTableroMaquina();
        Jugador jugador = tablero.getPropietario();
        Jugador otroJugador = otroTablero.getPropietario();
        boolean partidaAcabada = false;
        do{
            Jugador leToca = partida.getJugadorConTurno();
            Ataque ataque = realizarAtaque(leToca);

        }while(!partidaAcabada);
    }


    private Maquina crearMaquina() {
        return new Maquina();
    }


    @Override
    public Ataque realizarAtaque(Jugador jugadorAtacante) {
        String coordenadas = jugadorAtacante.getCoordenadasAtaque();

        Tablero tableroObjetivo = getTableroRival(jugadorAtacante);

        return tableroObjetivo.atacarCasilla(fila, columna, jugadorAtacante);
    }

    public Tablero getTableroRival(Jugador jugadorAtacante) {
        Partida partida = jugadorAtacante.getCurrentGame();
        Jugador unJugador = partida.getJugador();
        Jugador otroJugador = partida.getMaquina();

        return (jugadorAtacante.equals(unJugador)) ? otroJugador.getTablero() : unJugador.getTablero();
    }

    @Override
    public int pedirFila() {
        int fila = gameDisplay.pedirFila() - 1;
        if (fila > 9 || fila < 0) {
            throw new IllegalArgumentException("La fila esta fuera de los limites del tablero: 10 >= " + fila + " >= " +
                    "1");
        }
        return fila;
    }

    public String pedirCoordenadas() {
        this.gameDisplay.getCoordenadas();
    }
}
