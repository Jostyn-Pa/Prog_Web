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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostRest {

    private final PostService postService;

    @Inject
    public PostRest(PostService postService) {
        this.postService = postService;
    }

    @GET
    public List<Post> findAll() { return postService.findAll(); }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return postService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Post post) {
        postService.save(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Post post) {
        return postService.findById(id).map(existing -> {
            post.setId(id);
            postService.save(post);
            return Response.ok(post).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return postService.findById(id).map(existing -> {
            postService.delete(id);
            return Response.noContent().build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}