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
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ExternoRest {
    @GET
    @Path("/libros")
    public Response obtenerLibros() {
        try (ResteasyClient client = (ResteasyClient) ClientBuilder.newClient()) {
            Response res = client.target("https://openlibrary.org/search.json?q=java")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            String json = res.readEntity(String.class);
            return Response.ok(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
