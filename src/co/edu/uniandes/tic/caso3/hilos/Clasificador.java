package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Clasificador {
    private int idClasificador;
    private Buzon buzonClasificacion;
    private Buzon[] buzonesConsolidacion;
    private int clasificadoresActivos;

    public Clasificador(int idClasificador, Buzon buzonClasificacion, Buzon[] buzonesConsolidacion) {
        this.idClasificador = idClasificador;
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesConsolidacion = buzonesConsolidacion;
        this.clasificadoresActivos = 0;
    }

    public void run() {

    }

    public int clasificar(Evento evento) {
        return 0;
    }

    public void registrarTerminacion() {

    }

    public boolean esUltimoEnTerminar() {
        return false;
    }

    public void enviarEventosFinAServidores() {
        
    }
}