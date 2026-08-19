package com.prog.web;

import com.prog.web.dto.AutorDto;
import com.prog.web.inter.AutorRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestClientMain {
    public static void main(String[] args) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        ResteasyWebTarget target = client.target("http://localhost:8080");

        AutorRest proxy = target.proxy(AutorRest.class);

        AutorDto a1 = AutorDto.builder()
                .nombre("asd")
                .pais("asd")
                .build();

        try (Response res = proxy.save(a1)) {
            System.out.println(res.getStatus());
        } catch (Exception e) {
            Response res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
