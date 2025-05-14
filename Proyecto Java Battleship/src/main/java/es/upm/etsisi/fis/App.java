package es.upm.etsisi.fis;

import es.upm.etsisi.fis.logica.GameManager;
import es.upm.etsisi.fis.logica.PlayerManager;
import es.upm.etsisi.fis.presentacion.GameDisplay;
import es.upm.etsisi.fis.presentacion.PlayerDisplay;
import es.upm.etsisi.fis.presentacion.UI;

public class App {

    private final UI ui;

    public App(UI ui) {
        this.ui = ui;
    }

    public void start() {
        // Inicializar lo que toque cuando hagamos persistencia.
        this.ui.run();
    }

    public static void main(String[] args ){
        GameManager gameManager = new GameManager();
        PlayerManager playerManager = new PlayerManager();

        GameDisplay gameDisplay = new GameDisplay(gameManager, playerManager);
        PlayerDisplay playerDisplay = new PlayerDisplay(playerManager);

        App battleship = new App(new UI(playerDisplay, gameDisplay));

        battleship.start();
    }

}
