package com.prog.web.rest;

import com.prog.web.db.Producto;
import com.prog.web.servicios.inter.ProductoServiceInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/productos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RequestScoped
public class ProductoRest {

    @Inject
    private ProductoServiceInter productoServiceInter;

    @GET
    public Response query(@QueryParam("nombre") String nombre,
                          @QueryParam("minPrecio") Double minPrecio,
                          @QueryParam("maxPrecio") Double maxPrecio) {

        if (minPrecio != null && minPrecio < 0 || maxPrecio != null && maxPrecio < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativos");
        }

        var obj = productoServiceInter.query(nombre, minPrecio, maxPrecio);
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return productoServiceInter.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        Producto p = productoServiceInter.save(producto);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        return productoServiceInter.update(id, producto)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return productoServiceInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}