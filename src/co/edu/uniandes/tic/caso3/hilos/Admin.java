package co.edu.uniandes.tic.caso3.hilos;

import java.util.Random;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Admin extends Thread {
    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int numeroClasificadores;
    private Random random;

    public Admin(Buzon buzonAlertas, Buzon buzonClasificacion, int numeroClasificadores) {
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.numeroClasificadores = numeroClasificadores;
        this.random = new Random();
    }

    @Override
    public void run() {
        boolean terminar = false;

        while (!terminar) {
            Evento evento = buzonAlertas.retirar();

            if (evento.esFin()) {
                enviarEventosFin();
                terminar = true;
            } else {
                if (pasaInspeccion(evento)) {
                    buzonClasificacion.depositar(evento);
                }
            }
        }
    }

    public boolean pasaInspeccion(Evento evento) {
        int numero = random.nextInt(21); // 0 a 20
        return numero % 4 == 0;
    }

    public void enviarEventosFin() {
        for (int i = 1; i <= numeroClasificadores; i++) {
            Evento eventoFin = new Evento("FIN-CLAS-" + i, -1, -1, -1, true);
            buzonClasificacion.depositar(eventoFin);
        }
    }
}