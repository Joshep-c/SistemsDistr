package com.epis.rmi_spring.client;

import com.epis.rmi_spring.CalculatorService;

import java.rmi.Naming;

public class ClientApplication {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost:1099/CalculatorService";
        CalculatorService service = (CalculatorService) Naming.lookup(url);

        System.out.println("Resultado add(5, 3): " + service.add(5, 3));
        System.out.println("Resultado subtract(10, 4): " + service.subtract(10, 4));
    }
}
