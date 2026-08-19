package com.prog.web.inter;

import com.prog.web.dto.WorkoutLogDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/workouts")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface WorkoutRestClient {

    @GET
    List<WorkoutLogDto> findAll();

    @GET
    @Path("/{id}") // Corregido de {api} a {id}
    WorkoutLogDto findById(@PathParam("id") Integer id); // Corregido a Integer

    @POST
    Response create(WorkoutLogDto workoutLogDto);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, WorkoutLogDto workoutLogDto); // Corregido a Integer

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id); // Corregido a Integer

    @GET
    @Path("/split/{type}")
    List<WorkoutLogDto> split(@PathParam("type") String type);

    @GET
    @Path("/heavy")
    List<WorkoutLogDto> heavy(@QueryParam("minWeight") Double weight);
}