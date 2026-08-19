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
public class ExternoRest {
    @GET
    public Response getExterno() {
        try (ResteasyClient client = (ResteasyClient) ClientBuilder.newClient()){
            Response response = client.target("https://api.tvmaze.com/shows")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            String json = response.readEntity(String.class);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
