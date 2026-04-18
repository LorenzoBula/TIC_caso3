package co.edu.uniandes.tic.caso3.hilos;

import java.util.Random;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class ServidorDeDespliegue extends Thread {
    private int idServidor;
    private Buzon buzonConsolidacion;
    private Random random;

    public ServidorDeDespliegue(int idServidor, Buzon buzonConsolidacion) {
        this.idServidor = idServidor;
        this.buzonConsolidacion = buzonConsolidacion;
        this.random = new Random();
    }

    @Override
    public void run() {
        boolean terminar = false;

        while (!terminar) {
            Evento evento = buzonConsolidacion.retirar();

            if (evento.esFin()) {
                terminar = true;
            } else {
                procesar(evento);
            }
        }
    }

    public void procesar(Evento evento) {
        try {
            int tiempoProcesamiento = random.nextInt(901) + 100; // 100 a 1000 ms
            Thread.sleep(tiempoProcesamiento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}