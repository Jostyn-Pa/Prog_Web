package com.prog.web.rest;

import com.prog.web.db.VideoGame;
import com.prog.web.servicios.inter.VideoGameService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/games")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@RequestScoped

public class VideoGamesRest {

    private final VideoGameService  videoGameService;

    @Inject
    public VideoGamesRest(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        var obj = videoGameService.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return videoGameService.findById(id).map(Response::ok).orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(VideoGame videoGame) {
        var obj = videoGameService.save(videoGame);
        return obj != null
                ? Response.status(Response.Status.CREATED).entity(obj).build()
                : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, VideoGame videoGame) {
        return videoGameService.update(id, videoGame).map(up -> Response.ok(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return videoGameService.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/platform/{platform}")
    public Response findByPlatform(@PathParam("platform") String platform) {
        var obj = videoGameService.findByPlatform(platform);
        return Response.ok(obj).build();
    }

    @GET
    @Path("/deals") // 4. Sin llaves, es una ruta estática
    public Response findByPriceLessThanEquals(@QueryParam("maxPrice") Double maxPrice) {
        // 5. Validamos si está vacío y asignamos el 20.0 por defecto
        if (maxPrice == null) {
            maxPrice = 20.0;
        }
        var obj = videoGameService.findByPriceLessThanEquals(maxPrice);
        return Response.ok(obj).build();
    }
}
