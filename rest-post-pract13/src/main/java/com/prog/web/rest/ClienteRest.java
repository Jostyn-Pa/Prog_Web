package com.prog.web.rest;

import com.prog.web.db.Cliente;
import com.prog.web.servicio.inter.ClienteServicioInter;
import com.prog.web.servicio.inter.OrdenCompraServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RequestScoped
public class ClienteRest {

    private final ClienteServicioInter clienteServicioInter;

    @Inject
    private OrdenCompraServicioInter ordenCompraServicioInter;

    @Inject
    public ClienteRest(ClienteServicioInter clienteServicioInter) {
        this.clienteServicioInter = clienteServicioInter;
    }

    @GET
    public Response findAll() {
        var lista = clienteServicioInter.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return clienteServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(Cliente cliente) {
        Cliente cliente1 = clienteServicioInter.save(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente cliente) {
        return clienteServicioInter.update(id, cliente)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return clienteServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/ordenes")
    public Response ordenes(@PathParam("id")Integer id) {
        return clienteServicioInter.findById(id)
                .map(c -> {
                    var ordenes = ordenCompraServicioInter.findByClienteId(id);
                    return Response.ok(ordenes).build();
                }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
