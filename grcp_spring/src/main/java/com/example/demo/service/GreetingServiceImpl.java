package com.example.demo.service;

import com.example.demo.grpc.GreetingServiceGrpc;
import com.example.demo.grpc.HelloRequest;
import com.example.demo.grpc.HelloResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // Crear la respuesta
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting("¡Hola, " + request.getName() + "!")
                .build();
        
        // Enviar la respuesta al cliente
        responseObserver.onNext(response);
        
        // Completar la llamada RPC
        responseObserver.onCompleted();
    }
    
    @Override
    public void sayHelloServerStream(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // Enviar múltiples respuestas para demostrar el streaming del servidor
        for (int i = 1; i <= 5; i++) {
            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting("¡Hola, " + request.getName() + "! Mensaje #" + i)
                    .build();
            responseObserver.onNext(response);
            
            try {
                Thread.sleep(1000); // Simular procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
    
    @Override
    public StreamObserver<HelloRequest> sayHelloClientStream(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            private final StringBuilder names = new StringBuilder();
            
            @Override
            public void onNext(HelloRequest request) {
                // Acumular nombres que vienen del cliente
                if (names.length() > 0) {
                    names.append(", ");
                }
                names.append(request.getName());
            }
            
            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
            
            @Override
            public void onCompleted() {
                // Cuando el cliente termina de enviar, responder con todos los nombres acumulados
                HelloResponse response = HelloResponse.newBuilder()
                        .setGreeting("¡Hola a todos: " + names.toString() + "!")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
    
    @Override
    public StreamObserver<HelloRequest> sayHelloBiDirectionalStream(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest request) {
                // Por cada solicitud recibida, enviar una respuesta inmediatamente
                HelloResponse response = HelloResponse.newBuilder()
                        .setGreeting("¡Hola, " + request.getName() + "! (bidireccional)")
                        .build();
                responseObserver.onNext(response);
            }
            
            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
            
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
