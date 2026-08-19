package com.prog.web.main;

import com.prog.web.inter.VideoGameRestClient;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class ClienteRestMain {

    // Corregida la firma del main
    public static void main(String[] args) {

        // 1. Configuración del Cliente
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target("http://localhost:8080"); // Corregido el puerto

        // 2. Creación del Proxy Mágico
        VideoGameRestClient proxy = target.proxy(VideoGameRestClient.class);

        System.out.println("--- 1. POST (Creando Elden Ring) ---");
        VideoGameDto eldenRing = VideoGameDto.builder()
                .title("Elden Ring")
                .platform("PS5")
                .price(59.99)
                .isMultiplayer(true)
                .releaseYear(2022)
                .build();

        try (Response response = proxy.create(eldenRing)) {
            System.out.println("Status de creación: " + response.getStatus()); // Debería dar 201
        }

        System.out.println("\n--- 2. GET (Ofertas menores a $30) ---");
        // ¡Mira qué limpio queda! No hay que lidiar con JSON, solo llamas al método.
        List<VideoGameDto> ofertas = proxy.findDeals(90.0);

        for (VideoGameDto juego : ofertas) {
            System.out.println("- " + juego.getTitle() + " ($" + juego.getPrice() + ")");
        }

        List<VideoGameDto> v1 = proxy.findAll();
        v1.forEach(System.out::println);

        // Cierra el cliente al terminar
        client.close();
    }
}