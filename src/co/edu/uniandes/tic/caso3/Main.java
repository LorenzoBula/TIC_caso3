package co.edu.uniandes.tic.caso3;

import co.edu.uniandes.tic.caso3.hilos.*;
import co.edu.uniandes.tic.caso3.monitores.Buzon;

public class Main {
    public static void main(String[] args) {
        int cantidadSensores = 3;
        int ni = 10;
        int nc = 2;
        int ns = 3;
        int tam1 = 15;
        int tam2 = 10;

        int totalEventosEsperados = 0;

        for (int i = 1; i <= cantidadSensores; i++) {
            totalEventosEsperados += (ni * i);
        }

        System.out.println("Simulador esta arrancando con: " + totalEventosEsperados + "eventos en total.");

        Buzon buzonEntrada = new Buzon(-1);
        Buzon buzonAlertas = new Buzon(-1);
        Buzon buzonClasificacion = new Buzon(tam1);
        Buzon[] buzonesConsolidacion = new Buzon[ns];
        
        for (int i = 0; i < ns; i++) {
            buzonesConsolidacion[i] = new Buzon(tam2);
        }


        for (int i = 0; i < ns; i++) {
            ServidorDeDespliegue servidor = new ServidorDeDespliegue(i + 1, buzonesConsolidacion[i]);
            servidor.start();
        }

        // Clasificadores
        for (int i = 0; i < nc; i++) {
            Clasificador clasificador = new Clasificador(i + 1, buzonClasificacion, buzonesConsolidacion);
            clasificador.start();
        }

    
        Admin admin = new Admin(buzonAlertas, buzonClasificacion, nc);
        admin.start();

        
        Broker broker = new Broker(buzonEntrada, buzonAlertas, buzonClasificacion, totalEventosEsperados);
        broker.start();

        
        for (int i = 1; i <= cantidadSensores; i++) {
            int cantidadEventosSensor = ni * i;
            SensorIoT sensor = new SensorIoT(i, cantidadEventosSensor, ns, buzonEntrada);
            sensor.start();
        }
        
        System.out.println("Todos los hilos han sido lanzados.");
    }
}
    

