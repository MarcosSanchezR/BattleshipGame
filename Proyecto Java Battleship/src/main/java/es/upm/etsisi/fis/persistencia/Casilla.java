package es.upm.etsisi.fis.persistencia;

public class Casilla {

    private boolean impactada;
    private final int fila;
    private final int columna;

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

    public void marcarComoImpactada(){
        impactada = true;
    }

}
