package com.prog.web.rest;

import com.prog.web.db.Movie;
import com.prog.web.servicios.impl.MovieServiceImpl;
import com.prog.web.servicios.inter.MovieService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movies")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MovieRest {

    private final MovieService movieService;

    @Inject
    public MovieRest(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GET
    public Response findAll() {
        var obj = movieService.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return movieService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Movie movie) {
        Movie saved = movieService.save(movie);

        return saved != null
                ? Response.status(Response.Status.CREATED).entity(saved).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response updated(@PathParam("id") Integer id, Movie movie) {
        return movieService.update(id, movie).map(up -> Response.ok(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return movieService.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/genre/{genre}")
    public Response findByGenre(@PathParam("genre") String genre) {
        List<Movie> findGenre = movieService.findByGenre(genre);
        return Response.ok(findGenre).build();
    }

    @GET
    @Path("/title")
    public Response findByTitle(@QueryParam("title") String title) {
        List<Movie> titles = movieService.findByTitleLike(title);
        return Response.ok(titles).build();
    }

    @GET
    @Path("/rating")
    public Response findByRating(@QueryParam("minRating") Double minRating) {
        // 1. Primero validamos y damos el valor por defecto
        if (minRating == null) {
            minRating = 4.0;
        }
        // 2. Ahora sí, buscamos con el valor seguro
        List<Movie> ratings = movieService.findByRatingGreaterThanEquals(minRating);

        return Response.ok(ratings).build();
    }

}
