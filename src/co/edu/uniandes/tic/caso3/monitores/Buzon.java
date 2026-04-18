package co.edu.uniandes.tic.caso3.monitores;

import java.util.LinkedList;
import java.util.Queue;

import co.edu.uniandes.tic.caso3.modelo.Evento;

public class Buzon {
    private int capacidad;
    private Queue<Evento> eventos;

    public Buzon(int capacidad) {
        this.capacidad = capacidad;
        this.eventos = new LinkedList<>();
    }

    public synchronized void depositar(Evento evento) {
        while (estaLleno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        eventos.add(evento);
        notifyAll();
    }

    public synchronized Evento retirar() {
        while (estaVacio()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Evento evento = eventos.poll();
        notifyAll();
        return evento;
    }

    public synchronized Evento retirarSinEspera() {
        Evento evento = eventos.poll();

        if (evento != null) {
            notifyAll();
        }

        return evento;
    }

    public synchronized boolean estaVacio() {
        return eventos.isEmpty();
    }

    public synchronized boolean estaLleno() {
        if (capacidad <= 0) {
            return false;
        }
        return eventos.size() >= capacidad;
    }
}