package com.prog.web;

import com.prog.web.inter.OrdenCompraRest;
import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RestClientMain {
    static void main() {
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        ResteasyWebTarget target = client.target("http://localhost:8080");

        OrdenCompraRest proxy = target.proxy(OrdenCompraRest.class);
    }
}
