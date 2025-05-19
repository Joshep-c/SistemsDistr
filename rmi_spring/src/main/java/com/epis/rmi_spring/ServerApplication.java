package com.epis.rmi_spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApplication {
    private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("RMI Server is up and running on port {}", 1099);

        // Keep the application running until shutdown signal
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down RMI Server...");
            context.close();
        }));
    }
}