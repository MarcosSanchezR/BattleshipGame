package es.upm.etsisi.fis.display;

import es.upm.etsisi.fis.logic.IGameManager;
import es.upm.etsisi.fis.logic.IPlayerManager;
import es.upm.etsisi.fis.state.*;

import java.util.List;
import java.util.Scanner;

public class GameDisplay implements IGameDisplay, GameMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final IGameManager gameManager;
    private final IPlayerManager playerManager;

    public GameDisplay(IGameManager gameManager, IPlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
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
        Partida partida = gameManager.crearPartida(jugadorHumano);
        gameManager.jugarPartida(partida);
    }

    @Override
    public void mostrarPuntuaciones() {

    }

    @Override
    public void mostrarTop10Puntuaciones() {
    }

    @Override
    public boolean getConfirmacionHabilidad(Barco barco) {
        System.out.print("¿Quieres activar la habilidad especial de tu barco" + barco.getNombre() + "? (S/N)");
        return scanner.nextLine().trim().equalsIgnoreCase("S");
    }

    @Override
    public String getCoordenadas() {
        System.out.println("Introduce la coordenada (formato: x,y): ");
        return scanner.nextLine();
    }

    public void mostrarPuntuacion(Jugador ganador, double jugador, double maquina) {
        System.out.println("La partida ha acabado y el ganador es "+ganador.getNombre()+
                "\nLa maquina ha conseguido: "+maquina+" puntos\nHas conseguido: "+jugador+" puntos");

    }

    @Override
    public void mostrarMiTablero(Partida partida){
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
                        System.out.print(" ❌ ");    // Agua impactada (fuego fallido)
                    } else {
                        System.out.print(" 🌊 ");   // Agua sin impacto
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public int getFila() {
        int fila = 0;
        System.out.println("Seleccione una fila");
        fila = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine();
        return fila;
    }
    @Override
    public void mostrarTableroEnemigo(Partida partida){
        System.out.println("Tablero del Rival:");
        Tablero miTablero = partida.getTableroMaquina();
        List<Barco> barcosPropios = partida.getTableroMaquina().getBarcosPropios();
        Casilla[][] casillas = miTablero.getCasillas();

        for (int i = 0; i < Tablero.DIMENSION_TABLERO; i++) {
            for (int j = 0; j < Tablero.DIMENSION_TABLERO; j++) {
                Casilla casilla = casillas[i][j];
                boolean ocupado = false;

                int x = 0;
                while (x < barcosPropios.size() && !ocupado) {
                    Barco barco = barcosPropios.get(x);
                    if (barco.getCasillasOcupadas().contains(casilla)) {
                        ocupado = true;
                    }
                    x++;
                }

                if(casilla.isRevelada()){
                    if(casilla.getBarco().isPresent()){
                        System.out.print(" 🚢 "); // Barco revelado
                    }else {
                        System.out.print(" 🌊 "); //Agua revelada
                    }
                }else if (casilla.isImpactada()) {
                    if (ocupado) {
                        System.out.print(" 💥 ");  // Barco impactado
                    } else {
                        System.out.print(" ❌ ");    // Agua impactada (fuego fallido)
                    }
                } else {
                    System.out.print(" ☁️ ");       // Agua sin impacto (niebla de guerra)
                }
            }
            System.out.println();
        }
    }


}
