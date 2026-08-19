package com.prog.web.rest;

import com.prog.web.db.Todo;
import com.prog.web.servicios.interfaces.TodoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/todos")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoRest {

    private final TodoService todoService;

    @Inject
    public TodoRest(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    public List<Todo> findAll() {
        System.out.println("➡️ Petición GET recibida en /api/todos");
        return todoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return todoService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Todo todo) {
        todoService.save(todo);
        return Response.status(Response.Status.CREATED).entity(todo).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Todo todo) {
        return todoService.findById(id).map(existing -> {
            todo.setId(id);
            todoService.save(todo);
            return Response.ok(todo).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return todoService.findById(id).map(existing -> {
            todoService.delete(id);
            return Response.noContent().build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}