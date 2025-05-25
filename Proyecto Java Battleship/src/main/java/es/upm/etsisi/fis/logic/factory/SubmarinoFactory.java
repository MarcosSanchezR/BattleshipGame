package es.upm.etsisi.fis.logic.factory;

import es.upm.etsisi.fis.state.Barco;
import es.upm.etsisi.fis.state.Submarino;

public class SubmarinoFactory implements ShipFactory{

    @Override
    public Barco crearBarco() {
        return new Submarino();
    }
}
