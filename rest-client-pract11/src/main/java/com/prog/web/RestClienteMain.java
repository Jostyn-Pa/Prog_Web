package com.prog.web;

import com.prog.web.dto.CursoDto;
import com.prog.web.inter.CursoRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class RestClienteMain {
    static void main() {

        //RestEasyClient con ClientBuilder
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        //Target
        ResteasyWebTarget target = client.target("http://localhost:8080");

        //Proxy
        CursoRest proxy = target.proxy(CursoRest.class);

        System.out.println("***POST***");
        CursoDto curso1 = CursoDto.builder()
                .titulo("Java Backend Pro")
                .categoria("Programación")
                .precio(45.5)
                .build();

        try (Response response = proxy.create(curso1)) {
            System.out.println(response.getStatus());
        }

        System.out.println("GET");
        List<CursoDto> cursos = proxy.searchQuery("Programación",50.0);
        cursos.forEach(System.out::println);
    }
}
