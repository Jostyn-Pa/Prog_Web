package com.prog.web.rest;

import com.prog.web.db.MenuItem;
import com.prog.web.servicios.inter.MenuItemService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/items")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@RequestScoped
public class MenuItemRest {

    private final MenuItemService menuItemService;

    @Inject
    public MenuItemRest(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GET
    public Response findAll() {
        var obj = menuItemService.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return menuItemService.findById(id).map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response save(MenuItem menuItem) {
        MenuItem obj = menuItemService.save(menuItem);
        return Response.status(Response.Status.CREATED).entity(obj).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, MenuItem menuItem) {
        return menuItemService.update(id, menuItem).map(up -> Response.ok().entity(up).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return menuItemService.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/category/{cat}")
    public Response findByCategory(@PathParam("cat") String category) {
        var obj = menuItemService.findByCategory(category);
        return Response.ok(obj).build();
    }

    @GET
    @Path("/veggie")
    public Response findByIsVegetarian(@QueryParam("isVeggie") Boolean isVegetarian) { // Parámetro cambiado a isVeggie
        // La validación por defecto que faltaba:
        if (isVegetarian == null) {
            isVegetarian = true;
        }
        var obj = menuItemService.findByIsVegetarian(isVegetarian);
        return Response.ok(obj).build();
    }
}
