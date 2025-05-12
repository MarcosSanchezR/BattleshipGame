package es.upm.etsisi.fis;

import es.upm.etsisi.fis.logica.GameManager;
import es.upm.etsisi.fis.logica.PlayerManager;
import es.upm.etsisi.fis.presentacion.GameDisplay;
import es.upm.etsisi.fis.presentacion.IGameDisplay;
import es.upm.etsisi.fis.presentacion.UI;

public class App {

    private final UI ui = new UI();

    public static void main( String[] args ){
        GameManager gameManager = new GameManager();
        PlayerManager playerManager = new PlayerManager();

        GameDisplay gameDisplay = new GameDisplay(IGameManager);
    }
}
