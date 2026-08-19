package com.prog.web.rest;

import com.prog.web.db.Pelicula;
import com.prog.web.servicios.inter.PeliculaServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/peliculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PeliculaRest {

    private final PeliculaServicioInter peliculaServicioInter;

    @Inject
    public PeliculaRest(PeliculaServicioInter peliculaServicioInter) {
        this.peliculaServicioInter = peliculaServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = peliculaServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        return peliculaServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Pelicula pelicula){
        Pelicula p1 = peliculaServicioInter.save(pelicula);
        return Response.status(Response.Status.CREATED).entity(p1).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id")Integer id, Pelicula pelicula){
        return peliculaServicioInter.update(id, pelicula)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id")Integer id){
        return peliculaServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    public Response filtroOpcionales(@QueryParam("titulo")String titulo,
                                     @QueryParam("genero")String genero,
                                     @QueryParam("minPrecio")Double minPrecio,
                                     @QueryParam("maxPrecio")Double maxPrecio,
                                     @QueryParam("anio")Integer anio){
        var obj = peliculaServicioInter.filtrosOpcionales(titulo, genero, minPrecio, maxPrecio, anio);
        return Response.ok(obj).build();
    }

    @GET
    @Path("/rango")
    public Response filtrosObligatorios(@QueryParam("minPrecio")Double minPrecio,@QueryParam("maxPrecio")Double maxPrecio){
        if(minPrecio==null || maxPrecio==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe enviar los precios").build();
        }
        var obj = peliculaServicioInter.filtrosObligatorios(minPrecio,maxPrecio);
        return Response.ok(obj).build();
    }
}
