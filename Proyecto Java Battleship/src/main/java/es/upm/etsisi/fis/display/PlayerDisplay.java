package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IPlayerManager;
import es.upm.etsisi.fis.logic.PlayerManager;

import java.util.Scanner;

public class PlayerDisplay implements IPlayerDisplay {

    private final IPlayerManager playerManager;

    private final Scanner sc = new Scanner(System.in);

    public PlayerDisplay(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public IPlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void mostrarAltaUsuario() {
        System.out.println("---------- REGISTRO USUARIO ----------");
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        System.out.println("Contraseña: ");
        String contrasena = sc.nextLine();
        boolean registro = playerManager.darDeAlta(username, correo, contrasena);
    }

    @Override
    public void mostrarBajaUsuario() {
        System.out.println("---------- BAJA USUARIO ----------");
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        boolean baja = playerManager.darDeBaja(correo, contrasena);
    }

    @Override
    public void mostrarIniciarSesion() {
        System.out.println("---------- INICIAR SESION ----------");
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        System.out.println("Contraseña: ");
        String contrasena = sc.nextLine();
        boolean login = playerManager.iniciarSesion(correo, contrasena);
    }

    @Override
    public void mostrarCerrarSesion() {
        playerManager.cerrarSesion();
    }

    @Override
    public void mostrarMenu() {
        while (true) {
            if (playerManager.getLoggedUser() == null) {
                mostrarMenuSinLogin();
            } else {
                mostrarMenuLogado();
            }
        }
    }

    private void mostrarMenuSinLogin() {
        System.out.println("---------- MENU PRINCIPAL ----------");
        System.out.println("1 - Iniciar Sesion");
        System.out.println("2 - Registrarse");
        System.out.print("Por favor, elija una opción: ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                mostrarIniciarSesion();
                break;
            case 2:
                mostrarAltaUsuario();
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    private void mostrarMenuLogado() {
        System.out.println("---------- MENU PRINCIPAL ----------");
        System.out.println("1 - Jugar partida");
        System.out.println("2 - Darse de baja");
        System.out.println("3 - Cerrar sesion");
        System.out.print("Por favor, elija una opción: ");
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                //jugarpartida
                break;
            case 2:
                mostrarBajaUsuario();
                break;
            case 3:
                mostrarCerrarSesion();
            default:
                System.out.println("Opcion no valida.");
        }
    }
}
