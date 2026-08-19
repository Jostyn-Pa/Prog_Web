package com.prog.web.inter;

import com.prog.web.dto.LibroDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/libros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface LibroRest {

    @GET
    List<LibroDto> findAll();

    @GET
    @Path("/{id}")
    LibroDto findById(@PathParam("id") Integer id);

    @POST
    Response create(LibroDto libroDto);

    @PUT
    @Path("/{id}") // 3. Con la barrita inicial
    Response update(@PathParam("id") Integer id, LibroDto libroDto);

    @DELETE
    @Path("/{id}") // 3. Con la barrita inicial
    Response delete(@PathParam("id") Integer id);

    @GET
    @Path("/search")
    List<LibroDto> filteredQuery(@QueryParam("genero")String genero, @QueryParam("nacionalidad")String nacionalidad);
}
