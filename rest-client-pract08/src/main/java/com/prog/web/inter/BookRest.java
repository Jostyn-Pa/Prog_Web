package com.prog.web.inter;

import com.prog.web.dto.BookDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/books")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface BookRest {
    @GET
    List<BookDto> findAll();

    @GET
    @Path("/{id}")
    BookDto findById(@PathParam("id")Integer id);

    @POST
    Response create(BookDto bookDto);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id")Integer id, BookDto bookDto);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id")Integer id);
}
