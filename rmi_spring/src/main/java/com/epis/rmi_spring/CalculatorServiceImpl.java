package com.epis.rmi_spring;

import org.springframework.stereotype.Service;
import java.rmi.RemoteException;

/**
 * Implementación del servicio de cálculo.
 * @Service indica a Spring que registre este componente como un bean.
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public int add(int a, int b) throws RemoteException {
        // Devuelve la suma de a + b
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        // Devuelve la resta de a - b
        return a - b;
    }
}
