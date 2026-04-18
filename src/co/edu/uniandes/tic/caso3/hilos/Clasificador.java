package co.edu.uniandes.tic.caso3.hilos;

import co.edu.uniandes.tic.caso3.modelo.Evento;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Clasificador extends Thread {
    private int idClasificador;
    private Buzon buzonClasificacion;
    private Buzon[] buzonesConsolidacion;

    private static int clasificadoresActivos = 0;
    private static boolean finEnviadoAServidores = false;

    public Clasificador(int idClasificador, Buzon buzonClasificacion, Buzon[] buzonesConsolidacion) {
        this.idClasificador = idClasificador;
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesConsolidacion = buzonesConsolidacion;

        synchronized (Clasificador.class) {
            clasificadoresActivos++;
        }
    }

    @Override
    public void run() {
        boolean terminar = false;

        while (!terminar) {
            Evento evento = buzonClasificacion.retirar();

            if (evento.esFin()) {
                registrarTerminacion();

                if (esUltimoEnTerminar()) {
                    enviarEventosFinAServidores();
                }

                terminar = true;
            } else {
                int indiceServidor = clasificar(evento);
                buzonesConsolidacion[indiceServidor].depositar(evento);
            }
        }
    }

    public int clasificar(Evento evento) {
        int indice = evento.getTipo() - 1;

        if (indice < 0 || indice >= buzonesConsolidacion.length) {
            throw new IllegalArgumentException(
                    "Tipo de evento inválido para el clasificador " + idClasificador + ": " + evento.getTipo());
        }

        return indice;
    }

    public void registrarTerminacion() {
        synchronized (Clasificador.class) {
            clasificadoresActivos--;
        }
    }

    public boolean esUltimoEnTerminar() {
        synchronized (Clasificador.class) {
            return clasificadoresActivos == 0 && !finEnviadoAServidores;
        }
    }

    public void enviarEventosFinAServidores() {
        synchronized (Clasificador.class) {
            if (finEnviadoAServidores) {
                return;
            }

            for (int i = 0; i < buzonesConsolidacion.length; i++) {
                Evento eventoFin = new Evento("FIN-SERV-" + (i + 1), -1, -1, -1, true);
                buzonesConsolidacion[i].depositar(eventoFin);
            }

            finEnviadoAServidores = true;
        }
    }
}