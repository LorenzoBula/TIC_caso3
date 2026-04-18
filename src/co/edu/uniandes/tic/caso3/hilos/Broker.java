package co.edu.uniandes.tic.caso3.hilos;

import java.util.Random;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Broker extends Thread {
    private Buzon buzonEntrada;
    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int totalEventosEsperados;
    private int eventosProcesados;
    private Random random;

    public Broker(Buzon buzonEntrada, Buzon buzonAlertas, Buzon buzonClasificacion, int totalEventosEsperados) {
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.totalEventosEsperados = totalEventosEsperados;
        this.eventosProcesados = 0;
        this.random = new Random();
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

        Evento eventoFinAdmin = new Evento("FIN-ADMIN", -1, -1, -1, true);
        buzonAlertas.depositar(eventoFinAdmin);
    }

    public boolean esAnomalo(Evento evento) {
        int numero = random.nextInt(201); // 0 a 200
        return numero % 8 == 0;
    }
}