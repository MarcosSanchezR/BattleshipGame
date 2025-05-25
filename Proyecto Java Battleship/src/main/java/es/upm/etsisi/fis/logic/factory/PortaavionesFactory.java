package es.upm.etsisi.fis.logic.factory;

import es.upm.etsisi.fis.state.Barco;
import es.upm.etsisi.fis.state.Portaviones;

public class PortaavionesFactory implements ShipFactory{
    @Override
    public Barco crearBarco() {
        return new Portaviones();
    }
}
