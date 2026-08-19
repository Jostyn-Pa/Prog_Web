package com.prog.web;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.SeBootstrap;

import java.net.URI;

@ApplicationPath("/api")
public class RestPostMain {
    public static void main(String[] args) throws Exception{
        SeBootstrap.Configuration configuration = SeBootstrap.Configuration.builder()
                .host("0.0.0.0")
                .port(8080)
                .protocol("http")
                .build();

        SeBootstrap.start(MyApplication.class, configuration).thenAccept(response -> {
            System.out.println(response);
            URI uri = response.configuration().baseUri();
            System.out.println("Server started at: " + uri);
        });
        Thread.currentThread().join();

    }
}
