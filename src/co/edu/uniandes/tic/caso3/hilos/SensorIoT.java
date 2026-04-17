package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class SensorIoT {
    private int idSensor;
    private int cantidadEventos;
    private Buzon buzon;

    public SensorIoT(int idSensor, int cantidadEventos, Buzon buzon) {
        this.idSensor = idSensor;
        this.cantidadEventos = cantidadEventos;
        this.buzon = buzon;
    }

    public void run() {

    }

    public Evento generarEvento(int secuencial) {
        return null;
    }
}