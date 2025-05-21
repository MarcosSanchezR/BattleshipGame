package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IGameManager;
import es.upm.etsisi.fis.logic.IPlayerManager;
import es.upm.etsisi.fis.state.Jugador;

import java.util.Scanner;

public class GameDisplay implements IGameDisplay {

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

    // @TODO: Implementar RF #18769
    @Override
    public void realizarAtaque() {
        // Leer coordenadas (fila, columna)
        System.out.print("VAS A REALIZAR UN ATAQUE");
        System.out.print("Introduce la FILA que quieras atacar (0-9): ");
        int fila = scanner.nextInt();
        System.out.print("Introduce la COLUMNA que quieras atacar (0-9): ");
        int columna = scanner.nextInt();
        // int fila = 0, columna = 0;
        // Llamada a ControladorPartida.realizarAtaque(fila, columna)

        Jugador jugadorAtacante = playerManager.getLoggedUser();
        gameManager.realizarAtaque(fila, columna, jugadorAtacante);
    }

    @Override
    public void confirmacionHabilidad() {

    }

    private String getCoordenadas(){
        return "";
    }

}
