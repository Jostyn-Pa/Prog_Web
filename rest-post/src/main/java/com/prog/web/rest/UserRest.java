package com.prog.web.rest;

import com.prog.web.db.User;
import com.prog.web.repositories.UserRepository; // Corregido el nombre
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    private final UserRepository userRepository; // Corregido el nombre

    @Inject
    public UserRest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    // Retorna 200 OK con la lista (vacía o llena). No debería retornar 404.
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{id}")
    // Retorna 200 OK si existe, o 404 Not Found si no.
    public Response findById(@PathParam("id") Integer id) {
        return userRepository.findOptionalBy(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    // Retorna 201 Created tras guardar el usuario
    public Response save(User user) {
        userRepository.save(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT // Cambiado de POST a PUT para semántica REST correcta
    @Path("/{id}") // Corregido el doble paréntesis
    public Response update(@PathParam("id") Integer id, User user) {
        return userRepository.findOptionalBy(id)
                .map(existingUser -> {
                    user.setId(id); // Asegura que se actualice el ID correcto
                    userRepository.save(user);
                    return Response.ok(user).build(); // 200 OK con el usuario editado
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build()); // 404 si no existía
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return userRepository.findOptionalBy(id)
                .map(user -> {
                    userRepository.remove(user);
                    return Response.noContent().build(); // 204 No Content
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build()); // 404 si no existe
    }
}