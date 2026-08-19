package com.prog.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.SeBootstrap;

import java.net.URI;

public class RestPostMain {
    static void main() throws Exception{
        SeBootstrap.Configuration configuration = SeBootstrap.Configuration.builder()
                .port(8080)
                .host("localhost")
                .build();
        SeBootstrap.start(MyApplication.class, configuration).thenAccept(result -> {
            System.out.println(result);
            URI uri = result.configuration().baseUri();
            System.out.println("Server started at: " + uri);
        });
        Thread.currentThread().join();
    }
}
