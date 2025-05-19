package com.epis.rmi_spring.client;

import com.epis.rmi_spring.CalculatorService;

import java.rmi.Naming;

/**
 * Cliente que se conecta al servicio RMI y realiza llamadas remotas.
 */
public class ClientApplication {
    public static void main(String[] args) throws Exception {
        // URL del servicio RMI en el servidor
        String url = "rmi://localhost:1099/CalculatorService";

        // Realiza la búsqueda del objeto remoto usando Naming.lookup
        CalculatorService service = (CalculatorService) Naming.lookup(url);

        // Llamadas a los métodos remotos y muestra de resultados
        System.out.println("Resultado add(5, 3): " + service.add(5, 3));
        System.out.println("Resultado subtract(10, 4): " + service.subtract(10, 4));
    }
}
