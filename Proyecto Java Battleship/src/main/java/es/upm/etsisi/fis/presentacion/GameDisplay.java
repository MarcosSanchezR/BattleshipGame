package es.upm.etsisi.fis.presentacion;

import es.upm.etsisi.fis.logica.IGameManager;
import es.upm.etsisi.fis.logica.IPlayerManager;
import es.upm.etsisi.fis.persistencia.Jugador;

import java.util.Scanner;

public class GameDisplay implements IGameDisplay {

    private final Scanner scanner = new Scanner(System.in);

    private IGameManager gameManager;
    private IPlayerManager playerManager;

    @Override
    public void realizarAtaque() {
        // Leer coordenadas (fila, columna)
        int fila = 0, columna = 0;
        // Llamada a ControladorPartida.realizarAtaque(fila, columna)

        Jugador jugadorAtacante = playerManager.getLoggedUser();
        gameManager.realizarAtaque(fila, columna, jugadorAtacante);
    }

    @Override
    public void confirmacionHabilidad() {

    }

    private String getCoordenadas(){
        return "";
    }

}
