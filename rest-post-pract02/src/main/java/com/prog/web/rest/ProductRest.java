package com.prog.web.rest;

import com.prog.web.db.Product;
import com.prog.web.servicios.inter.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/products") // <-- CORREGIDO: Es @Path, no @ApplicationPath
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRest {

    private final ProductService productService;

    @Inject
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    /* --- LOS CLÁSICOS (CRUD) --- */

    @GET
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return productService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(Product product) {
        Product saved = productService.save(product);
        return saved != null
                ? Response.status(Response.Status.CREATED).entity(saved).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Product product) {
        return productService.update(id, product)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return productService.delete(id)
                ? Response.noContent().build() // <-- 204 No Content es mejor para DELETE
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    /* --- LOS ESPECIALES --- */

    // Ruta: /products/category/electronics
    @GET
    @Path("/category/{categoryName}") // <-- CORREGIDO: Evita colisión con /{id}
    public Response findByCategory(@PathParam("categoryName") String categoryName) {
        List<Product> results = productService.findByCategory(categoryName);
        if (results != null && !results.isEmpty()) {
            return Response.ok(results).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Ruta: /products/search?name=televisor
    @GET
    @Path("/search") // <-- CORREGIDO: Ruta más semántica para búsquedas
    public Response searchByName(@QueryParam("name") String name) {
        List<Product> results = productService.findByName(name);
        return Response.ok(results).build(); // Siempre devuelve 200, incluso si es una lista vacía []
    }

    // Ruta: /products/low-stock?limit=5
    @GET
    @Path("/low-stock")
    public Response getLowStock(@QueryParam("limit") Integer limit) {
        // Lógica del límite por defecto
        if (limit == null || limit <= 0) {
            limit = 10;
        }

        List<Product> results = productService.findByStockLessThan(limit);
        return Response.ok(results).build();
    }
}