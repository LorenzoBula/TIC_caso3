package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Admin {
    private Buzon buzonAlertas;
    private Buzon buzonClasificacion;
    private int numeroClasificadores;

    public Admin(Buzon buzonAlertas, Buzon buzonClasificacion, int numeroClasificadores) {
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        this.numeroClasificadores = numeroClasificadores;
    }

    public void run() {

    }

    public boolean pasaInspeccion(Evento evento) {
        return false;
    }

    public void enviarEventosFin() {
        
    }
}