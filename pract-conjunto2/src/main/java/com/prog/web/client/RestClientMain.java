package com.prog.web.client;

import com.prog.web.client.inter.DirectorRest;
import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestClientMain {
    public static void main(String[] args) {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        ResteasyWebTarget target = client.target("http://localhost:8080");

        DirectorRest proxy = target.proxy(DirectorRest.class);


        client.close();
    }
}
