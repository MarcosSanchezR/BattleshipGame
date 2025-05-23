package es.upm.etsisi.fis.state;

public class Casilla {

    private boolean impactada;
    private final int fila;
    private final int columna;
    private Barco barco;

    public Casilla(int fila, int columna) {
        this.impactada = false;
        this.fila = fila;
        this.columna = columna;
    }

    public boolean isImpactada() {
        return impactada;
    }

    public void setImpactada(boolean impactada) {
        this.impactada = impactada;
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

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public boolean tieneBarco() {
        return this.barco != null;
    }
}
