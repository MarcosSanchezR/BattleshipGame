package es.upm.etsisi.fis.state;

import java.util.Optional;

public class Casilla {

    private boolean impactada;
    private boolean revelada;
    private final int fila;
    private final int columna;
    private Optional<Barco> barco;

    public Casilla(int fila, int columna) {
        this.impactada = false;
        this.revelada=false;
        this.fila = fila;
        this.columna = columna;
    }

    public boolean isImpactada() {
        return impactada;
    }

    public void setImpactada(boolean impactada) {
        this.impactada = impactada;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void marcarComoImpactada() {
        impactada = true;
    }

    public void marcarRevelada(){ revelada=true;}

    public Optional<Barco> getBarco() {
        return barco;
    }

    public void setBarco(Optional<Barco> barco) {
        this.barco = barco.isPresent() ? barco : Optional.empty();
    }

}
