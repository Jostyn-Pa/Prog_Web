package com.prog.web;

import com.prog.web.repositories.UserRepository;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.ws.rs.SeBootstrap;
import java.net.URI;

public class RestPostMain {

    public static void main(String[] args) throws Exception {
        /*
        // 1. Iniciar el contenedor CDI (Necesario para que funcionen los @Inject)
        var cdiContainer = SeContainerInitializer.newInstance().initialize();

        // (Opcional) Probar que la base de datos funciona antes de arrancar el servidor web
        var repo = cdiContainer.select(UserRepository.class).get();
        System.out.println("Usuarios en DB al arrancar:");
        repo.findAll().forEach(System.out::println);

        // 2. Configurar el servidor web JAX-RS
        SeBootstrap.Configuration config = SeBootstrap.Configuration.builder()
                .host("0.0.0.0")
                .port(8080)
                .protocol("http")
                .build();

        // 3. Arrancar el servidor pasando tu configuración MyApplication
        SeBootstrap.start(MyApplication.class, config)
                .thenAccept(instance -> {
                    URI uri = instance.configuration().baseUri();
                    System.out.println("✅ Servidor REST iniciado correctamente en: " + uri);
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error al iniciar el servidor: " + ex.getMessage());
                    return null;
                });

        // 4. Mantener el hilo principal vivo para que el servidor no se apague
        Thread.currentThread().join();*/

        // 1. Configurar el servidor web JAX-RS
        SeBootstrap.Configuration config = SeBootstrap.Configuration.builder()
                .host("0.0.0.0")
                .port(8080)
                .protocol("http")
                .build();

        // 2. Arrancar el servidor pasando tu configuración MyApplication
        // RESTEasy levantará CDI de forma automática e integrada aquí
        SeBootstrap.start(MyApplication.class, config)
                .thenAccept(instance -> {
                    URI uri = instance.configuration().baseUri();
                    System.out.println("\n==================================================");
                    System.out.println("✅ Servidor REST iniciado correctamente en: " + uri);
                    System.out.println("==================================================\n");
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error al iniciar el servidor: " + ex.getMessage());
                    ex.printStackTrace();
                    return null;
                });

        // 3. Mantener el hilo principal vivo
        Thread.currentThread().join();
            }
        }