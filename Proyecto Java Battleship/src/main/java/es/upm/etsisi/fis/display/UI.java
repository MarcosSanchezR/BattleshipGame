package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.JugadorHumano;

import java.util.Optional;
import java.util.Scanner;

public class UI {

    private static final Scanner sc = new Scanner(System.in);

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
        System.out.println("Pulsa cualquier tecla para comenzar.");
        sc.nextLine();
        System.out.println();
    }

    public void run() {
        showIntro();
        menu();
    }

    public void menu() {
        boolean exit = false;
        do{
            Optional<JugadorHumano> loggedUser = getLoggedUser();
            int option;
            if (loggedUser.isEmpty()) {
                option = showNotLoggedMenu();
                switch (option) {
                    case 1 -> playerDisplay.mostrarAltaUsuario();
                    case 2 -> playerDisplay.mostrarIniciarSesion();
                    default -> throw new IllegalArgumentException("Opcion no valida");
                }
            } else {
                option = showLoggedMenu();
                switch (option) {
                    case 1 -> gameDisplay.iniciarPartida();
                    case 2 -> gameDisplay.mostrarTop10Puntuaciones();
                    case 3 -> {
                        playerDisplay.mostrarCerrarSesion();
                        exit = true;
                    }
                    case 4 -> {
                        playerDisplay.mostrarBajaUsuario();
                        exit = true;
                    }
                    default -> throw new IllegalArgumentException("Opcion no valida");
                }
            }
        }while(!exit);
    }

    private Optional<JugadorHumano> getLoggedUser() {
        return Optional.ofNullable(playerDisplay.getPlayerManager().getLoggedUser());
    }

    private int showNotLoggedMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Darse de alta\n");
        sb.append("2. Iniciar sesión\n");
        return sc.nextInt();
    }

    private int showLoggedMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Jugar partida\n");
        sb.append("2. Cerrar sesión\n");
        sb.append("3. Darse de baja\n");
        return sc.nextInt();
    }


}
