package es.upm.etsisi.fis.logic.factory;

import es.upm.etsisi.fis.state.Barco;
import es.upm.etsisi.fis.state.Patrullero;

public class PatrulleroFactory implements ShipFactory{

    @Override
    public Barco crearBarco() {
        return new Patrullero();
    }
}
