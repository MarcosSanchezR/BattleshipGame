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
    public String realizarAtaque() {
        // Leer coordenadas (fila, columna)
        System.out.print("VAS A REALIZAR UN ATAQUE");
        return getCoordenadas();
    }

    @Override
    public void confirmacionHabilidad() {

    }

    @Override
    public int pedirFila() {
        System.out.println("Seleccione una fila");
        return scanner.nextInt();
    }

    @Override
    public int pedirColumna() {
        System.out.println("Seleccione una columna");
        return scanner.nextInt();
    }


    private String getCoordenadas(){
        System.out.println("Introduce la coordenada (formato: x,y): ");
        if (scanner.hasNextLine()) scanner.nextLine();
        return scanner.nextLine();
    }

}
//ttst