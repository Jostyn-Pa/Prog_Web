package com.prog.web.rest;

import com.prog.web.db.OrdenCompra;
import com.prog.web.exception.ConflictException;
import com.prog.web.servicios.inter.ClienteServiceInter;
import com.prog.web.servicios.inter.OrdenCompraServiceInter;
import com.prog.web.servicios.inter.ProductoServiceInter;
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

    @Inject
    private OrdenCompraServiceInter ordenCompraServiceInter;

    @Inject
    private ClienteServiceInter clienteServiceInter;

    @Inject
    private ProductoServiceInter productoServiceInter;

    @GET
    public Response findAll() {
        return Response.ok(ordenCompraServiceInter.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return ordenCompraServiceInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        boolean fueBorrado = ordenCompraServiceInter.delete(id);

        return fueBorrado
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(OrdenCompra ordenCompra) {
        if (ordenCompra.getPrecio() == null || ordenCompra.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio de la orden es obligatorio y debe ser mayor o igual a 0");
        }

        if (ordenCompra.getClienteId() == null || ordenCompra.getClienteId().getId() == null ||
                ordenCompra.getProductoId() == null || ordenCompra.getProductoId().getId() == null) {
            throw new IllegalArgumentException("Debe incluir el ID del cliente y del producto");
        }

        Integer idDelCliente = ordenCompra.getClienteId().getId();
        if (clienteServiceInter.findById(idDelCliente).isEmpty()) {
            throw new ConflictException("El cliente con ID " + idDelCliente + " no existe.");
        }

        Integer idDelProducto = ordenCompra.getProductoId().getId();
        if (productoServiceInter.findById(idDelProducto).isEmpty()) {
            throw new ConflictException("El producto con ID " + idDelProducto + " no existe.");
        }

        OrdenCompra guardada = ordenCompraServiceInter.save(ordenCompra);
        return Response.status(Response.Status.CREATED).entity(guardada).build();
    }
}