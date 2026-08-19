package com.prog.web.rest;

import com.prog.web.db.Autor;
import com.prog.web.servicios.inter.AutorServicioInter;
import com.prog.web.servicios.inter.LibroServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AutorRest {

    private final AutorServicioInter autorServicioInter;

    private final LibroServicioInter libroServicioInter;

    @Inject
    public AutorRest(AutorServicioInter autorServicioInter, LibroServicioInter libroServicioInter) {
        this.autorServicioInter = autorServicioInter;
        this.libroServicioInter = libroServicioInter;
    }

    @GET
    public Response findAll() {
        return Response.ok(autorServicioInter.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return Response.ok(autorServicioInter.findById(id)).build();
    }

    @POST
    public Response save(Autor autor) {
        Autor a1 =  autorServicioInter.save(autor);
        return Response.ok().entity(a1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Autor autor) {
        return autorServicioInter.update(id, autor)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return autorServicioInter.delete(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    //GET /api/autores/{id}/libros
    @GET
    @Path("/{id}/libros")
    public Response findByAutorId(@PathParam("id")Integer id) {
        return autorServicioInter.findById(id)
                .map(autores -> {
                    var obj = libroServicioInter.findByAutorId(id);
                    return Response.ok(obj).build();
                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
