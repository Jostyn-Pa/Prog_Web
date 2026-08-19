package com.prog.web.inter;

import com.prog.web.main.VideoGameDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/games")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface VideoGameRestClient {

    @GET
    List<VideoGameDto> findAll(); // ¡El Proxy deserializa la lista automáticamente!

    @GET
    @Path("/{id}")
    VideoGameDto findById(@PathParam("id") Integer id); // Devuelve el DTO directo

    @POST
    Response create(VideoGameDto videoGameDto); // Aquí sí dejamos Response para ver el Status 201

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") Integer id, VideoGameDto videoGameDto);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

    @GET
    @Path("/platform/{platform}")
    List<VideoGameDto> findByPlatform(@PathParam("platform") String platform);

    @GET
    @Path("/deals")
    List<VideoGameDto> findDeals(@QueryParam("maxPrice") Double maxPrice); // Nombre corregido
}