package com.epis.rmi_spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Punto de entrada del servidor.
 * Inicializa el contexto de Spring y deja el servicio RMI en ejecución.
 */
public class ServerApplication {

    // Logger para mostrar información del servidor
    private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        // Crea el contexto de Spring con la configuración de AppConfig
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        // Informa que el servidor está funcionando
        log.info("RMI Server is up and running on port {}", 1099);

        // Hook para cerrar el servidor de forma segura al terminar
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down RMI Server...");
            context.close();
        }));
    }
}
