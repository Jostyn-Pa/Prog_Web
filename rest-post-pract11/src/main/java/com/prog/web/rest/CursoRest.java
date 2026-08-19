package com.prog.web.rest;

import com.prog.web.db.Curso;
import com.prog.web.servicios.inter.CursoServicioInter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
@RequestScoped
public class CursoRest {

    private final CursoServicioInter cursoServicioInter;

    @Inject
    public CursoRest(CursoServicioInter cursoServicioInter) {
        this.cursoServicioInter = cursoServicioInter;
    }

    @GET
    public Response findAll() {
        var obj = cursoServicioInter.findAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        return cursoServicioInter.findById(id).map(Response::ok).orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    public Response create(Curso curso) {

        Curso obj = cursoServicioInter.save(curso);

        if(curso.getPrecio() == null || curso.getTitulo() == null || curso.getCategoria() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(obj).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Curso curso) {
        return cursoServicioInter.update(id, curso).map(up ->
            Response.ok().entity(up).build()
        ).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        return cursoServicioInter.delete(id)
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search")
    public Response searchQuery(@QueryParam("categoria")String categoria, @QueryParam("precioMaximo")Double precioMaximo) {
        var obj = cursoServicioInter.searchQuery(categoria, precioMaximo);
        return Response.ok(obj).build();
    }

}
