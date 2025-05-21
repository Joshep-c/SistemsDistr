package com.example.demo.client;

import com.example.demo.grpc.GreetingServiceGrpc;
import com.example.demo.grpc.HelloRequest;
import com.example.demo.grpc.HelloResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class GrpcClientService {

    private ManagedChannel channel;
    private GreetingServiceGrpc.GreetingServiceBlockingStub blockingStub;
    private GreetingServiceGrpc.GreetingServiceStub asyncStub;    @PostConstruct
    private void init() {
        // Crear el canal de comunicación
        channel = ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext() // No usar SSL/TLS para este ejemplo
                .build();
        
        // Crear los stubs para la comunicación
        blockingStub = GreetingServiceGrpc.newBlockingStub(channel);
        asyncStub = GreetingServiceGrpc.newStub(channel);
        
        log.info("Cliente gRPC inicializado");
    }
    
    public String sendSimpleRequest(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        
        HelloResponse response = blockingStub.sayHello(request);
        return response.getGreeting();
    }
    
    public void testServerStreaming(String name) throws InterruptedException {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        
        CountDownLatch latch = new CountDownLatch(1);
        
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse response) {
                log.info("Respuesta recibida: {}", response.getGreeting());
            }
            
            @Override
            public void onError(Throwable t) {
                log.error("Error en streaming: {}", t.getMessage());
                latch.countDown();
            }
            
            @Override
            public void onCompleted() {
                log.info("Streaming completado");
                latch.countDown();
            }
        };
        
        asyncStub.sayHelloServerStream(request, responseObserver);
        
        // Esperar a que termine el streaming
        latch.await(10, TimeUnit.SECONDS);
    }
    
    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error("Error cerrando el canal gRPC", e);
            }
        }
    }
}
