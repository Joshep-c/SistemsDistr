package com.epis.rmi_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Clase de configuración para el servidor Spring.
 * @Configuration indica que esta clase contiene definiciones de beans.
 * @ComponentScan le dice a Spring dónde buscar las clases anotadas como @Service.
 */
@Configuration
@ComponentScan("com.epis.rmi_spring") // Asegúrate de que el paquete sea el correcto
public class AppConfig {

    /**
     * Bean que exporta el servicio RMI.
     * Recibe la implementación de CalculatorService que Spring detecta.
     */
    @Bean 
    public RmiServiceExporter rmiExporter(CalculatorService calculatorService) {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("CalculatorService"); // Nombre con el que se expone
        exporter.setServiceInterface(CalculatorService.class); // Interfaz expuesta
        exporter.setService(calculatorService); // Implementación del servicio
        exporter.setRegistryPort(1099); // Puerto del RMI registry
        exporter.setAlwaysCreateRegistry(true); // Crea el registro si no existe

        return exporter;
    }
}
