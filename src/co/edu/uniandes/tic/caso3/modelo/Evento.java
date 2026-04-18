package co.edu.uniandes.tic.caso3.modelo;

public class Evento {
    private String id;
    private int idSensor;
    private int secuencial;
    private int tipo;
    private boolean fin;

    public Evento(String id, int idSensor, int secuencial, int tipo, boolean fin) {
        this.id = id;
        this.idSensor = idSensor;
        this.secuencial = secuencial;
        this.tipo = tipo;
        this.fin = fin;
    }

    public static Evento crearEventoFin(String id) {
        return new Evento(id, -1, -1, -1, true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public int getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(int secuencial) {
        this.secuencial = secuencial;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean esFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", idSensor=" + idSensor +
                ", secuencial=" + secuencial +
                ", tipo=" + tipo +
                ", fin=" + fin +
                '}';
    }
}