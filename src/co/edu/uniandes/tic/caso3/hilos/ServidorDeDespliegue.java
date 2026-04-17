package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class ServidorDeDespliegue {
    private int idServidor;
    private Buzon buzonConsolidacion;

    public ServidorDeDespliegue(int idServidor, Buzon buzonConsolidacion) {
        this.idServidor = idServidor;
        this.buzonConsolidacion = buzonConsolidacion;
    }

    public void run() {

    }

    public void procesar(Evento evento) {

    }
}