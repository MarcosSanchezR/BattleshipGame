package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.logic.factory.*;
import es.upm.etsisi.fis.state.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GameManager implements IGameManager {

    private static GameManager instance;

    private final List<Partida> partidas;
    private GameDisplay gameDisplay;

    public GameManager() {
        this.partidas = new ArrayList<>();
        setGameDisplay(gameDisplay);

    }

    public static GameManager getInstance() {
        if (instance == null){
            instance = new GameManager();
        }
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
    public Partida crearPartida(JugadorHumano jugadorHumano) {
        Maquina maquina = crearMaquina();
        Partida partida = new Partida(jugadorHumano, maquina);

        partidas.add(partida);
        jugadorHumano.aniadirPartida(partida);
        maquina.aniadirPartida(partida);

        jugadorHumano.setCurrentGame(partida);
        maquina.setCurrentGame(partida);

        crearYColocarBarcos(jugadorHumano.getTablero(), maquina.getTablero());

        return partida;
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
        boolean colocado = false;

        while (!colocado){
            boolean horizontal = random.nextBoolean();
            int fila = horizontal ? random.nextInt(10) : random.nextInt(11-size);
            int columna = horizontal ? random.nextInt(11-size) : random.nextInt(10);

            List<Casilla> posibles = posiblesCasillas(horizontal, fila, columna, size, tablero);

            boolean puedeColocar = posibles.stream().allMatch(casilla -> casilla.getBarco().isEmpty());

            if (puedeColocar){
                for (Casilla c : posibles){
                    c.setBarco(Optional.of(barco));
                    barco.getCasillasOcupadas().add(c);
                }
                tablero.getBarcosPropios().add(barco);
                barco.setTablero(tablero);
                colocado = true;
            }
        }
    }

    private List<Casilla> posiblesCasillas (boolean horizontal, int fila, int columna, int size, Tablero tablero){
        List<Casilla> posibles = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int f = horizontal ? fila : fila + i;
            int c = horizontal ? columna + i : columna;
            Casilla casilla = tablero.getCasillas()[f][c];
            posibles.add(casilla);
        }
        return posibles;
    }

    private Maquina crearMaquina() {
        return new Maquina();
    }

    @Override
    public void jugarPartida(Partida partida) {
        boolean partidaAcabada;
        Jugador leToca = partida.getJugadorConTurno();
        do {
            gameDisplay.mostrarMiTablero(partida);
            gameDisplay.mostrarTableroEnemigo(partida);
            Ataque ataque;
            do {
                ataque = realizarAtaqueReglamentario(leToca, getTableroRival(leToca));
            }while (casillaImpactada(ataque, leToca));
            Jugador victima = ataque.tableroAtacado().getPropietario();
            if(ataque.barcoImpactado().isPresent()){
                Barco barcoAtacado = ataque.barcoImpactado().get();
                if(barcoAtacado.tieneHabilidadesRestantes() && victima.confirmacionHabilidad(barcoAtacado)) {
                    barcoAtacado.usarHabilidadEspecial();
                }
            }
            leToca.aniadirAtaque(ataque);
            partidaAcabada = comprobarFinPartida(partida);
            leToca = partida.cambiarTurnos();
        } while (!partidaAcabada);

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
        gameDisplay.mostrarPuntuacion(ganador, puntuacionHumano, puntuacionMaquina);


    }

    private boolean casillaImpactada(Ataque ataque, Jugador jugador){
        List<Ataque> ataques = jugador.getAtaquesRealizados();
        boolean hayImpacto = false;
        if (!ataques.isEmpty()) {
            int i = 0;
            do {
                if (ataques.get(i).getCasilla().equals(ataque.getCasilla())) {
                    hayImpacto = true;
                }
                i++;
            } while (i < ataques.size() && !hayImpacto);
        }
        return hayImpacto;
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
            fila = gameDisplay.getFila();
        } while (!coordenadaValida(fila));

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
