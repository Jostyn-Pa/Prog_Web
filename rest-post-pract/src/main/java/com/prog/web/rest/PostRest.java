package com.prog.web.rest;

import com.prog.web.db.Post;
import com.prog.web.servicios.interfaces.PostService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/posts")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class PostRest {

    private final PostService postService;

    @Inject
    public PostRest(PostService postService) {
        this.postService = postService;
    }

    @GET
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return postService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(Post post) {
        return postService.save(post) != null
                ? Response.status(Response.Status.CREATED).entity(post).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Post post) {
        return postService.update(id, post)
                .map(updatedPost -> Response.ok(updatedPost).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        if (postService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/search")
    public Response search(@QueryParam("author") String author, @QueryParam("title") String title) {
        // 1. Llamamos a la base de datos UNA SOLA VEZ y guardamos la respuesta
        List<Post> results = postService.searchPosts(author, title);

        // 2. Evaluamos la respuesta guardada
        if (results != null && !results.isEmpty()) {
            return Response.ok(results).build(); // 200 OK con la lista
        } else {
            // 404 si la lista está vacía (no se encontró nada)
            // O podrías devolver simplemente 200 OK con una lista vacía [],
            // dependiendo de lo que espere tu Frontend.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
