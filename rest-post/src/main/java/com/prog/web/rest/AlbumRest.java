package com.prog.web.rest;

import com.prog.web.db.Album;
import com.prog.web.servicios.interfaces.AlbumService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/albums")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumRest {

    private final AlbumService albumService;

    @Inject
    public AlbumRest(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GET
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return albumService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Album album) {
        albumService.save(album);
        return Response.status(Response.Status.CREATED).entity(album).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Album album) {
        return albumService.findById(id).map(existing -> {
            album.setId(id);
            albumService.save(album);
            return Response.ok(album).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return albumService.findById(id).map(existing -> {
            albumService.delete(id);
            return Response.noContent().build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}