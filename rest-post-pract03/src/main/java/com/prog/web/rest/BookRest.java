package com.prog.web.rest;

import com.prog.web.db.Book;
import com.prog.web.servicios.inter.BookService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class BookRest{

    private BookService bookService;

    @Inject
    public BookRest(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        var obj = bookService.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return bookService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(Book book) {
        Book saved = bookService.save(book);

        return saved != null
                ? Response.status(Response.Status.CREATED).entity(saved).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Book book) {
        return bookService.update(id, book).map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return bookService.delete(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByTitle(@QueryParam("title") String title) {
        List<Book> findTitle = bookService.findByTitleLike(title);
        return Response.ok(findTitle).build();
    }

    @GET
    @Path("/searchYear/{year}")
    public Response findByPublishedYearGreaterThan(@PathParam("year") Integer year) {
        List<Book> findYear = bookService.findByPublishedYearGreaterThan(year);
        return Response.ok(findYear).build();
    }

    @GET
    @Path("/availability")
    public Response findByIsAvailable(@QueryParam("status") Boolean status){
        // Si no mandan el parámetro, asumimos que quieren ver los que SÍ están disponibles
        if (status == null) {
            status = true;
        }
        return Response.ok().entity(bookService.findByIsAvailable(status)).build();
    }

}
