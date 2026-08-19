package com.prog.web.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;

@Path("/chao")
public class ChaoRest {

    @GET
    public String adios() {
        return "Adiós, mundo: " + LocalDateTime.now();
    }
}
