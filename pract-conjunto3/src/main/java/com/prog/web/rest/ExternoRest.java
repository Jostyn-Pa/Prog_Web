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
    @Path("/dcotores")
    public Response getDoctores() {
        try (ResteasyClient client = (ResteasyClient) ClientBuilder.newClient()) {
            Response response = client.target("https://jsonplaceholder.typicode.com/users")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            String json = response.readEntity(String.class);
            return Response.status(200).entity(json).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
