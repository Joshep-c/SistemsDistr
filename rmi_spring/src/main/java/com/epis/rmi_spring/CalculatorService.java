package com.epis.rmi_spring;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota para RMI.
 * Extiende Remote para que pueda ser usada por RMI.
 */
public interface CalculatorService extends Remote {

    /**
     * Método remoto para sumar dos números enteros.
     * @throws RemoteException obligatorio en interfaces RMI
     */
    int add(int a, int b) throws RemoteException;

    /**
     * Método remoto para restar dos números enteros.
     */
    int subtract(int a, int b) throws RemoteException;
}
