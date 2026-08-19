package com.prog.web.cliente;

import com.prog.web.cliente.dto.MedicoDto;
import com.prog.web.cliente.inter.MedicoRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ClienteRestMain {
    public static void main(String[] args) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        ResteasyWebTarget target = client.target("http://localhost:8080/api");

        MedicoRest proxy = target.proxy(MedicoRest.class);

        MedicoDto m1 = MedicoDto.builder()
                .nombre("asd")
                .especialidad("asd")
                .build();

        try (Response res = proxy.create(m1)) {
            System.out.println(res.getStatus());
        }

        client.close();
    }
}
