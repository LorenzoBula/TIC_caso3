package co.edu.uniandes.tic.caso3.hilos;

import java.util.Random;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class SensorIoT extends Thread {
    private int idSensor;
    private int cantidadEventos;
    private int numeroServidores;
    private Buzon buzon;
    private Random random;

    public SensorIoT(int idSensor, int cantidadEventos, int numeroServidores, Buzon buzon) {
        this.idSensor = idSensor;
        this.cantidadEventos = cantidadEventos;
        this.numeroServidores = numeroServidores;
        this.buzon = buzon;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 1; i <= cantidadEventos; i++) {
            Evento evento = generarEvento(i);
            buzon.depositar(evento);
        }
    }

    public Evento generarEvento(int secuencial) {
        int tipo = random.nextInt(numeroServidores) + 1;
        String id = "S" + idSensor + "-E" + secuencial;

        return new Evento(id, idSensor, secuencial, tipo, false);
    }
}