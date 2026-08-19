package com.prog.web.rest;

import com.prog.web.db.Medico;
import com.prog.web.db.Paciente;
import com.prog.web.servicios.inter.MedicoServicioInter;
import com.prog.web.servicios.inter.PacienteServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/medicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MedicoRest {

    private final MedicoServicioInter medicoServicioInter;
    private final PacienteServicioInter pacienteServicioInter;

    @Inject
    public MedicoRest(MedicoServicioInter medicoServicioInter, PacienteServicioInter pacienteServicioInter) {
        this.medicoServicioInter = medicoServicioInter;
        this.pacienteServicioInter = pacienteServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = medicoServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return medicoServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Medico medico) {
        Medico p1 = medicoServicioInter.save(medico);
        return Response.status(Response.Status.CREATED).entity(p1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Medico medico) {
        return medicoServicioInter.update(id, medico)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return medicoServicioInter.delete(id)
                ?  Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/pacientes")
    public Response findByMedicoId(@PathParam("id")Integer id) {
        var obj = pacienteServicioInter.findByMedicoId(id);
        return Response.ok(obj).build();
    }

}
