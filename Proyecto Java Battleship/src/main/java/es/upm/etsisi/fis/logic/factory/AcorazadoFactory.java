package es.upm.etsisi.fis.logic.factory;

import es.upm.etsisi.fis.state.Acorazado;
import es.upm.etsisi.fis.state.Barco;

public class AcorazadoFactory implements ShipFactory {

    @Override
    public Barco crearBarco() {
        return new Acorazado();
    }
}
