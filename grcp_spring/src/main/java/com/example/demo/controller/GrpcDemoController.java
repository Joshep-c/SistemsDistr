package com.example.demo.controller;

import com.example.demo.client.GrpcClientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grpc")
public class GrpcDemoController {

    private final GrpcClientService grpcClientService;
    
    public GrpcDemoController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }
    
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return grpcClientService.sendSimpleRequest(name);
    }
    
    @GetMapping("/stream/{name}")
    public String testServerStreaming(@PathVariable String name) {
        try {
            grpcClientService.testServerStreaming(name);
            return "Streaming iniciado, revisa los logs del servidor";
        } catch (InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }
}
