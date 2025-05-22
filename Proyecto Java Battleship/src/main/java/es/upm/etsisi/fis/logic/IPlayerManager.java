package es.upm.etsisi.fis.logic;

import es.upm.etsisi.fis.state.JugadorHumano;

public interface IPlayerManager {

    JugadorHumano getLoggedUser();

    boolean darDeAlta(String username, String correo, String contra);

    boolean darDeBaja(String correo, String contra);

    boolean iniciarSesion(String correo, String contra);

    void cerrarSesion();
}
