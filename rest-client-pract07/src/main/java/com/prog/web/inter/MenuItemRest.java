package com.prog.web.inter;

import com.prog.web.dto.MenuItemDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.List;

@Path("/api/items")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface MenuItemRest {
    @GET
    List<MenuItemDto>findAll();

    @GET
    @Path("/{id}")
    MenuItemDto findById(@PathParam("id") Integer id);

    @POST
    Response save(MenuItemDto menuItem);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id")Integer id, MenuItemDto menuItem);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

    @GET
    @Path("/category/{cat}")
    List<MenuItemDto> findByCategory(@PathParam("cat") String category);

    @GET
    @Path("/veggie")
    List<MenuItemDto> findByIsVegetarian(@QueryParam("isVeggie") Boolean isVegetarian);
}
