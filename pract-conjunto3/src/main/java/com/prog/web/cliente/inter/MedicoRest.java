package com.prog.web.cliente.inter;

import com.prog.web.cliente.dto.MedicoDto;
import com.prog.web.db.Medico;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/medicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MedicoRest {
    @GET
    List<MedicoDto>findAll();

    @GET
    @Path("/{id}")
    MedicoDto findById(@PathParam("id") Integer id);

    @POST
    Response create(MedicoDto medico);
}
