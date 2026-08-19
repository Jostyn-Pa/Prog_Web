package com.prog.web.inter;

import com.prog.web.dto.CursoDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/cursos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public interface CursoRest {
    @GET
    List<CursoDto> findAll();


    @GET
    @Path("/{id}")
    CursoDto findById(@PathParam("id")Integer id);

    @POST
    Response create(CursoDto curso);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id")Integer id, CursoDto curso);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id")Integer id);

    @GET
    @Path("/search")
    List<CursoDto> searchQuery(@QueryParam("categoria")String categoria, @QueryParam("precioMaximo")Double precioMaximo);
}
