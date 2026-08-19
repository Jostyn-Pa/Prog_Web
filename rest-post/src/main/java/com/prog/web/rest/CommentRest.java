package com.prog.web.rest;

import com.prog.web.db.Comment;
import com.prog.web.servicios.interfaces.CommentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/comments")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentRest {

    private final CommentService commentService;

    @Inject
    public CommentRest(CommentService commentService) {
        this.commentService = commentService;
    }

    @GET
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return commentService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Comment comment) {
        commentService.save(comment);
        return Response.status(Response.Status.CREATED).entity(comment).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Comment comment) {
        return commentService.findById(id).map(existing -> {
            comment.setId(id);
            commentService.save(comment);
            return Response.ok(comment).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return commentService.findById(id).map(existing -> {
            commentService.delete(id);
            return Response.noContent().build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}