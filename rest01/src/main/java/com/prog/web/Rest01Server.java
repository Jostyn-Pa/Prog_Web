package com.prog.web;

import jakarta.ws.rs.SeBootstrap;

import java.net.URI;

public class Rest01Server {
    static void main() throws InterruptedException {
        SeBootstrap.Configuration conf = SeBootstrap.Configuration.builder()
                //Aqui se tiene que poner una ip dinamica y la forma es esta
                .host("0.0.0.0")
                .port(8080)
                .protocol("http")
                .build();

        SeBootstrap.start(MyApplication.class, conf)
                .thenAccept(instance -> {
                    System.out.println(instance);
                    URI uri = instance.configuration().baseUri();
                    System.out.println("Servidor Iniciado " + uri);
                });
        Thread.currentThread().join();
    }
}
