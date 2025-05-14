package es.upm.etsisi.fis.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Barco {

    private boolean hundido;
    private final int tamanio;
    private int habilidadesRestantes;

    private List<Casilla> casillasOcupadas;

    public Barco(int tamanio, int habilidadesRestantes) {
        this.hundido = false;
        this.tamanio = tamanio;
        this.habilidadesRestantes = habilidadesRestantes;
        this.casillasOcupadas = new ArrayList<Casilla>(tamanio);
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public int getTamanio() {
        return tamanio;
    }

    public int getHabilidadesRestantes() {
        return habilidadesRestantes;
    }

    public void setHabilidadesRestantes(int habilidadesRestantes) {
        this.habilidadesRestantes = habilidadesRestantes;
    }

    public List<Casilla> getCasillasOcupadas() {
        return casillasOcupadas;
    }

    public void setCasillasOcupadas(List<Casilla> casillasOcupadas) {
        this.casillasOcupadas = casillasOcupadas;
    }

    public void actualizarEstado(){
        boolean casillaVivaEncontrada = false;
        Iterator<Casilla> iterator = casillasOcupadas.iterator();
        do{
            Casilla casilla = iterator.next();
            if(!casilla.isImpactada()){
                casillaVivaEncontrada = true;
            }
        }while(!casillaVivaEncontrada && iterator.hasNext());

        boolean barcoHundido = !casillaVivaEncontrada;
        if(barcoHundido != this.hundido){
            this.hundido = barcoHundido;
        }
    }

    public boolean usarHabilidadEspecial(Tablero tableroEnemigo){
        return habilidadesRestantes > 0;
    }
}
