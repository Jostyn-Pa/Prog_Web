package com.prog.web.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

@Path("/externo")
@Produces({MediaType.APPLICATION_JSON})
@RequestScoped
public class InstructoresExternoRest {

    @GET
    @Path("/instructores")
    public Response obtenerInstructores() {
        try (ResteasyClient client = (ResteasyClient) ClientBuilder.newClient()) {
            Response respuestaExterna = client.target("https://jsonplaceholder.typicode.com/users")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            String json = respuestaExterna.readEntity(String.class);
            return Response.ok(json).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
