package com.prog.web.rest;

import com.prog.web.db.Producto;
import com.prog.web.servicio.inter.ProductoServicioInter;
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

    private final ProductoServicioInter productoServicioInter;

    @Inject
    public ProductoRest(ProductoServicioInter productoServicioInter) {
        this.productoServicioInter = productoServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = productoServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return productoServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
    public Response findByQuery(
            @QueryParam("nombre") String nombre,
            @QueryParam("minPrecio")Double minPrecio,
            @QueryParam("maxPrecio")Double maxPrecio
    ) {
        if (minPrecio != null && minPrecio < 0 || maxPrecio != null && maxPrecio < 0) {
            throw new IllegalArgumentException("Los precios no pueden ser negativos");
        }

        var obj = productoServicioInter.findByNombreAndminPrecioAndMaxPrecio(
                nombre,
                minPrecio,
                maxPrecio
        );
        return Response.ok(obj).build();
    }

    @POST
    public Response create(Producto producto) {
        Producto p1 = productoServicioInter.save(producto);
        return Response.status(Response.Status.CREATED).entity(p1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id,Producto producto) {
        return productoServicioInter.update(id, producto).map(
                up -> Response.ok().entity(up).build()
        ).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return productoServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
