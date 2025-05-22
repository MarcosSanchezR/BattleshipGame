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
    public void iniciarPartida(JugadorHumano jugadorHumano) {
        Maquina maquina = crearMaquina();
        Partida partida = new Partida(jugadorHumano, maquina);
        partidas.add(partida);
        jugadorHumano.setCurrentGame(partida);
        maquina.setCurrentGame(partida);
        //@TODO: Lógica de juego (otros métodos)
    }


    private Maquina crearMaquina() {
        return new Maquina();
    }


    @Override
    public Ataque realizarAtaque(Jugador jugadorAtacante) {
        int fila = 0;
        int columna = 0;
        while (fila == 0 || columna == 0) {
            String coordenadas = gameDisplay.realizarAtaque();
            String[] partes = coordenadas.split(",");
            if (partes.length == 2) {
                try {
                    fila = Integer.parseInt(partes[0].trim());
                    columna = Integer.parseInt(partes[1].trim());
                } catch (NumberFormatException _) {
                    System.out.println("La coordenada introducida debe ser numerica");
                }
            } else {
                System.out.println("No has respetado el formato");
            }
        }

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
}
