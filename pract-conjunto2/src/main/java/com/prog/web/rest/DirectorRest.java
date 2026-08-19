package com.prog.web.rest;

import com.prog.web.db.Director;
import com.prog.web.servicios.inter.DirectorServicioInter;
import com.prog.web.servicios.inter.PeliculaServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/directores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class DirectorRest {

    private final DirectorServicioInter directorServicioInter;
    private final PeliculaServicioInter peliculaServicioInter;

    @Inject
    public DirectorRest(DirectorServicioInter directorServicioInter, PeliculaServicioInter peliculaServicioInter) {
        this.directorServicioInter = directorServicioInter;
        this.peliculaServicioInter = peliculaServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = directorServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        return directorServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Director director) {
        Director d1 = directorServicioInter.save(director);
        return Response.status(Response.Status.CREATED).entity(d1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Director director) {
        return directorServicioInter.update(id, director)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return directorServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/peliculas")
    public Response getDirectoresPeliculas(@PathParam("id")Integer id) {
        return directorServicioInter.findById(id)
                .map(x -> {
                    var peliculas = peliculaServicioInter.findDirectorById(id);
                    return Response.ok(peliculas).build();
                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
