package com.prog.web.rest;

import com.prog.web.db.Photo;
import com.prog.web.servicios.interfaces.PhotoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/photos")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhotoRest {

    private final PhotoService photoService;

    @Inject
    public PhotoRest(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GET
    public List<Photo> findAll() {
        return photoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return photoService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Photo photo) {
        photoService.save(photo);
        return Response.status(Response.Status.CREATED).entity(photo).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Photo photo) {
        return photoService.findById(id).map(existing -> {
            photo.setId(id);
            photoService.save(photo);
            return Response.ok(photo).build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return photoService.findById(id).map(existing -> {
            photoService.delete(id);
            return Response.noContent().build();
        }).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}