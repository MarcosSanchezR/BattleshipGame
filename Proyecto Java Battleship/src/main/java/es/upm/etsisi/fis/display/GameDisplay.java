package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IGameManager;
import es.upm.etsisi.fis.logic.IPlayerManager;
import es.upm.etsisi.fis.state.*;

import java.util.List;
import java.util.Scanner;


public class GameDisplay implements IGameDisplay, GameMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final Partida partida;
    private final IGameManager gameManager;
    private final IPlayerManager playerManager;

    public GameDisplay(IGameManager gameManager, IPlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
        this.partida = null;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IGameManager getGameManager() {
        return gameManager;
    }

    public IPlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void jugar() {
        JugadorHumano jugadorHumano = playerManager.getLoggedUser();
        gameManager.crearPartida(jugadorHumano);
    }

    @Override
    public void mostrarPuntuaciones() {

    }

    // @TODO: Implementar RF #18769
    @Override
    public String realizarAtaque() {
        // Leer coordenadas (fila, columna)
        System.out.print("VAS A REALIZAR UN ATAQUE");
        return getCoordenadas();
    }

    @Override
    public boolean getConfirmacionHabilidad(Barco barco) {
        System.out.print("¿Quieres activar la habilidad especial de tu barco" + barco.getNombre() + "? (S/N)");
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }

    @Override
    public int pedirFila() {
        System.out.println("Seleccione una fila");
        return scanner.nextInt();
    }

    @Override
    public int pedirColumna() {
        System.out.println("Seleccione una columna");
        return scanner.nextInt();
    }

    @Override
    public String getCoordenadas() {
        System.out.println("Introduce la coordenada (formato: x,y): ");
        if (scanner.hasNextLine()) scanner.nextLine();
        return scanner.nextLine();
    }

    @Override
    public void mostrarTop10Puntuaciones(){
    }

    @Override
    public void mostrarMiTablero() {
        System.out.println("Mi Tablero:");
        Tablero miTablero = partida.getTableroJugador();
        List<Barco> barcosPropios = partida.getTableroJugador().getBarcosPropios();
        Casilla[][] casillas = miTablero.getCasillas();

        for (int i = 0; i < Tablero.DIMENSION_TABLERO; i++) {
            for (int j = 0; j < Tablero.DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean ocupado = false;

                int k = 0;
                while (k < barcosPropios.size() && !ocupado) {
                    Barco barco = barcosPropios.get(k);
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;
                    }
                    k++;
                }

                if (ocupado) {
                    if (casilla.isImpactada()) {
                        System.out.print(" 💥 ");  // Barco impactado
                    } else {
                        System.out.print(" 🚢 ");  // Barco intacto
                    }
                } else {
                    if (casilla.isImpactada()) {
                        System.out.print(" X ");    // Agua impactada (fuego fallido)
                    } else {
                        System.out.print(" 🌊 ");   // Agua sin impacto
                    }
                }
            }
            System.out.println();
        }
    }


    @Override
    public void mostrarTableroEnemigo(){
    }

}
