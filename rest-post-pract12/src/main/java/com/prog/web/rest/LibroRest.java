package com.prog.web.rest;

import com.prog.web.db.Libro;
import com.prog.web.servicios.inter.LibroServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/libros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public Response findById(@PathParam("id") Integer id) {
        return libroServicioInter.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Libro libro) {
        // 2. Usamos try-catch para atrapar las validaciones que pusimos en el Servicio
        try {
            Libro obj = libroServicioInter.save(libro);
            return Response.status(Response.Status.CREATED).entity(obj).build();
        } catch (IllegalArgumentException e) {
            // Si el servicio detecta que falta el título o el autor, devolvemos 400
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{id}") // 3. Con la barrita inicial
    public Response update(@PathParam("id") Integer id, Libro libro){
        try {
            return libroServicioInter.update(id, libro).map(
                    up -> Response.ok().entity(up).build()
            ).orElse(Response.status(Response.Status.NOT_FOUND).build());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}") // 3. Con la barrita inicial
    public Response delete(@PathParam("id") Integer id) {
        return libroServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    public Response filteredQuery(@QueryParam("genero")String genero, @QueryParam("nacionalidad")String nacionalidad) {
        var obj = libroServicioInter.queryFilter(genero, nacionalidad);
        return Response.ok(obj).build();
    }
}
