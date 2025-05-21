package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import com.example.demo.service.GreetingServiceImpl;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Configuration
@Slf4j
public class GrpcServerConfig {

    private Server server;

    @Bean
    public Server grpcServer() throws IOException {
        int port = 9090;
        server = ServerBuilder.forPort(port)
                .addService(new GreetingServiceImpl())
                .build()
                .start();
        
        log.info("Servidor gRPC iniciado en el puerto {}", port);
        
        // Iniciar el servidor en un hilo separado para no bloquear la inicialización de Spring
        new Thread(() -> {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                log.error("Error en el servidor gRPC", e);
            }
        }).start();
        
        return server;
    }
    
    @PreDestroy
    public void stopServer() {
        if (server != null) {
            log.info("Deteniendo servidor gRPC");
            server.shutdown();
        }
    }
}
