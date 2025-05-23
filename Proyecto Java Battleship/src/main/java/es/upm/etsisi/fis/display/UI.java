package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.state.JugadorHumano;

import java.util.Optional;
import java.util.Scanner;

public class UI {

    private static final Scanner sc = new Scanner(System.in);

    private final PlayerMenu playerDisplay;
    private final GameMenu gameDisplay;

    public UI(PlayerMenu playerDisplay, GameMenu gameDisplay) {
        this.playerDisplay = playerDisplay;
        this.gameDisplay = gameDisplay;
    }

    public PlayerMenu getPlayerDisplay() {
        return playerDisplay;
    }

    public GameMenu getGameDisplay() {
        return gameDisplay;
    }

    private void showIntro() {
        System.out.println("BATTLESHIP - ETSISI'S EDITION");
        System.out.println("Pulsa ENTER para comenzar.");
        sc.nextLine();
        System.out.println();
    }

    public void run() {
        showIntro();
        boolean exit;
        do {
            exit = menu();
        } while (!exit);
    }

    public boolean menu() {
        Optional<JugadorHumano> loggedUser = getLoggedUser();
        boolean result = false;
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
                case 1 -> gameDisplay.jugar();
                case 2 -> gameDisplay.mostrarPuntuaciones();
                case 3 -> {
                    playerDisplay.mostrarCerrarSesion();
                    result = true;
                }
                case 4 -> {
                    playerDisplay.mostrarBajaUsuario();
                    result = true;
                }
                default -> throw new IllegalArgumentException("Opcion no valida");
            }
        }
        return result;
    }

    private Optional<JugadorHumano> getLoggedUser() {
        return Optional.ofNullable(playerDisplay.getPlayerManager().getLoggedUser());
    }

    private int showNotLoggedMenu() {
        String menu = """
                1. Darse de alta
                2. Iniciar sesión
                """;
        System.out.print(menu);
        return sc.nextInt();
    }

    private int showLoggedMenu() {
        String menu = """
                1. Jugar partida
                2. Cerrar sesión
                3. Darse de baja
                """;
        System.out.print(menu);
        return sc.nextInt();
    }


}
