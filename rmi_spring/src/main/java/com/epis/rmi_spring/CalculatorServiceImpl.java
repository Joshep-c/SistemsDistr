package com.epis.rmi_spring;

import org.springframework.stereotype.Service;
import java.rmi.RemoteException;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }
}