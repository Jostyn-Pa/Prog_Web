package com.prog.web.rest;

import com.prog.web.db.OrdenCompra;
import com.prog.web.servicio.inter.OrdenCompraServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OrdenCompraRest {

    private final OrdenCompraServicioInter  ordenCompraServicioInter;

    @Inject
    public OrdenCompraRest(OrdenCompraServicioInter ordenCompraServicioInter) {
        this.ordenCompraServicioInter = ordenCompraServicioInter;
    }

    @GET
    public Response findAll() {
        var obj =  ordenCompraServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return ordenCompraServicioInter.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(OrdenCompra ordenCompra) {

        if(ordenCompra.getId()==null || ordenCompra.getCliente().getId() == null || ordenCompra.getProducto().getId() == null){
            throw new RuntimeException("Debe incluir el id de la orden");
        }

        OrdenCompra o1 = ordenCompraServicioInter.save(ordenCompra);
        return Response.status(Response.Status.CREATED).entity(o1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, OrdenCompra ordenCompra) {
        return ordenCompraServicioInter.update(id, ordenCompra)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return ordenCompraServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public Response findByClienteId(@PathParam("id") Integer id) {
        var obj =  ordenCompraServicioInter.findByClienteId(id);
        return Response.ok(obj).build();
    }


}
