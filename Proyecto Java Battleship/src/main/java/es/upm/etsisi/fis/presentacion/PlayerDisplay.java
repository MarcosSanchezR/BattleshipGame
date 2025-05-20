package es.upm.etsisi.fis.presentacion;

import es.upm.etsisi.fis.logica.IPlayerManager;
import es.upm.etsisi.fis.logica.PlayerManager;

import java.util.Scanner;

public class PlayerDisplay implements IPlayerDisplay {

    private final IPlayerManager playerManager;

    public PlayerDisplay(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public IPlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void mostrarAltaUsuario() {

    }

    @Override
    public void mostrarBajaUsuario() {

    }

    @Override
    public void mostrarIniciarSesion() {

    }

    @Override
    public void mostrarCerrarSesion() {

    }

    @Override
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            if(playerManager.getLoggedUser() == null){
                mostrarMenuSinLogin(sc);
            }
            else{
                mostrarMenuLogado(sc);
            }
        }
    }

    private void mostrarMenuSinLogin(Scanner sc){
        System.out.println("---------- MENU PRINCIPAL ----------");
        System.out.println("1 - Iniciar Sesion");
        System.out.println("2 - Registrarse");
        System.out.println("Por favor, elija una opción: ");
        int opcion = sc.nextInt();

        switch(opcion){
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

    private void mostrarMenuLogado(Scanner sc){
        System.out.println("---------- MENU PRINCIPAL ----------");
        System.out.println("1 - Jugar partida");
        System.out.println("2 - Darse de baja");
        System.out.println("2 - Cerrar sesion");
        System.out.println("Por favor, elija una opción: ");
        int opcion = sc.nextInt();

        switch(opcion){
            case 1:
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
