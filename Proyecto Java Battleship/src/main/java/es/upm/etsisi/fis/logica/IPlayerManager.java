package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.Jugador;
import es.upm.etsisi.fis.persistencia.JugadorHumano;

public interface IPlayerManager {

    JugadorHumano getLoggedUser();
    boolean darDeAlta(String username, String correo, String contra);
    boolean darDeBaja(String correo, String contra);
    boolean iniciarSesion(String correo, String contra);
    void cerrarSesion();
}
