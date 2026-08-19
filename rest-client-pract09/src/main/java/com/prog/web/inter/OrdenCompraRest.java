package com.prog.web.inter;

import com.prog.web.dto.OrdenCompraDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OrdenCompraRest {

    @GET
    List<OrdenCompraDto>findAll();

    @GET
    @Path("/{id}")
    OrdenCompraDto findById(@PathParam("id") Integer id);


    @POST
    Response create(OrdenCompraDto ordenCompra);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, OrdenCompraDto ordenCompra);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id")Integer id);

    @GET
    @Path("/cliente/{id}")
    Response findClienteById(@PathParam("id") Integer id);

}
