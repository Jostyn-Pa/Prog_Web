package com.prog.web.rest;

import com.prog.web.db.Book;
import com.prog.web.service.inter.BookServiceInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@RequestScoped
public class BookRest {

    private final BookServiceInter bookService;

    @Inject
    public BookRest(BookServiceInter bookService) {
        this.bookService = bookService;
    }

    @GET
    public Response findAll() {
        var obj =  bookService.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return bookService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(Book book) {
        Book saved = bookService.save(book);
        return Response.ok().entity(saved).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Book book) {
        return bookService.update(id, book).map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return bookService.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
