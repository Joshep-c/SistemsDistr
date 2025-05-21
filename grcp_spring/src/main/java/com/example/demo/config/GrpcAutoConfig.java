package com.example.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import net.devh.boot.grpc.client.autoconfigure.GrpcClientHealthAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration;

@Configuration
@EnableAutoConfiguration(exclude = {
        GrpcClientHealthAutoConfiguration.class,
        GrpcHealthServiceAutoConfiguration.class
})
public class GrpcAutoConfig {
    
}
