package com.prog.web.rest;

import com.prog.web.db.WorkoutLog;
import com.prog.web.servicios.inter.WorkoutLogServicio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workouts")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class WorkoutLogRest {

    private final WorkoutLogServicio workoutLogServicio;

    @Inject
    public WorkoutLogRest(WorkoutLogServicio workoutLogServicio) {
        this.workoutLogServicio = workoutLogServicio;
    }

    @GET
    public Response findAll() {
        var obj = workoutLogServicio.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return workoutLogServicio.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save (WorkoutLog workoutLog) {
        WorkoutLog saved = workoutLogServicio.save(workoutLog);
        return saved != null
                ? Response.status(Response.Status.CREATED).entity(saved).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, WorkoutLog workoutLog) {
        return workoutLogServicio.update(id, workoutLog).map(up ->
                Response.ok().entity(up).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return workoutLogServicio.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/split/{type}")
    public Response findBySplitType(@PathParam("type")String type) {

        List<WorkoutLog> workoutLogs = workoutLogServicio.findBySplitType(type);
        return Response.ok(workoutLogs).build();
    }

    @GET
    @Path("/heavy")
    public Response findByWeightGreaterThanEqual(@QueryParam("minWeight") Double weight) { // Ajustado a minWeight
        // Regla de negocio: Valor por defecto
        if (weight == null) {
            weight = 60.0;
        }
        List<WorkoutLog> workoutLogs = workoutLogServicio.findByWeightGreaterThanEquals(weight);
        return Response.ok(workoutLogs).build();
    }
}
