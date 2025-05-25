package es.upm.etsisi.fis;

import es.upm.etsisi.fis.display.GameDisplay;
import es.upm.etsisi.fis.display.PlayerDisplay;
import es.upm.etsisi.fis.display.UI;
import es.upm.etsisi.fis.logic.GameManager;
import es.upm.etsisi.fis.logic.PlayerManager;

public class App {


    private final UI ui;

    public App(UI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {

        GameManager gameManager = GameManager.getInstance();
        PlayerManager playerManager = new PlayerManager();

        GameDisplay gameDisplay = new GameDisplay(gameManager, playerManager);
        PlayerDisplay playerDisplay = new PlayerDisplay(playerManager);
        gameManager.setGameDisplay(gameDisplay);

        App battleship = new App(new UI(playerDisplay, gameDisplay));

        battleship.start();
    }

    public void start() {
        // Inicializar lo que toque cuando hagamos persistencia.
        this.ui.run();
    }

}
