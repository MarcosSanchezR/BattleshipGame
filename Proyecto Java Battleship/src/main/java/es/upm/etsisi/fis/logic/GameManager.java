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

    private Maquina crearMaquina() {
        return new Maquina();
    }

    private void jugarPartida(Partida partida) {
        boolean partidaAcabada;
        Jugador leToca = partida.getJugadorConTurno();
        do {
            Ataque ataque = realizarAtaqueReglamentario(leToca, getTableroRival(leToca));
            Jugador victima = ataque.tableroAtacado().getPropietario();
            if(ataque.barcoImpactado().isPresent() && victima.confirmacionHabilidad()){
                Barco barcoAtacado = ataque.barcoImpactado().get();
                barcoAtacado.usarHabilidadEspecial();
            }
            partidaAcabada = comprobarFinPartida(partida);
            leToca = partida.cambiarTurnos();
        } while (!partidaAcabada);
        //@TODO: Lógica de puntuaciones AQUÍ
        Jugador humano = partida.getJugador();
        Jugador maquina = partida.getMaquina();
        double puntuacionHumano = partida.calcularPuntuacion(humano);
        double puntuacionMaquina = partida.calcularPuntuacion(maquina);
        Jugador ganador;
        if(puntuacionHumano > puntuacionMaquina){
            puntuacionHumano += 20;
            puntuacionMaquina -= 20;
            ganador = humano;
        } else {
            puntuacionHumano -= 20;
            puntuacionMaquina += 20;
            ganador = maquina;
        }
        gameDisplay.mostrarPuntuacion(ganador);


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

    private boolean comprobarTableroHundido(Tablero tablero){
        boolean hundido = true;

        for (Barco barco:tablero.getBarcosPropios()){
            for (Casilla casilla: barco.getCasillasOcupadas()){
                if (casilla.isImpactada()){
                    hundido = false;
                }
                //@FIXME: Corregir este for anidado con un while/do-while
            }
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

    public boolean pedirConfirmacionHabilidad() {
        return gameDisplay.getConfirmacionHabilidad();
    }
}
