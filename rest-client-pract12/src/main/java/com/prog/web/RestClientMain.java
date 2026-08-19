package com.prog.web;

import com.prog.web.dto.AuthorDto;
import com.prog.web.dto.LibroDto;
import com.prog.web.inter.LibroRest;
import com.prog.web.inter.ResenaExternaRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class RestClientMain {
    static void main() {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        ResteasyWebTarget target = client.target("http://localhost:8080");

        LibroRest proxy = target.proxy(LibroRest.class);
        ResenaExternaRest proxy2 = target.proxy(ResenaExternaRest.class);

        LibroDto libro1 = LibroDto.builder()
                .titulo("Paula")
                .genero("Biografía")
                .authorDto(AuthorDto.builder().id(3).build())
                .build();

        try (Response res = proxy.create(libro1)) {
            System.out.println(res.getStatus());
        }

        System.out.println("GET");
        List<LibroDto> query = proxy.filteredQuery("Realismo Mágico", "Colombiana");
        query.forEach(System.out::println);

        System.out.println("CONSUMIR");
        try (Response res = proxy2.resenas()){
            String comentarios = res.readEntity(String.class);
            System.out.println(comentarios);
        }

        client.close();
    }
}
