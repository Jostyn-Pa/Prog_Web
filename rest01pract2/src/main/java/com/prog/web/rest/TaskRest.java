package com.prog.web.rest;

import com.prog.web.MyApplication;
import com.prog.web.dto.Task;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/tasks")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TaskRest {

    private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>(Map.of(
            1, Task.builder().id(1).title("Task 1").completed(false).build(),
            2, Task.builder().id(2).title("Task 2").completed(true).build()
    ));

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTasks() {
        var obj = tasks.values();

        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response findById(@PathParam("id") Integer id) {
        var obj = tasks.get(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    public Response deleteById(@PathParam("id") Integer id) {
        var obj = tasks.remove(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj).build();
    }
}
