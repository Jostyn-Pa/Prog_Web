package com.prog.web;

import com.prog.web.cliente.dto.ClienteDto;
import com.prog.web.cliente.inter.ClienteRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestClientMain {
    public static void main(String[] args) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        //Target con ResteasyWebTarget
        ResteasyWebTarget target = client.target("http://localhost:8080");

        //Proxy con un REST
        ClienteRest proxy = target.proxy(ClienteRest.class);

        ClienteDto dto = ClienteDto.builder()
                .nombre("asd")
                .apellido("asd")
                .direccion("adsadadsa")
                .build();

        try (Response res = proxy.create(dto)) {
            System.out.println(res.getStatus());
        }
    }
}
