package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Broker extends Thread {
    private Buzon buzonEntrada;
    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int totalEventosEsperados;
    private int eventosProcesados;

    public Broker(Buzon buzonEntrada, Buzon buzonAlertas, Buzon buzonClasificacion, int totalEventosEsperados) {
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.totalEventosEsperados = totalEventosEsperados;
        this.eventosProcesados = 0;
    }


    @Override
    public void run() {
        while (eventosProcesados < totalEventosEsperados) {
            Evento e = buzonEntrada.retirarSinEspera(); 

            if (e == null) {
                Thread.yield(); 
            } else {
                eventosProcesados++;
                
                if (esAnomalo(e)) {
                    buzonAlertas.depositar(e);
                } else {
                    buzonClasificacion.depositar(e);
                }
            }
        }
    }
    

    public boolean esAnomalo(Evento evento) {
        return false;
    }
}
