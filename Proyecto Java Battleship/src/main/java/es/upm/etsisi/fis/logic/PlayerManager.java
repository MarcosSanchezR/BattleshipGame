package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.JugadorHumano;
import servidor.ExternalLDAP;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements IPlayerManager {

    private final List<JugadorHumano> jugadores;
    private JugadorHumano loggedUser;

   public PlayerManager() {
        this.jugadores = new ArrayList<>();
    }

    public List<JugadorHumano> getJugadores() {
        return jugadores;
    }

    @Override
    public JugadorHumano getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(JugadorHumano loggedUser) {
        this.loggedUser = loggedUser;
    }

    //metodo que devuelve true si se da de alta un jugador
    public boolean darDeAlta(String username, String correo, String contra) {
        String nombreCifrado = ExternalLDAP.LoginLDAP();
        if (nombreCifrado == null) {
            return false;
        }
        if (JugadorExistente(correo, contra) != null) {
            return false;
        }
        JugadorHumano jugadorHumano = new JugadorHumano(username, correo, contra, false);
        jugadores.add(jugadorHumano);
        return true;
    }

    //Método que devuelve el jugador que coincida con el correo y contraseña que se pasen
    // por parámetros de la lista de jugadores
    public JugadorHumano JugadorExistente(String correo, String contra) {
        for (JugadorHumano jugador : jugadores) {
            if (jugador.getCorreo().equals(correo) && jugador.getPassword().equals(contra)) {
                return jugador;
            }
        }
        return null;
    }

    //devuelve true si te puedes dar de baja false si no puedes
    public boolean darDeBaja(String correo, String contra) {
        JugadorHumano jugador = JugadorExistente(correo, contra);
        if (jugador != null) {
            jugadores.remove(jugador);
            cerrarSesion();
            return true;
        }
        return false;
    }

    //devuelve true si ha podido iniciar sesion false si no ha podido
    public boolean iniciarSesion(String correo, String contra) {
        JugadorHumano jugadorEncontrado = JugadorExistente(correo, contra);
        if (jugadorEncontrado == null) {
            return false;
        } else {
            this.loggedUser = jugadorEncontrado;
            return true;
        }
    }

    //pone el usuario actual a null
    public void cerrarSesion() {
        if (loggedUser != null) {
            setLoggedUser(null);
        }
    }
}
