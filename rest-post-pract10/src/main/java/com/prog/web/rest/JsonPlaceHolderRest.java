package com.prog.web.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

@Path("/externo/posts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class JsonPlaceHolderRest {

    @GET
    public Response obtenerPostsJsonPlace() {
        try (ResteasyClient client = (ResteasyClient) ClientBuilder.newClient()) {

            Response respuestaExterna = client.target("https://jsonplaceholder.typicode.com/posts")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            String jsonResult = respuestaExterna.readEntity(String.class);
            return Response.ok(jsonResult).build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\": \"Error al conectar con la API externa\"}")
                    .build();
        }
    }
}