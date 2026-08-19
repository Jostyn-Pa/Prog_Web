package com.prog.web.rest;

import com.prog.web.db.Paciente;
import com.prog.web.servicios.inter.MedicoServicioInter;
import com.prog.web.servicios.inter.PacienteServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pacientes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RequestScoped
public class PacienteRest {

    private final PacienteServicioInter pacienteServicioInter;

    @Inject
    public PacienteRest(PacienteServicioInter pacienteServicioInter) {
        this.pacienteServicioInter = pacienteServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = pacienteServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return pacienteServicioInter.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Paciente paciente) {
        Paciente p1 = pacienteServicioInter.save(paciente);
        return Response.status(Response.Status.CREATED).entity(p1).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Paciente paciente) {
        return pacienteServicioInter.update(id, paciente)
                .map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return pacienteServicioInter.delete(id)
                ?  Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/consultas/search")
    public Response queryOpcionales(
            @QueryParam("nombre")String nombre,
            @QueryParam("enfermedad")String enfermedad,
            @QueryParam("edadMinima")Integer edadMinima,
            @QueryParam("edadMaxima")Integer edadMaxima
    ) {

        var obj = pacienteServicioInter.queryOpcionales(
                nombre, enfermedad, edadMinima, edadMaxima
        );
        return Response.ok(obj).build();
    }

    @GET
    @Path("/consultas/rango")
    public Response queryRango(@QueryParam("minCosto")Double minCosto, @QueryParam("maxCosto")Double maxCosto) {
        var obj = pacienteServicioInter.filtrosObligatorios(minCosto, maxCosto);
        return Response.ok(obj).build();
    }
}
