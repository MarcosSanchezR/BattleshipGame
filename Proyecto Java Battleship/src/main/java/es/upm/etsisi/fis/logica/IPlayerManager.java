package es.upm.etsisi.fis.logica;

import es.upm.etsisi.fis.persistencia.Jugador;
import es.upm.etsisi.fis.persistencia.JugadorHumano;

public interface IPlayerManager {

    JugadorHumano getLoggedUser();

}
