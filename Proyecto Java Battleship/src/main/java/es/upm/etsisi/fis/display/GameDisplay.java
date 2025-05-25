package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IGameManager;
import es.upm.etsisi.fis.logic.IPlayerManager;
import es.upm.etsisi.fis.state.Barco;
import es.upm.etsisi.fis.state.JugadorHumano;
import es.upm.etsisi.fis.state.Partida;

import java.util.Scanner;

public class GameDisplay implements IGameDisplay, GameMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final IGameManager gameManager;
    private final IPlayerManager playerManager;

    public GameDisplay(IGameManager gameManager, IPlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IGameManager getGameManager() {
        return gameManager;
    }

    public IPlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void jugar() {
        JugadorHumano jugadorHumano = playerManager.getLoggedUser();
        Partida partida = gameManager.crearPartida(jugadorHumano);
        gameManager.jugarPartida(partida);
    }

    @Override
    public void mostrarPuntuaciones() {

    }

    @Override
    public void mostrarTop10Puntuaciones(){

    }

    @Override
    public void mostrarTablero() {

    }

    @Override
    public String getCoordenadas() {
        System.out.println("Introduce la coordenada (formato: x,y): ");
        if (scanner.hasNextLine()) scanner.nextLine();
        return scanner.nextLine();
    }

    public void mostrarPuntuacion(Jugador ganador) {
    }

    public void mostrarTop10Puntuaciones(){
    }

    // @TODO: Implementar RF #18769
    @Override
    public boolean getConfirmacionHabilidad(Barco barco) {
        System.out.print("¿Quieres activar la habilidad especial de tu barco" + barco.getNombre() + "? (S/N)");
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }

    @Override
    public int getFila() {
        System.out.println("Seleccione una fila");
        return scanner.nextInt();
    }

}
