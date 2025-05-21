package es.upm.etsisi.fis.state;

import es.upm.etsisi.fis.logica.GameManager;

public class Partida {

    private static int id_counter = 0;

    private int id;
    private int turnos;
    private double puntuacionJugador;
    private double puntuacionMaquina;

    private final Jugador jugador;
    private final Jugador maquina;
    private final Tablero tableroJugador;
    private final Tablero tableroMaquina;
    private GameManager gameManager;

    public Partida(Jugador jugador, Jugador maquina) {
        this.id = id_counter++;
        this.turnos = 0;
        this.puntuacionJugador = 0;
        this.puntuacionMaquina = 0;

        this.jugador = jugador;
        this.maquina = maquina;
        this.tableroJugador = new Tablero(jugador);
        this.tableroMaquina = new Tablero(maquina);

        this.jugador.setTablero(tableroJugador);
        this.jugador.setTurno(true);

        this.maquina.setTablero(tableroMaquina);
    }

    public static int getId_counter() {
        return id_counter;
    }

    public static void setId_counter(int id_counter) {
        Partida.id_counter = id_counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public double getPuntuacionJugador() {
        return puntuacionJugador;
    }

    public void setPuntuacionJugador(double puntuacionJugador) {
        this.puntuacionJugador = puntuacionJugador;
    }

    public double getPuntuacionMaquina() {
        return puntuacionMaquina;
    }

    public void setPuntuacionMaquina(double puntuacionMaquina) {
        this.puntuacionMaquina = puntuacionMaquina;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Jugador getMaquina() {
        return maquina;
    }

    public Tablero getTableroJugador() {
        return tableroJugador;
    }

    public Tablero getTableroMaquina() {
        return tableroMaquina;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public static Partida inicializarPartida(Jugador humano, Jugador maquina){
        Partida partida = new Partida(humano, maquina);
        humano.setCurrentGame(partida);
        maquina.setCurrentGame(partida);
        return partida;
    }

    public Jugador cambiarTurnos(){
        boolean turnoJugador = jugador.isTurno();
        this.jugador.setTurno(!turnoJugador);
        this.maquina.setTurno(turnoJugador);
        this.turnos++;
        return (this.jugador.isTurno()) ? this.jugador : this.maquina;
    }
}
