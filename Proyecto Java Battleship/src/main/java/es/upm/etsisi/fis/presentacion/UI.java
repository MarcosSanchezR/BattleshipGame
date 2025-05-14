package es.upm.etsisi.fis.presentacion;

import java.util.Scanner;

public class UI {

    private final IPlayerDisplay playerDisplay;
    private final IGameDisplay gameDisplay;

    public UI(IPlayerDisplay playerDisplay, IGameDisplay gameDisplay) {
        this.playerDisplay = playerDisplay;
        this.gameDisplay = gameDisplay;
    }

    public IPlayerDisplay getPlayerDisplay() {
        return playerDisplay;
    }

    public IGameDisplay getGameDisplay() {
        return gameDisplay;
    }

}
