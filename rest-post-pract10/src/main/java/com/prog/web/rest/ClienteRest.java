package com.prog.web.rest;

import com.prog.web.db.Cliente;
import com.prog.web.servicios.inter.ClienteServiceInter;
import com.prog.web.servicios.inter.OrdenCompraServiceInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteRest {

    @Inject
    private ClienteServiceInter clienteServiceInter;

    @Inject
    private OrdenCompraServiceInter ordenCompraServiceInter;

    @GET
    public Response findAll() {
        return Response.ok(clienteServiceInter.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return clienteServiceInter.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @GET
    @Path("/{id}/ordenes")
    public Response getOrdenesDeCliente(@PathParam("id") Integer id) {
        return clienteServiceInter.findById(id)
                .map(cliente -> {
                    var ordenes = ordenCompraServiceInter.findByClienteId(id);
                    return Response.ok(ordenes).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank() ||
                cliente.getDireccion() == null || cliente.getDireccion().isBlank()) {
            throw new IllegalArgumentException("El nombre y direccion son obligatorios");
        }
        Cliente c = clienteServiceInter.save(cliente);
        return Response.status(Response.Status.CREATED).entity(c).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente cliente) {
        return clienteServiceInter.update(id, cliente)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return clienteServiceInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}