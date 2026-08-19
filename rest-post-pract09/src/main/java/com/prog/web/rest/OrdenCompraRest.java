package com.prog.web.rest;

import com.prog.web.db.OrdenCompra;
import com.prog.web.services.inter.OrdenCompraServiceInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orden_compra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OrdenCompraRest {

    private final OrdenCompraServiceInter ordenCompraServiceInter;

    @Inject
    public OrdenCompraRest(OrdenCompraServiceInter ordenCompraServiceInter) {
        this.ordenCompraServiceInter = ordenCompraServiceInter;
    }

    @GET
    public Response findAll() {
        var obj = ordenCompraServiceInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return ordenCompraServiceInter.findById(id).map(Response::ok).orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(OrdenCompra ordenCompra) {
        OrdenCompra saved = ordenCompraServiceInter.save(ordenCompra);

        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, OrdenCompra ordenCompra) {
        return ordenCompraServiceInter.update(id, ordenCompra).map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return ordenCompraServiceInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/cliente/{id}")
    public Response findClienteById(@PathParam("id") Integer id) {
        var obj = ordenCompraServiceInter.findByClienteId(id);

        return Response.ok(obj).build();
    }

}
