package com.prog.web.rest;

import com.prog.web.dto.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/books")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BookRest {

    private static Map<Integer, Book> books = Map.of(
            1, Book.builder().id(1).title("asd").author("ad").price(28.7).build(),
            2, Book.builder().id(2).title("asd2").author("dom").price(15.6).build()
    );

    @GET
    @Path("/{id: \\d+}")
    public Response findById(@PathParam("id") Integer id) {
        var obj = BookRest.books.get(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(obj).build();
    }

    @GET
    @Path("/search")
    @Produces({MediaType.TEXT_PLAIN})
    public String findAuthor(@QueryParam("author")  @DefaultValue("Anónimo") String author) {
        return "El autor es: " + author;
    }

    @POST
    //@Path("/crear")
    public Response crear(Book book) {
        books.put(book.getId(), book);
        System.out.println("Libro creado: " + book);
        return Response.status(Response.Status.CREATED).build();
    }

}
