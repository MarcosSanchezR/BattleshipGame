package es.upm.etsisi.fis.presentacion;

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

    private void showIntro() {
        System.out.println("BATTLESHIP - ETSISI'S EDITION");
        //@TODO: Hacer que esto funcione.
        System.out.println("Pulsa cualquier tecla para comenzar. (AUN NO FUNCIONA)");
        do{
            continue;
        }while(false);
    }

    public void run(){
        showIntro();
        // Lógica del programa
        boolean exit = false;
        do{

        }while(!exit);
    }

}
