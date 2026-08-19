package com.prog.web.rest;

import com.prog.web.db.Libro;
import com.prog.web.servicios.inter.LibroServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/libros")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@RequestScoped
public class LibroRest {

    private final LibroServicioInter libroServicioInter;

    @Inject
    public LibroRest(LibroServicioInter libroServicioInter) {
        this.libroServicioInter = libroServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = libroServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        return libroServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Libro libro) {
        Libro l1 = libroServicioInter.save(libro);
        return Response.status(Response.Status.CREATED).entity(l1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Libro libro) {
        return libroServicioInter.update(id, libro)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return libroServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response findByTituloAndMinPrecioAndMaxPrecioAndCategoria(
            @QueryParam("titulo") String titulo,
            @QueryParam("minPrecio") Double minPrecio,
            @QueryParam("maxPrecio") Double maxPrecio,
            @QueryParam("categoria") String categoria
    ) {
        var obj = libroServicioInter.findByTituloAndMinPrecioAndMaxPrecioAndCategoria(
                titulo,minPrecio,maxPrecio,categoria
        );
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findByAutorId(@PathParam("id")Integer id) {
        var obj = libroServicioInter.findByAutorId(id);
        return Response.ok(obj).build();
    }
}
