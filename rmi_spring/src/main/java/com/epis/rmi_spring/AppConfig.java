package com.epis.rmi_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan("com.epis.rmi_spring")
public class AppConfig {

    @Bean 
    public RmiServiceExporter rmiExporter(CalculatorService calculatorService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("CalculatorService");
        exporter.setServiceInterface(CalculatorService.class);
        exporter.setService(calculatorService);
        exporter.setRegistryPort(1099);
        exporter.setAlwaysCreateRegistry(true);
        return exporter;
    }
}