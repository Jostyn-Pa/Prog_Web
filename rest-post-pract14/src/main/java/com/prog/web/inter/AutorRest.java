package com.prog.web.inter;

import com.prog.web.db.Autor;
import com.prog.web.dto.AutorDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AutorRest {

    @GET
    List<AutorDto> findAll();

    @GET
    @Path("/{id}")
    AutorDto findById(@PathParam("id") Integer id);

    @POST
    Response save(AutorDto autor);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, AutorDto autor);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

}
