package es.upm.etsisi.fis.presentacion;

import java.util.Scanner;

public class UI {

    private Scanner sc = new Scanner(System.in);
    private IPlayerDisplay vistaUsuario;
    private IGameDisplay vistaPartida;

    public UI(Scanner sc, IPlayerDisplay vistaUsuario, IGameDisplay vistaPartida) {
        this.sc = sc;
        this.vistaUsuario = vistaUsuario;
        this.vistaPartida = vistaPartida;
    }
}
