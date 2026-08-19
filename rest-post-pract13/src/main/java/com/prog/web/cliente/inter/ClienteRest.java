package com.prog.web.cliente.inter;

import com.prog.web.cliente.dto.ClienteDto;
import com.prog.web.db.Cliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/clientes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ClienteRest {

    @GET
    List<ClienteDto> findAll();

    @GET
    @Path("/{id}")
    ClienteDto findById(@PathParam("id") Integer id);

    @POST
    Response create(ClienteDto clienteDto);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, ClienteDto cliente);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

    @GET
    @Path("/{id}/ordenes")
    List<ClienteDto> ordenes(@PathParam("id")Integer id);

}
